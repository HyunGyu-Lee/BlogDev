package com.leelab.blogproject.post.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.category.dto.MainCategoryDTO;
import com.leelab.blogproject.category.dto.SubCategoryDTO;
import com.leelab.blogproject.category.service.CategoryService;
import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.common.annotation.RequireAuthCheck;
import com.leelab.blogproject.common.exception.GeneralBlogException;
import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.feature.service.FeatureService;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.neighbor.service.NeighborService;
import com.leelab.blogproject.notification.service.NotificationService;
import com.leelab.blogproject.notification.vo.NotificationBuilder;
import com.leelab.blogproject.notification.vo.NotificationVo;
import com.leelab.blogproject.post.dto.PostDTO;
import com.leelab.blogproject.post.service.PostService;
import com.leelab.blogproject.post.vo.PostVO;
import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.user.service.UserService;
import com.leelab.blogproject.utils.page.PageVo;
import com.leelab.blogproject.visithistory.service.VisitHistoryService;
import com.leelab.blogproject.visithistory.vo.VisitHistoryVO;
import com.leelab.blogproject.visithistory.vo.VisitorCountVO;

@Controller
public class PostController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FeatureService featureService;

	@Autowired
	private VisitHistoryService visitHistoryService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NeighborService neighborService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	/* 로그인 돼있으면 로그인유저 ID, 아니면 IP주소 */
	public String getVisitor() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		UserDTO loginUser = (UserDTO)request.getSession().getAttribute("user");
		if(loginUser!=null) return loginUser.getId();
		else return request.getRemoteAddr();
	}
	
	@RequestMapping("openWritePost")
	@RequireAuthCheck(checkFor="blogId")
	public ModelAndView openWritePost(@RequestParam("blogId") String id) {
		ModelAndView mv = new ModelAndView("blog/writePost");
		mv.addObject("category", categoryService.getUserCategory(id));
		mv.addObject("id", id);
		return mv;
	}
	
	@RequestMapping(value="postPreview", method=RequestMethod.POST)
	public ModelAndView postPreview(@RequestParam Map<String, String> requestScope) {
		
		String ctn = requestScope.get("content");
		
		logger.info("Content-length : {}", ctn.length());
		
		ModelAndView mv = new ModelAndView("blog/preview");
		mv.addObject("title", requestScope.get("title"));
		mv.addObject("categoryName", requestScope.get("categoryName"));
		mv.addObject("content", requestScope.get("content"));
		mv.addObject("category", categoryService.getUserCategory(requestScope.get("blogId")));
		return mv;
	}
	
	@RequestMapping(value="writePost", method=RequestMethod.POST)
	@ResponseBody
	public void writePost(PostDTO post) {
		String user_id = post.getUser_id();
		
		postService.newPost(post);
		
		NotificationVo vo = new NotificationBuilder().setLink("/postview/"+user_id+"/"+post.getId())
													 .setMessage("<strong>"+userService.getUser(user_id).getNickname()+"</strong>님의 새 포스트가 있습니다.")
													 .setNotificator(user_id).build();
		
		notificationService.notificate(vo, neighborService.getNeighborList(user_id));
	}
	
	@NotLoginCheck
	@RequestMapping("/{id}") 
	public ModelAndView viewPosts(SearchVO searchVo, PageVo pageVo, @PathVariable String id) throws GeneralBlogException {
		logger.info("Open Blog to {}", id);
		
		/* 블로그 방문자 ID */
		String visitorId = getVisitor();
		
		/* 블로그 주인 정보 */
		UserDTO user = userService.getUser(id);
		if(user==null)throw new GeneralBlogException("존재하지 않는 사용자의 블로그");
		
		/* 블로그 카테고리 정보 */
		Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);

		/* 포스트 페이징하여 가져옴 */
		searchVo.setUser_id(id);
		pageVo = postService.getPageInfo(searchVo, pageVo);
		ArrayList<PostDTO> posts = postService.getPosts(searchVo, pageVo);
		
		/* 블로그 Feature정보 */
		FeatureVo feature = featureService.getBlogFeature(searchVo.getUser_id());
		
		/* Today, Total 정보 */
		VisitHistoryVO historyVo = new VisitHistoryVO();
		historyVo.setBlog_id(id);
		historyVo.setVisitor_id(visitorId);
		
		visitHistoryService.visitBlog(historyVo);
		/* 아직 해결안됨 */
		VisitorCountVO countVo = visitHistoryService.getBlogVisitorCountInfo(historyVo);
		logger.debug("{}",countVo);

		ModelAndView mv = new ModelAndView("blog/blog");		
		mv.addObject("user", user);
		mv.addObject("category", category);
		mv.addObject("posts", posts);
		mv.addObject("page", pageVo);
		mv.addObject("search", searchVo);
		mv.addObject("feature", feature);
		mv.addObject("visit_count", countVo);

		logger.debug("{}", pageVo);
		return mv;
	}
	
	@RequestMapping("/postview/{id}/{post_id}")
	@NotLoginCheck
	public ModelAndView viewPost(@PathVariable(name="id") String id, @PathVariable(name="post_id") String postId, SearchVO searchVo, PageVo pageVo) {
		logger.info("Open blog to {} - No.{}'s post",id, postId);
		ModelAndView mv = new ModelAndView("blog/blog");
		
		searchVo.setUser_id(id);
		String viewUserId = getVisitor();

		PostDTO post = postService.getPostDetail(searchVo, viewUserId);
		
		if(post==null)
		{
			mv.setViewName("error");
			return mv;
		}
		
		searchVo.setMain_category_id(post.getMain_category_id());
		searchVo.setSub_category_id(post.getSub_category_id());

		ArrayList<PostVO> posts = postService.getPostsInPage(searchVo, pageVo);
		pageVo.setCurrentPage(posts.get(0).getCurrentPage());
		pageVo = postService.getPageInfo(searchVo, pageVo);
		logger.debug("{}", searchVo);
		
		logger.debug("viewPost pageVo {}", pageVo);

		Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);
		mv.addObject("user", userService.getUser(id));
		mv.addObject("category", category);
		mv.addObject("post", post);
		mv.addObject("footer", posts);
		mv.addObject("page", pageVo);
		mv.addObject("search", searchVo);
		mv.addObject("feature", featureService.getBlogFeature(searchVo.getUser_id()));
		return mv;
	}
	
	@RequestMapping(value="ajax/getPostList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getPostList(SearchVO searchVo, PageVo pageVo){
		
		pageVo = postService.getPageInfo(searchVo, pageVo);
		ArrayList<PostDTO> posts = postService.getPosts(searchVo, pageVo);
		
		HashMap<String, Object> result = SimpleHashMap.newInstance().put("posts", posts).put("page", pageVo).put("search", searchVo);

		logger.debug("{}", searchVo);
		logger.debug("{}", pageVo);
		
		return result;
	}
	
	@RequestMapping(value="deletePost", method=RequestMethod.POST)
	@ResponseBody
	public void deletePost(SearchVO searchVo) {
		int post_id = searchVo.getPost_id();
		logger.info("Delete post - {}", post_id);		
		postService.deletePost(post_id);
	}
	
	@RequestMapping("openUpdatePost")
	public ModelAndView openUpdatePost(SearchVO searchVo, @SessionAttribute("user") UserDTO loginUser) {
		ModelAndView mv = new ModelAndView("blog/updatePost");
		logger.debug("{}, {}", searchVo, loginUser);
		PostDTO post = postService.getPostDetail(searchVo, loginUser.getId());
		mv.addObject("category", categoryService.getUserCategory(post.getUser_id()));
		post.setContent(StringEscapeUtils.escapeJavaScript(post.getContent()));
		mv.addObject("post", post);
		return mv;		
	}
	
	@RequestMapping(value="updatePost", method=RequestMethod.POST)
	@ResponseBody
	public void updatePost(PostDTO post) {
		postService.updatePost(post);
	}
	
}

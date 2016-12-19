package com.leelab.blogproject.user;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Table : BLOG_USER
 * */
@Repository
public interface UserDAO {

	/* 모든 사용자 선택 */
	public List<UserDTO> selectAll();
	
	/* id가 id인 사용자 선택 */
	public UserDTO selectUser(String id);
	
	/* UserDTO 사용자 추가 */
	public void add(UserDTO user);
	
	/* id가 id인 사용자 제거 */
	public void delete(String id);

	/* 모든 사용자 제거 */
	public void deleteAll();

	/* 사용자 정보 수정 */
	public void update(UserDTO user);
}

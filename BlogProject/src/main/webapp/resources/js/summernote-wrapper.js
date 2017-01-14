var editor;

var onImageUploadCallback;
var contextPath;
$.User = {
	setContextPath : function(ctxPath) {
		contextPath = ctxPath;
	},	
	duplicateCheck : function(id, onSuccess) {
		$.ajax({
			url  : contextPath+'/ajax/duplicate_check',
			type : 'post',
			data : {id : id},
			success : function(response) {
				onSuccess(response);
			}
		});
	},
	login : function(settings, onSuccess) {
		if(settings.isSocial)
		{
			console.log('소셜 사용자 로그인 요청');
			console.dir(settings);
			$.ajax({
				url : contextPath+'/ajax/login',
				type : 'post',
				data : {id:settings.id,nickname:settings.nickname,profile_url:settings.profile_url,socialLogin:true},
				success : onSuccess
			});
		}
		else
		{
			/* 일반 사용자 로그인 기능 구현 */
			$.ajax({
				url : contextPath+'/ajax/login',
				type : 'post',
				data : {"id" : settings.id, "password" : settings.password, "redirectUri" : settings.redirectUri},
				dataType : 'json',
				success : onSuccess
			});
		}
	}
}

$.snote = {
	initalize : function(editorArea) {
		var toolbar = [
			['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['table', ['table']],
		    ['insert', ['link', 'picture', 'video']],
		    ['view', ['fullscreen', 'codeview']]
		];
			
		var settings = {
			height: 300,
			minHeight: null,
			maxHeight: null,
			focus : true,
			lang: 'ko-KR',	
			toolbar: toolbar,
			callbacks : {
				onImageUpload : onImageUploadCallback
			}
		};
		
		editor = $(editorArea);
		editor.summernote(settings);
	},
	setOnImageUpload : function(callback) {
		onImageUploadCallback = callback;
	},
	node : function(node) {
		editor.summernote('insertNode', node);
	},
	image : function(url, imageModify) {
		editor.summernote('insertImage', url, imageModify);
	}
}
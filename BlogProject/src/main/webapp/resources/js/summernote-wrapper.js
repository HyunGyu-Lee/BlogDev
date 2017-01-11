var editor;

var onImageUploadCallback;
	
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
function FormObject(url, type){
	this.formObj = $.parseHTML('<form action="'+url+'" method="'+type+'"></form>');
}

FormObject.prototype.append = function(key,value) {
	$(this.formObj).append('<input type="hidden" name="'+key+'" value="'+value+'"/>');
};

FormObject.prototype.submit = function(){
	$(this.formObj).submit();
}

function cut(content, byte) {
	var contentLength = 0;
	var replacedContent = '';
	var temp;
	for (i = 0; i < content.length; i++)
	{
        var code = content.charCodeAt(i);
        var ch = content.substr(i,1).toUpperCase();

        temp = content.substr(i,1)
        code = parseInt(code);
         
        if ((ch < "0" || ch > "9") && (ch < "A" || ch > "Z") && ((code > 255) || (code < 0))) contentLength = contentLength + 3;
        else contentLength = contentLength + 1;

        if(contentLength>byte)
        {
        	replacedContent += '···';
        	break;
        }
        else replacedContent = replacedContent+temp;
	}    
        return replacedContent;
        
}

function valueFromQueryString(query_string, key) {
	key = key.replace(/[*+?^$.\[\]{}()|\\\/]/g, "\\$&"); // escape RegEx meta chars
    var match = location.search.match(new RegExp("[?&]"+key+"=([^&]+)(&|$)"));
    return match && decodeURIComponent(match[1].replace(/\+/g, " "));
}

function setImageFromFile(input, expression) {
	if (input.files && input.files[0])
	{
        var reader = new FileReader();

        reader.onload = function (e) {
            $(expression).attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function isEmptyString(str) {
	var t = str.trim();
	if(t.length==0)return true;
	return false;
}

function emailValidate(email) {
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	if(!regExp.test(email))
	{
		return false;
	}
	return true;
}

function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

function appendForm(form, key, value) {
	form.append('<input type="hidden" name="'+key+'" value="'+value+'"/>');
}


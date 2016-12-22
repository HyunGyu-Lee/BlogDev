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

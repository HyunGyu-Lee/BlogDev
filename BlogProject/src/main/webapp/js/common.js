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

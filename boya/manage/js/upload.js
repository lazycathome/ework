function uploadImage(form)
{
	var	upload = document.getElementById('uploadFileHere');
	var	w = document.body.scrollWidth;

	if(w > 380)x = (w - 380) / 2;
	else x = 0;

	with(upload.style)
	{
		position = 'absolute';
		left = x + 'px';
		top = 0 + 'px';
		display = 'block';
		width = '380px';
		backgroundColor = 'white';
		border = '1 solid #808080';
	}

	document.getElementById('uploadFileFrame').src = '../uploadImg.html';
}

function checkOfUploadImageForm(form)
{
	if(form.localFile.value == '')
	{
		alert('请选择一个图片文件。');
		form.localFile.focus();
		return false;
	}
	parent.document.forms('userInfoForm').imageUrl.value = form.romoteFile.value;

	return true;
}

function romoteFileName(form)
{
	var	local = form.localFile.value;
	var	ntime = new Date();
	form.romoteFile.value = '/upload/' + ntime.getTime() + local.substr(local.lastIndexOf('.'));
}
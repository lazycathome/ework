(function($){
	$.util = {
		ajax : function(url, param, callback){
			$.ajax({
				url : url,
				data : param,
				callback : callback,
				type : "post",
				success : function(data){
					this.callback(data);
				},
				error : function(msg){
					jAlert(msg,"error");
				}
			});
		},
		
		getHtml : function(data, exp){
			$(data).find(exp).each(function() {
				var id_i = $.readXML.getXMLFiledValue(this, "id");
			});
		}
	};
	
})(jQuery);

function checkOfSysMailForm(form)
{
	if(form.email.value == '')
	{
		alert('邮箱地址必须输入。');
		form.email.focus();
		return false;
	}
	
	if(!checkMailAddress(form.email.value))
	{
		alert('不是一个有效的邮箱地址。');
		form.email.focus();
		return false;
	}

	if(form.server.value == '')
	{
		alert('服务器必须输入。');
		form.server.focus();
		return false;
	}
	
	if(form.userName.value == '')
	{
		alert('用户名必须输入。');
		form.userName.focus();
		return false;
	}
	
	if(form.userPwd.value == '')
	{
		alert('密码必须输入。');
		form.userPwd.focus();
		return false;
	}
	
	return true;
}

function checkMailAddress(mailAddr) 
{ 
	var	pattern = /\w+@\w+\.[a-z]+/;

	if(pattern.test(mailAddr))return true; 

	return false;
}

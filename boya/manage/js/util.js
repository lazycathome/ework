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
		alert('�����ַ�������롣');
		form.email.focus();
		return false;
	}
	
	if(!checkMailAddress(form.email.value))
	{
		alert('����һ����Ч�������ַ��');
		form.email.focus();
		return false;
	}

	if(form.server.value == '')
	{
		alert('�������������롣');
		form.server.focus();
		return false;
	}
	
	if(form.userName.value == '')
	{
		alert('�û����������롣');
		form.userName.focus();
		return false;
	}
	
	if(form.userPwd.value == '')
	{
		alert('����������롣');
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

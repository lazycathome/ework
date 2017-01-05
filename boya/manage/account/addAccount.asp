<%@LANGUAGE="VBSCRIPT" CODEPAGE="65001"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加分类</title>
<link href="../../css/showDialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../js/showDialog.js"></script>
<script type="text/javascript" src="../js/account.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
</head>

<body>
<form name="userInfoForm"  action="saveAccount.asp" method="post" id="userInfoForm">
<table width="95%" border="0" cellpadding="0" cellspacing="0" class="bk" align="center">
   <tr>
     <td valign="top"><table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
       <tr>
         <td height="15" align="right" valign="top" bgcolor="#FAFAFA"></td>
       </tr>
     </table>
       <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#CCCCCC">
         <tr>
           <td height="45" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>账户名称：</td>
           <td height="35" bgcolor="#FFFFFF" valign="middle"><input id="username" name="username" type="text" maxlength="32" value="" /></td>
         </tr> 
         <tr bgcolor="#FAFAFA">
           <td width="31%" height="45" align="right" valign="middle">账户密码：</td>
           <td width="69%" valign="middle"><input id="password" name="password" type="password" value="" /></td>
         </tr>
         <tr bgcolor="#FFFFFF">
           <td height="45" align="right" valign="middle">重复密码：</td>
           <td valign="middle"><input id="password2" name="password2" type="password" value="" /></td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td height="45" align="right" valign="middle">账户描述：</td>
           <td valign="middle"><input id="description" name="description" type="text" value="" /></td>
         </tr>
         <tr>
           <td align="right" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;</td>
           <td height="40" valign="middle" bgcolor="#FFFFFF"><input id="submitInfo" type="submit" value="确认添加" /><input id="reset" type="reset" value="确认重置" /></td>
         </tr>
       </table>
       </td>
   </tr>
  </table>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#userInfoForm").bind("submit",function(){
		return $.account.validation();
	});
});
</script>
</body>
</html>

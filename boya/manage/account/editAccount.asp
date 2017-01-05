<!--#include file="edit.asp"-->
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
<form name="userInfoForm"  action="updateAccount.asp" method="post" id="userInfoForm">
<input name="id" value="<%=id%>" type="hidden" />
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
           <td height="35" bgcolor="#FFFFFF" valign="middle"><input id="username" name="username" type="text" maxlength="32" value="<%=username%>" /></td>
         </tr> 
         <tr bgcolor="#FAFAFA">
           <td height="45" align="right" valign="middle">旧 密 码：</td>
           <td valign="middle"><input id="password" name="password" type="password" value="" /></td>
         </tr>
         <tr bgcolor="#FFFFFF">
           <td height="45" align="right" valign="middle">新 密 码：</td>
           <td valign="middle"><input id="passwordNew" name="passwordNew" type="password" value="" /></td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td height="45" align="right" valign="middle">重复新密码：</td>
           <td valign="middle"><input id="passwordNew1" name="passwordNew1" type="password" value="" /></td>
         </tr>
         <tr bgcolor="#FFFFFF">
           <td height="45" align="right" valign="middle">账户描述：</td>
           <td valign="middle"><input id="description" name="description" type="text" value="<%=des%>" /></td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td align="right" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;</td>
           <td height="40" valign="middle" bgcolor="#FFFFFF"><input id="submitInfo" type="submit" value="确认修改" /><input id="back" type="button" value="确认返回" /></td>
         </tr>
       </table>
       </td>
   </tr>
  </table>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#userInfoForm").bind("submit",function(){
		return $.account.validationUpate();
	});
	$("#back").bind("cilick",function(){
		history.go(-1);
	});
});
</script>
</body>
</html>

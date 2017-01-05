<!--#include file="edit.asp"-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>回复留言</title>
<link href="../../css/showDialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../js/showDialog.js"></script>
<script type="text/javascript" src="../js/feedback.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
</head>

<body>
<form name="userInfoForm"  action="updateFeedback.asp" method="post" id="userInfoForm">
  <input type="hidden" name="id" value="<%=id%>" />
<table width="95%" border="0" cellpadding="0" cellspacing="0" class="bk" align="center">
   <tr>
     <td valign="top"><table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
       <tr>
         <td height="15" align="right" valign="top" bgcolor="#FAFAFA"></td>
       </tr>
     </table>
       <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#CCCCCC">
         <tr>
           <td height="45" align="right" valign="middle" bgcolor="#FFFFFF">留言标题：</td>
           <td height="35" bgcolor="#FFFFFF" valign="middle"><%= title %></td>
         </tr> 
         <tr bgcolor="#FAFAFA">
           <td width="31%" height="45" align="right" valign="middle">联系方式：</td>
           <td width="69%" valign="middle"><%= tel %></td>
         </tr>
         <tr bgcolor="#FFFFFF">
           <td height="45" align="right" valign="middle">留言作者：</td>
           <td valign="middle"><%= username %></td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td height="45" align="right" valign="middle">留言内容：</td>
           <td valign="middle"><%= content %></td>
         </tr>
          <tr bgcolor="#FAFAFA">
           <td height="45" align="right" valign="middle"><span class="red">* </span>回复内容：</td>
           <td valign="middle"><textarea cols="40" rows="5" name="replyContent"><%=replyContent%></textarea></td>
         </tr>
         <tr>
           <td align="right" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;</td>
           <td height="40" valign="middle" bgcolor="#FFFFFF"><input id="submitInfo" type="submit" value="确认回复" /><input id="reset" type="reset" value="确认重置" /></td>
         </tr>
       </table>
       </td>
   </tr>
  </table>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#userInfoForm").bind("submit",function(){
		return $.feedback.validation();
	});
});
</script>
</body>
</html>

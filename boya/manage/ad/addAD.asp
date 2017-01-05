<%@LANGUAGE="VBSCRIPT" CODEPAGE="65001"%>
<!--#include file="../editor.asp" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加分类</title>
<link href="../../css/showDialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../js/showDialog.js"></script>
<script type="text/javascript" src="../js/ad.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
</head>

<body>
<form name="form1"  action="saveAD.asp" method="post" id="userInfoForm">
<input type="hidden" name="typeId" value="1" />
<table width="95%" border="0" cellpadding="0" cellspacing="0" class="bk" align="center">
   <tr>
     <td valign="top"><table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
       <tr>
         <td height="15" align="right" valign="top" bgcolor="#FAFAFA"></td>
       </tr>
     </table>
       <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#CCCCCC">
         <tr>
           <td height="45" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>广告名称：</td>
           <td height="35" bgcolor="#FFFFFF" valign="middle"><input id="name" name="name" type="text" maxlength="32" value="" /></td>
         </tr> 
         <tr bgcolor="#FAFAFA">
           <td width="31%" height="45" align="right" valign="middle">广告栏目：</td>
           <td width="69%" column="0" id="columnList" valign="middle"></td>
         </tr>
         <tr>
           <td height="45" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>广告类型：</td>
           <td bgcolor="#FFFFFF" valign="middle"><input id="typeId" name="typeId" <%=type1%> type="radio" value="1" />flash<input <%=type2%> id="typeId" name="typeId" type="radio" value="2" />幻灯片<input <%=type3%> id="typeId" name="typeId" type="radio" value="3" />图片</td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td height="45" align="right" valign="middle">广告类容：</td>
           <td valign="middle">
            <% createFckEditor content,"content",320,700,"Default" %>
           </td>
         </tr>
         <tr>
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
	$.column.getColumnList("typeId=4");
	$("#userInfoForm").bind("submit",function(){
		return $.ad.validation();
	});
	$("#back").bind("click",function(){
		history.go(-1);
	});
});
</script>
</body>
</html>

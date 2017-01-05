<!--#include file="edit.asp" -->
<!--#include file="../editor.asp" -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改文章</title>
<link href="../../css/jquery.datepick.css" rel="stylesheet" type="text/css" />
<link href="../../css/showDialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../js/showDialog.js"></script>
<script type="text/javascript" src="../js/articles.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript" src="../js/column.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../../js/jquery.datepick.js"></script>
</head>

<body>
<form name="form1"  action="updateArticles.asp" method="post" id="userInfoForm">
<input type="hidden" name="typeId" value="<%=typeId%>" />
<input type="hidden" name="id" value="<%=id%>" />
<input type="hidden" name="columnName" id="columnName" value=""/>
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="bk" align="center">
   <tr>
     <td valign="middle"><table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
       <tr>
         <td height="15" align="right" valign="middle" bgcolor="#FAFAFA"></td>
       </tr>
     </table>
       <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#CCCCCC">
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>文章名称：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="subject" name="subject" type="text" value="<%=subject%>" /></td>
         </tr> 
         <tr bgcolor="#FAFAFA">
           <td width="15%" height="30" align="right" valign="middle"><span class="red">* </span>所属分类：</td>
           <td width="85%" isId="0" column="<%=columnId%>" id="columnList"></td>
         </tr>
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>录入时间：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="created" name="created" type="text" value="<%=regTime%>" /></td>
         </tr>
         <% if source == 11 then %>
          <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>学员图片：</td>
           <td height="30" bgcolor="#FFFFFF"><input type="text" name="imageUrl" size="48" value="<%=description%>" tabindex=4> <input type="button" value="上传照片" name="upload" onclick="javascript:uploadImage(userInfoForm);"></td>
         </tr>
         <%end if%>
         <tr bgcolor="#FAFAFA">
           <td width="15%" height="30" align="right" valign="middle"><span class="red">* </span>文章内容：</td>
           <td width="85%" valign="middle">
           <% createFckEditor content,"content",400,700,"Default" %>
           </td>
         </tr>
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>高亮：</td>
           <td bgcolor="#FFFFFF"><input id="isHot" name="isHot" <%=isHot%> type="checkbox" value="1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="red">* </span>置顶：<input id="isTop" name="isTop" <%=isTop%> type="checkbox" value="1" /></td>
         </tr>
         
         <%if typeId= 1 then%>
         <tr bgcolor="#FAFAFA">
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">文章来源：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="source" name="source" type="text" value="<%=sources%>" /></td>
         </tr><tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">文章作者：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="author" name="author" type="text" value="<%=author%>" /></td>
         </tr>
         <%else%>
         <tr bgcolor="#FAFAFA">
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">文章作者：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="author" name="author" type="text" value="<%=author%>" /></td>
         </tr>
         <%end if%>
         <tr>
           <td align="right" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;</td>
           <td height="40" valign="bottom" bgcolor="#FFFFFF"><input id="submitInfo" type="submit" value="确认修改" /><input id="back" type="button" value="确认返回" /></td>
         </tr>
       </table>
       </td>
   </tr>
  </table>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$.column.getColumnList("typeId=<%=typeId%>","../column/queryColumn.asp");
	$("#created").datepick({
		changeMonth: true,
		numberOfMonths: 1,
		onSelect: function(selectedDate) {
			var instance = $(this).data("datepick");
			var date = $.datepick.parseDate(instance.settings.dateFormat || $.datepick._defaults.dateFormat, selectedDate, instance.settings);
		}
	});
	$("#userInfoForm").bind("submit",function(){
		return $.articles.validation();
	});
	$("#back").bind("click",function(){
		history.go(-1);
	});
	
});
</script>
<div id=uploadFileHere style="display: none;">
<iframe id=uploadFileFrame src=../uploadImg.html width=100% height=160 scrolling=no frameborder=no></iframe>
</div>
</body>
</html>

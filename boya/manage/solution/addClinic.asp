<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加诊疗中心推荐课程</title>
<link href="../../css/showDialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../../js/showDialog.js"></script>
<script type="text/javascript" src="../js/solution.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript" src="../js/column.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/upload.js"></script>
<script type="text/javascript" src="../js/articles.js"></script>
</head>

<body>
<form name="userInfoForm"  action="saveSolution.asp" method="post" id="userInfoForm">
<input type="hidden" name="typeId" value="1"/>
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="bk" align="center">
   <tr>
     <td valign="middle"><table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
       <tr>
         <td height="15" align="right" valign="middle" bgcolor="#FAFAFA"></td>
       </tr>
     </table>
       <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#CCCCCC">
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>课程名称：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="solutionName" name="solutionName" type="text" value="" /></td>
         </tr> 
         <tr bgcolor="#FAFAFA">
           <td width="15%" height="30" align="right" valign="middle"><span class="red">* </span>所属问题：</td>
           <td width="85%" column="0" id="articleList"></td>
         </tr>
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>课程图片：</td>
           <td height="30" bgcolor="#FFFFFF"><input type="text" name="imageUrl" size="48" value="/images/solution.gif" tabindex=4> <input type="button" value="上传照片" name="upload" onclick="javascript:uploadImage(userInfoForm);"></td>
         </tr>
         <!--tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>排序：</td>
           <td bgcolor="#FFFFFF"><select name="display">
           <option  value="1">1</option>
           <option value="2">2</option>
           <option value="3">3</option>
           <option value="4">4</option>
           <option value="5">5</option>
           <option value="6">6</option>
           <option value="7">7</option>
           <option value="8">8</option>
           <option value="9">9</option>
           <option value="10">10</option>
           <option value="11">11</option>
           <option value="12">12</option>
           <option value="13">13</option>
           <option value="14">14</option>
           <option value="15">15</option>
           <option value="16">16</option>
           <option value="17">17</option>
           <option value="18">18</option>
           <option value="19">19</option>
           <option value="20">20</option>
           </select></td>
         </tr-->
         <!--tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">文章来源：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="source" name="source" type="text" value="" /></td>
         </tr-->
         <tr bgcolor="#FAFAFA">
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">课程时长：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="time" name="time" type="text" value="" /></td>
         </tr>
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">服务期：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="expireTime" name="expireTime" type="text" value="" /></td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">服务类型：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="type" name="type" type="text" value="" /></td>
         </tr>
         <tr>
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF">课程费用：</td>
           <td height="30" bgcolor="#FFFFFF"><input id="money" name="money" type="text" value="" /></td>
         </tr>
         <tr bgcolor="#FAFAFA">
           <td height="30" align="right" valign="middle" bgcolor="#FFFFFF"><span class="red">* </span>置顶：</td>
           <td bgcolor="#FFFFFF"><input id="isTop" name="isTop" type="checkbox" value="1" /></td>
         </tr>
         <tr>
           <td align="right" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;</td>
           <td height="40" valign="bottom" bgcolor="#FFFFFF"><input id="submitInfo" type="submit" value="确认添加" /><input id="reset" type="reset" value="确认重置" /></td>
         </tr>
       </table>
       </td>
   </tr>
  </table>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$.articles.getArticlesList("typeId=2&isCurpage=1","../articles/queryArticles.asp");

	$("#userInfoForm").bind("submit",function(){
		
		return $.articles.validationClinic();;
	});
});
</script>

<div id=uploadFileHere style="display: none;">
<iframe id=uploadFileFrame src=uploadImg.html width=100% height=160 scrolling=no frameborder=no></iframe>
</div>
</body>
</html>

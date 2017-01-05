<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线留言</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="../css/css.css" rel="stylesheet" type="text/css"/>
<link href="../css/showDialog.css" rel="stylesheet" type="text/css"/>
<link href="../css/mypagination.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>


</head>
<body>
<!--#include file="../top.asp"-->
<div class="list_con">
<div class="con_ly"><h4 align="center"><img src="../images/20130807.jpg" width="556" height="503" border="0" usemap="#Map" />
<map name="Map" id="Map"><area shape="rect" coords="191,235,353,321" href="#a01" />
</map></h4>
  <div id="data_body">  </div>
  <div id="data_page" style="text-align:right;">  </div>

  </div>

<div class="con_ly_bor"><h4><a name="a01" id="a01"></a>发表留言</h4>
	<div class="con_ly_bor_name">
		<form method="post" action="saveFeedback.asp">
  			<table width="100%" border="0" cellspacing="0" cellpadding="3">
			    <tr>
			      <td width="25%"><div align="right">标题：</div></td>
			      <td width="60%"><input name="title" type="text" id="title" size="58" /></td>
			    </tr>
			    <tr>
			      <td><div align="right">姓名：</div></td>
			      <td><input name="username" type="text" id="username" size="58" /></td>
			    </tr>
			    <tr>
			      <td><div align="right">联系方式：</div></td>
			      <td><input name="tel" type="text" id="tel" size="58" /></td>
			    </tr>
			    <tr>
			      <td valign="top"><div align="right">内容：</div></td>
			      <td><textarea name="content" id="content" cols="45" rows="5"></textarea></td>
			    </tr>
			    <tr>
			      <td valign="top">&nbsp;</td>
			      <td><input type="submit" name="button" id="button" value="发表" /></td>
			    </tr>
			</table>
  		</form>
  	</div>
</div>
<div style="width:1000px; margin:0 auto">  <!--#include file="../common/footer.asp"--></div>
  <div class="clear"></div>
</div>
<!--#include file="../bottom.asp"-->
<script type="text/javascript" src="../js/feedback.js"></script>
<script type="text/javascript" src="../js/jquery.mypagination.js"></script>
<script type="text/javascript" src="../js/showDialog.js"></script>

<script type="text/javascript" src="../js/jqDnR.js"></script>
<script>
$(document).ready(function(){
	$.readXML.getData("queryFeedback.asp","curpage=1");
});
</script>
</body>
</html>

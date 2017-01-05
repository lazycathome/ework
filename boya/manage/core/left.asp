<html>
<head>
<style type="text/css">
BODY {
	FONT-SIZE: 9pt;
	BACKGROUND: 799ae1;
	MARGIN: 0px;
}

.sec_menu {
	BORDER-RIGHT: white 1px solid;
	BACKGROUND: #d6dff7;
	OVERFLOW: hidden;
	BORDER-LEFT: white 1px solid;
	BORDER-BOTTOM: white 1px solid;
}

.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 10px;
	COLOR: #215dc6;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 10px;
	COLOR: #428eff;
	POSITION: relative;
	TOP: 2px
}

.style1 {
	color: #FF0000
}

table {
	font-size: 12px
}

A:link {
	color: #000066;
	text-decoration: none;
}

A:visited {
	color: 000066;
	text-decoration: none;
}

A:active {
	color: #DB6D00;
	text-decoration: none;
}

A:hover {
	color: #FF9900;
	text-decoration: none;
}
</style>
<title>999edu.net后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body link="#000000" vlink="#000000" alink="#FF0000">

<table cellspacing="0" cellpadding="0" width="158">
	<tr>
		<td valign="bottom" height="42"><img height="38"
			src="../../images/title.gif" width="158" border="0"></td>
	</tr>
	<tr><td style="color:red;text-align:center;font-size:14px;padding-top:5px;"></td></tr>
	<tr>
		<td class="menu_title" background="../../images/title_bg_quit.gif" height="30" align="center">
		<span> <a href="../logon.asp" target="_top"><b><font
			color="215DC6">注销登录</font></b></a><b><font color="215DC6">&nbsp;&nbsp;</font></b></span></td>
	</tr>
</table>
<script>
var he=document.body.clientHeight-105;
document.write("<div id=tt style=height:"+he+";overflow:hidden>");
function aa(Dir) {
	tt.doScroll(Dir);
	Timer = setTimeout('aa("' + Dir + '")', 100)
}//这里100为滚动速度
function StopScroll() {
	if (Timer != null)
		clearTimeout(Timer)
}

function initIt() {
	divColl = document.all.tags("DIV");
	for (i = 0; i < divColl.length; i++) {
		whichEl = divColl(i);
		if (whichEl.className == "child")
			whichEl.style.display = "none";
	}
}
function expands(el) {
	whichEl1 = eval(el + "Child");
	if (whichEl1.style.display == "none") {
		initIt();
		whichEl1.style.display = "block";
	} else {
		whichEl1.style.display = "none";
	}
}
var tree = 0;
function loadThreadFollow() {
	if (tree == 0) {
		document.frames["hiddenframe"].location.replace("LeftTree.asp");
		tree = 1;
	}
}

function showsubmenu(sid) {
	whichEl = eval("submenu" + sid);
	imgmenu = eval("imgmenu" + sid);
	if (whichEl.style.display == "none") {
		eval("submenu" + sid + ".style.display=\"\";");
		imgmenu.background = "../../images/menuup.gif";
	} else {
		eval("submenu" + sid + ".style.display=\"none\";");
		imgmenu.background = "../../images/menudown.gif";
	}
}

</script>

<table cellspacing="0" cellpadding="0" width="158">
<tr>
<td id="imgmenu1"class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(1)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>新闻栏目管理</span></td></tr>
<tr>
	<td id="submenu1">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../column/addArticles.asp" target="main">栏目添加</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../column/showArticlesList.html" target="main">栏目列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>
<tr>
<td id="imgmenu4" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(4)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>新闻管理</span></td></tr>
<tr>
	<td id="submenu4" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../articles/addArticles.asp" target="main">添加文章</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../articles/showArticlesList.html" target="main">文章列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>

<tr>
<td id="imgmenu2" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(2)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>家校互动栏目管理</span></td></tr>
<tr>
	<td id="submenu2" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../column/addClinic.asp" target="main">栏目添加</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../column/showClinicList.html" target="main">栏目列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>

<tr>
<td id="imgmenu5" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(5)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>家校互动管理</span></td></tr>
<tr>
	<td id="submenu5" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../articles/addClinic.asp" target="main">添加互动</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../articles/showClinicList.html" target="main">互动列表</a></td>
		</tr>
		<!--
		<tr>
			<td height="9"><a href="../solution/addClinic.asp" target="main">添加推荐课程</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../solution/showClinicList.html" target="main">推荐课程列表</a></td>
		</tr>
		-->
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>

<tr>
<td id="imgmenu3" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(3)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>课程设置</span></td></tr>
<tr>
	<td id="submenu3" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../column/addSolution.asp" target="main">栏目添加</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../column/showSolutionList.html" target="main">栏目列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>
<tr>
<td id="imgmenu6" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(6)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>课程设置管理</span></td></tr>
<tr>
	<td id="submenu6" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../articles/addSolution.asp" target="main">添加课程</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../articles/showSolutionList.html" target="main">课程列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>

<tr>
<td id="imgmenu7" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(7)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>学员展示管理</span></td></tr>
<tr>
	<td id="submenu7" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../articles/addStudent.asp" target="main">添加学员</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../articles/showStudentList.html" target="main">学员列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>

<tr>
<td id="imgmenu9" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(9)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>留言管理</span></td></tr>
<tr>
	<td id="submenu9" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../feedback/showFeedbackList.html" target="main">留言列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>
<tr>
<td id="imgmenu8" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(8)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>账户管理</span></td></tr>
<tr>
	<td id="submenu8" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../account/addAccount.asp" target="main">添加账户</a></td>
		</tr>
		<tr>
			<td height="9"><a href="../account/showAccountList.html" target="main">账户列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>
<!--
<tr>
<td id="imgmenu7" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(7)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>用户管理</span></td></tr>
<tr>
	<td id="submenu7" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../user/showUserList.html" target="main">用户列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>

<tr>
<td id="imgmenu9" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(9)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>留言管理</span></td></tr>
<tr>
	<td id="submenu9" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../feebook/showFeebookList.html" target="main">评论列表</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>
<tr>
<td id="imgmenu10" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(10)" onMouseOut="this.className='menu_title';"  class="menu_title" style="cursor: hand" background="../../images/menudown.gif" height="25">
<span>邮件管理</span></td></tr>
<tr>
	<td id="submenu10" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<div align="center">
	<table cellspacing="3" cellpadding="0" width="130">
		<tr>
			<td height="9"><a href="../mail/sysMail.asp" target="main">邮件发送配置</a></td>
		</tr>
	</table>
	</div>
	</div>
	<br>
	</td>
</tr>
-->
</table>
</body>
</html>

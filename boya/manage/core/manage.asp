<html>
<head>
<title>管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../../css/main.css">
<script>
	if (self != top) {
		top.location = self.location;
	}
	function switchSysBar() {
		if (switchPoint.innerText == 3) {
			switchPoint.innerText = 4
			document.all("frmTitle").style.display = "none";
		} else {
			switchPoint.innerText = 3
			document.all("frmTitle").style.display = "";
		}
	}
</script>
<style type="text/css">
.navPoint {
	COLOR: white;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE: 9pt
}

talbe {
	font-size: 12px
}
</style>
</head>
<body style="MARGIN: 0px" scroll=no
	onResize=javascript:parent.carnoc.location.reload()>

<table border="0" cellPadding="0" cellSpacing="0" height="100%"
	width="100%">
	<tr>
		<td align="middle" noWrap vAlign="center" id="frmTitle"><iframe
			frameBorder="0" id="carnoc" name="carnoc" scrolling=no src="left.asp"
			style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 170px; Z-INDEX: 2">
		</iframe></td>
		<td bgcolor="A4B6D7" style="WIDTH: 9pt">
		<table border="0" cellPadding="0" cellSpacing="0" height="100%">
			<tr>
				<td style="HEIGHT: 100%" onclick=
	switchSysBar();
><font style="font-size 9pt; cursor default; color #ffffff"> <br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<span class="navPoint" id="switchPoint" title="关闭/打开左栏">3</span><br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				</font>
				</td>
			</tr>
		</table>
		</td>
		<td style="WIDTH: 100%"><iframe frameBorder="0" id="main"
			name="main" scrolling="yes" src="welcome.asp"
			style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 1">
		</iframe></td>
		
	</tr>
</table>
</html>
<script>
	if (window.screen.width < '1024') {
		switchSysBar()
	}
</script>
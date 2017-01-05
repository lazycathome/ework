<!--#include file="../common.asp"-->
<%
dim nname, email, serverAddr, userName, userPwd
dim conn, rs

nname = ""
email = ""
serverAddr = ""
userName = ""
userPwd = ""

set conn = server.createObject("ADODB.Connection")
conn.open application("connWebsite")

set rs = conn.execute("SELECT * FROM SYSMAIL")

if not (rs.eof or rs.bof) then
	nname = rs("NAME")
	email = rs("EMAIL")
	serverAddr = rs("SERVER")
	userName = rs("USER")
	userPwd = rs("PWD")
end if

rs.close
set rs = nothing

conn.close
set conn = nothing
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>设置邮箱帐户</title>
<link rel="stylesheet" href="style.css" type="text/css">
<script type="text/javascript" src="../../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
</head>

<body>

<form action=sysMailer.asp method=POST name=sysMailForm onSubmit="return checkOfSysMailForm(this);">
<table border="1" width="558" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#E6E6E6" id=headerTable>
<tr><td><p><label>设置邮箱帐户</label></p></td></tr>
</table>

<table border="1" width="558" cellpadding="0" style="border-collapse: collapse" bordercolor="#E6E6E6" id=loginTable>
	<tr>
		<td width="158" align="right"><label>姓名：</label></td>
		<td width="400"><input name="name" size="30" tabindex="1" value="<% = nname %>"></td>
	</tr>
	<tr>
		<td width="158" align="right"><label>邮箱地址：</label></td>
		<td width="400"><input name="email" size="48" tabindex="2" value="<% = email %>" onBlur="javascript:serverAddr(sysMailForm);"></td>
	</tr>
	<tr>
		<td width="158" align="right"><label>服务器：</label></td>
		<td width="400"><input name="server" size="30" tabindex="3" value="<% = serverAddr %>"></td>
	</tr>
	<tr>
		<td width="158" align="right"><label>用户名：</label></td>
		<td width="400"><input name="userName" size="30" tabindex="4" value="<% = userName %>"></td>
	</tr>
	<tr>
		<td width="158" align="right"><label>密码：</label></td>
		<td width="400">
		<input type="password" name="userPwd" size="30" tabindex="5" value="<% = userPwd %>"></td>
	</tr>
</table>

<table border="0" width="558" cellspacing="0" cellpadding="0" id=buttonTable>
	<tr>
		<td width="158"></td>
		<td width="400" style="padding: 12px 0 0 6px;">
		<input type="submit" value="保存设置" name="ok" tabindex="6">
		<input type="button" value="返回" name="cancel" tabindex="7" onClick="javascript:history.back();"></td>
	</tr>
</table>
</form>

<br>

</body>

</html>

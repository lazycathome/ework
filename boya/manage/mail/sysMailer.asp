<!--#include file="../common.asp"-->
<%
dim nname, email, serverAddr, userName, userPwd
dim conn, rs

nname = request.form("name")
email = request.form("email")
serverAddr = request.form("server")
userName = request.form("userName")
userPwd = request.form("userPwd")

set conn = server.createObject("ADODB.Connection")
conn.open application("connWebsite")

set rs = server.createObject("ADODB.Recordset")
rs.open "SELECT * FROM SYSMAIL", conn, 3, 3

if rs.eof or rs.bof then rs.AddNew
rs("NAME") = nname
rs("EMAIL") = email
rs("SERVER") = serverAddr
rs("USER") = userName
rs("PWD") = userPwd
rs.Update

rs.close
set rs = nothing

conn.close
set conn = nothing

response.redirect "../core/welcome.asp"
%>

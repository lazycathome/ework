<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")

dim id,username,password,password2,passwordnew,passwordnew1,des,roleId,groupId,display,sql
id = 			Trim(request.Form("id"))
username = 		Trim(request.Form("username"))
password = 		Trim(request.Form("password"))
password2 = 	Trim(request.Form("password2"))
passwordnew = 	Trim(request.Form("passwordnew"))
passwordnew1 = 	Trim(request.Form("passwordnew1"))
des = 			Trim(request.Form("description"))
display = 		Trim(request.Form("display"))

url = "showAccountList.html"

%>
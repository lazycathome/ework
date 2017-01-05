<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
Set rs=Server.CreateObject("Adodb.RecordSet")

dim id,userName,nickName,email,province,city,address,school,grade,phone,mobilePhone,qq,headUrl,lastTime,regTime,updateTime,display,sql
id = Trim(request("id"))
if isnull(id) or id = "" then
	id = 0
end if
sql = "SELECT * FROM USERS WHERE ID="&id
rs.open sql,conn,2,1
if not(rs.bof or rs.eof) then
	id = rs("ID")
	username = rs("USERNAME")
	nickName = rs("NICKNAME")
	email = rs("EMAIL")
	province = rs("PROVINCE")
	city = rs("CITY")
	address = rs("ADDRESS")
	school = rs("SCHOOL")
	grade = rs("GRADE")
	phone = rs("PHONE")
	mobilePhone = rs("MOBILEPHONE")
	qq = rs("QQ")
	headUrl = rs("HEADURL")
	lastTime = rs("LASTTIME")
	regTime = rs("REGTIME")	
	if rs("DISPLAY") = 0 then
		display = "¿ÉÓÃ"
	else
		display = "½ûÓÃ"
	end if		
	
end if
rs.close:set rs=nothing
conn.close:set conn=nothing
%>
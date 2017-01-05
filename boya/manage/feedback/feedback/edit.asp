<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
Set rs=Server.CreateObject("Adodb.RecordSet")

dim id,username,des,display,sql,display1
id = Trim(request("id"))
if isnull(id) or id = "" then
	id = 0
end if
sql = "SELECT * FROM FEEDBACK WHERE ID="&id
rs.open sql,conn,2,1
if not(rs.bof or rs.eof) then
	id = rs("ID")
	username = rs("USERNAME")
	title = rs("TITLE")
	reply = rs("REPLY")
	author = rs("AUTHOR")
	content = rs("CONTENT")
	tel = rs("TEL")
	regTime = rs("REGTIME")
	replyContent = rs("replyContent")
end if
rs.close:set rs=nothing
conn.close:set conn=nothing
%>
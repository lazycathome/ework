<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
Set rs=Server.CreateObject("Adodb.RecordSet")

dim id,columnName,des,display,parentId,typeId,sql
id = Trim(request("id"))
if isnull(id) or id = "" then
	id = 0
end if
sql = "SELECT * FROM COLUMNS WHERE ID="&id
rs.open sql,conn,2,1
if not(rs.bof or rs.eof) then
	id = rs("ID")
	columnName = rs("COLUMNNAME")
	des = rs("DESCRIPTION")
	if rs("DISPLAY") = 0 then
		display = "checked=""checked"""
	else
		display1 = "checked=""checked"""
	end if		
	
	parentId = rs("PARENTID")
	typeId = rs("TYPEID")
end if
rs.close:set rs=nothing
conn.close:set conn=nothing
%>
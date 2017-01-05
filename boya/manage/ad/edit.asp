<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
Set rs=Server.CreateObject("Adodb.RecordSet")

dim id,adName,typeId,typeNames,content,sql,type1,type2,type3
id = Trim(request("id"))
if isnull(id) or id = "" then
	id = 0
end if
sql = "SELECT * FROM AD WHERE ID="&id
rs.open sql,conn,2,1
if not(rs.bof or rs.eof) then
	id = rs("ID")
	adName = rs("ADNAME")	
	typeNames = rs("TYPENAME")
	content = rs("CONTENT")
	typeId = rs("TYPEID")
	if int(typeId) =1 then
		type1 = "checked=""checked"""
	elseif int(typeId)=2 then
		type2 = "checked=""checked"""
	elseif int(typeId)=3 then
		type3 = "checked=""checked"""
	end if
end if
rs.close:set rs=nothing
conn.close:set conn=nothing
%>
<!--#include file="../common.asp"-->
<%
dim typeId,columnId,columnName,regTime,isHot,isTop,content,title,summary,author,sources,url,sql,keyword,id,rs,conn

Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
set rs = server.CreateObject("ADODB.RecordSet")
id = Trim(request("id"))
source = Trim(request("source"))
if isnull(id) or id = "" then
	id = 0
end if

sql = "SELECT * FROM ARTICLES WHERE ID="&id

rs.open sql,conn,2,1
if not(rs.bof or rs.eof) then
	id = rs("ID")
	columnId = rs("COLUMNID")
	columnName = rs("COLUMNNAME")
	if rs("ISHOT") = 1 then
		isHot = "checked=""checked"""
	end if	
	if rs("ISTOP") = 1 then
		isTop = "checked=""checked"""		
	end if
	
	sources = rs("SOURCE")
	summary = rs("SUMMARY")
	content = rs("CONTENT")
	typeId = rs("TYPEID")
	subject = rs("SUBJECT")
	author = rs("AUTHOR")
	keyword = rs("KEYWORD")
	regtime = rs("REGTIME")
	description = rs("description")
end if
rs.close:set rs=nothing
conn.close:set conn=nothing
%>
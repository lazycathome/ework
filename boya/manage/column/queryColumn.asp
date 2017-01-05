<!--#include file="../common.asp"--->
<%
response.contentType = "application/xml"
response.Expires = 0 
response.write("<?xml version=""1.0"" encoding=""utf-8""?> ")


response.write("<columnList>")
curpage = Trim(request("curpage"))
typeId = Trim(request("typeId"))
isCurpage = Trim(request("isCurpage"))
if isnull(isCurpage) or isCurpage="" then
	isCurpage = 0
end if
if isnull(curpage) or curpage="" then
    curpage=1
else
    if int(curpage)<=0 then
	   curpage=1
	end if
end if

Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
Set rs=Server.CreateObject("Adodb.RecordSet")
 
dim totalcount,pagecount,curpage,sql

sql = "SELECT COUNT(*) FROM COLUMNS WHERE TYPEID="&typeId

rs.open sql,conn,0,1
totalcount=rs(0)
rs.close
if totalcount=0 then
    set rs=nothing
	conn.close:set conn=Nothing
	response.write("<itemPageSize>"& pagesize &"</itemPageSize>")
   response.write("<itemCount>0</itemCount>")
   response.write("</columnList>")
   response.end 
end if

pagecount=int(totalcount/pagesize)
if totalcount > pagecount*pagesize then
   pagecount=pagecount+1
end if

if int(curpage)>int(pagecount) then
   curpage=pagecount
 end If
 
response.write("<itemPageSize>"& pagesize &"</itemPageSize>")
response.write("<itemCount>"& totalcount &"</itemCount>")
response.write("<pageIndex>"& curpage &"</pageIndex>")
 
if int(isCurpage) <> 0 then
		sql= "select * from COLUMNS WHERE TYPEID="&typeId
else 
	if int(curpage)=1 then
		sql= "select top "& pagesize &" * from COLUMNS WHERE TYPEID="&typeId
	else
		sql= "select top "& pagesize &" * from COLUMNS where ID not in (select top "& (curpage-1)*pagesize &" ID from COLUMNS  WHERE TYPEID="&typeId&") "
	end if
end If
sql = sql&" ORDER BY REGTIME DESC"
response.write("<sql>"&sql&"</sql>")

rs.Open sql,conn,0,1
 
do while not rs.eof

	response.write("<column>")
	response.write("<id>"& rs("ID") &"</id>")
	response.write("<name>"& rs("COLUMNNAME") &"</name>")
	response.write("<parentId>"& rs("PARENTID") &"</parentId>")	
	response.write("<display>"& rs("DISPLAY") &"</display>")
	response.write("<regTime>"& rs("REGTIME") &"</regTime>")
	response.write("</column>")
rs.movenext
loop

rs.close:set rs=nothing
conn.close: set conn=Nothing
response.write("</columnList>")
response.End
%>
<!--#include file="../common/common.asp"--->
<%
dim totalcount,pagecount,curpage,sql
response.contentType = "application/xml"
response.Expires = 0 
response.write("<?xml version=""1.0"" encoding=""utf-8""?> ")


response.write("<feedbacks>")
curpage = Trim(request("curpage"))

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
Set rs = Server.CreateObject("Adodb.RecordSet")
 

sql = "SELECT COUNT(ID) FROM FEEDBACK "

rs.open sql,conn,0,1
totalcount=rs(0)
rs.close
if totalcount=0 then
    set rs=nothing
	conn.close:set conn=Nothing
	response.write("<itemPageSize>"& pageSize &"</itemPageSize>")
   response.write("<itemCount>0</itemCount>")
   response.write("</feedbacks>")
   response.end 
end if

pagecount=int(totalcount/pageSize)
if totalcount > pagecount*pageSize then
   pagecount=pagecount+1
end if

if int(curpage)>int(pagecount) then
   curpage=pagecount
 end If
 
response.write("<itemPagesize>"& pageSize &"</itemPagesize>")
response.write("<itemCount>"& totalcount &"</itemCount>")
response.write("<pageIndex>"& curpage &"</pageIndex>")
 
if int(isCurpage) <> 0 then
		sql= "select * from FEEDBACK"
else 
	if int(curpage)=1 then
		sql= "select top "& pageSize &" * from FEEDBACK"
	else
		sql= "select top "& pageSize &" * from FEEDBACK where ID not in (select top "& (curpage-1)*pageSize &" ID from FEEDBACK "
	end if
end If
sql = sql&" ORDER BY REGTIME DESC"
response.write("<sql>"&sql&"</sql>")

rs.Open sql,conn,0,1
 
do while not rs.eof

	response.write("<feedback>")
	response.write("<id>"& rs("ID") &"</id>")
	response.write("<username>"& rs("USERNAME") &"</username>")
	response.write("<title>"& rs("TITLE") &"</title>")	
	response.write("<reply>"& rs("reply") &"</reply>")
	response.write("<author>"& rs("AUTHOR") &"</author>")
	response.write("<regTime>"& rs("REGTIME") &"</regTime>")
	response.write("<lastTime>"& rs("REGTIME") &"</lastTime>")
	response.write("<tel>"& rs("TEL") &"</tel>")
	response.write("<ip>"& rs("IP") &"</ip>")
	response.write("<replyContent>"& rs("REPLYCONTENT") &"</replyContent>")
	response.write("</feedback>")
rs.movenext
loop

rs.close:set rs=nothing
conn.close: set conn=Nothing
response.write("</feedbacks>")
response.End
%>
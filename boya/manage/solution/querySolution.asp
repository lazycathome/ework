<!--#include file="../common.asp"--->
<%
response.contentType = "application/xml"
response.Expires = 0 
response.write("<?xml version=""1.0"" encoding=""utf-8""?> ")


response.write("<solutions>")
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

sql = "SELECT COUNT(*) FROM SOLUTION WHERE DISPLAY=0 AND TYPEID="&typeId

rs.open sql,conn,0,1
totalcount=rs(0)
rs.close
if totalcount=0 then
    set rs=nothing
	conn.close:set conn=Nothing
	response.write("<itemPageSize>"& pagesize &"</itemPageSize>")
   response.write("<itemCount>0</itemCount>")
   response.write("</solutions>")
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
		sql= "select * from SOLUTION WHERE DISPLAY=0 AND TYPEID="&typeId
else 
	if int(curpage)=1 then
		sql= "select top "& pagesize &" * from SOLUTION WHERE DISPLAY=0 AND TYPEID="&typeId
	else
		sql= "select top "& pagesize &" * from SOLUTION where ID not in (select top "& (curpage-1)*pagesize &" ID from SOLUTION  WHERE DISPLAY=0 AND TYPEID="&typeId&") "
	end if
end If
sql = sql&" ORDER BY REGTIME DESC"
response.write("<sql>"&sql&"</sql>")

rs.Open sql,conn,0,1
 
do while not rs.eof

	response.write("<solution>")
	response.write("<id>"& rs("ID") &"</id>")
	response.write("<columnId>"& rs("COLUMNID") &"</columnId>")
	response.write("<articleId>"& rs("ARTICLEID") &"</articleId>")
	response.write("<solutionName>"& rs("SOLUTIONNAME") &"</solutionName>")	
	response.write("<time>"& rs("TIMES") &"</time>")
	response.write("<expireTime>"& rs("EXPIRETIME") &"</expireTime>")	
	response.write("<type>"& rs("TYPE") &"</type>")	
	response.write("<money>"& rs("MONEYS") &"</money>")
	response.write("<price>"& rs("PRICE") &"</price>")
	response.write("<referral>"& rs("REFERRAL") &"</referral>")
	response.write("<url>"& rs("DESURL") &"</url>")
	response.write("<image>"& rs("IMAGEPATH") &"</image>")
	response.write("<isHot>"& rs("ISHOT") &"</isHot>")
	response.write("<isTop>"& rs("ISTOP") &"</isTop>")	
	response.write("<typeId>"& rs("TYPEID") &"</typeId>")	
	response.write("</solution>")
rs.movenext
loop

rs.close:set rs=nothing
conn.close: set conn=Nothing
response.write("</solutions>")
response.End
%>
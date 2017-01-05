<!--#include file="../common.asp"-->
<%
dim id,typeId,columnId,articleId,solutionName,times,expireTime,types,money,price,referral,url,image,regTime,isHot,isTop,display,sql,rs,conn

Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
set rs = server.CreateObject("ADODB.RecordSet")
id = Trim(request("id"))
if isnull(id) or id = "" then
	id = 0
end if

sql = "SELECT * FROM SOLUTION WHERE ID="&id

rs.open sql,conn,2,1
if not(rs.bof or rs.eof) then
	id = rs("ID")
	typeId = rs("TYPEID")
	columnId = rs("COLUMNID")
	articleId = rs("ARTICLEID")
	solutionName = rs("SOLUTIONNAME")
	times = rs("TIMES")
	expireTime = rs("EXPIRETIME")
	types = rs("TYPE")
	money = rs("MONEYS")
	price = rs("PRICE")
	referral = rs("REFERRAL")
	url = rs("DESURL")
	image = rs("IMAGEPATH")
	keyword = rs("KEYWORD")
	if rs("ISHOT") = 1 then
		isHot = "checked=""checked"""
	end if	
	if rs("ISTOP") = 1 then
		isTop = "checked=""checked"""		
	end if
	if columnId=0 then
		columnId = articleId
	end if
end if
rs.close:set rs=nothing
conn.close:set conn=nothing
%>
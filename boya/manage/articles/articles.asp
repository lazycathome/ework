<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")

dim typeId,columnId,columnName,regTime,isHot,isTop,content,subject,summary,author,sources,url,sql,keyword,id
typeId = Trim(request("typeId"))
columnId = Trim(request.Form("columnId"))
columnName = Trim(request.Form("columnName"))
regTime = Trim(request.Form("created"))
isHot = Trim(request.Form("isHot"))
isTop = Trim(request.Form("isTop"))
subject = Trim(request.Form("subject"))
content = Trim(request.Form("content"))
'summary = Trim(request.Form("summary"))
author = Trim(request.Form("author"))
sources = Trim(request.Form("source"))
id = Trim(request("id"))
keyword = Trim(request.Form("keyword"))
description = Trim(request.Form("imageUrl"))

if isNull(isHot) or isHot = "" then
	isHot = 0
end if

if isNull(isTop) or isTop = "" then
	isTop = 0
end if

if not isNull(typeId) and int(typeId)=1 then
	url = "showArticlesList.html"
else
	url = "showClinicList.html"
end if

%>
<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")

dim id,typeId,columnId,articleId,solutionName,times,expireTime,types,money,price,referral,desUrl,imagePath,regTime,isHot,isTop,display,sql
typeId = Trim(request("typeId"))
columnId = Trim(request.Form("columnId"))
articleId = Trim(request.Form("articleId"))
solutionName = Trim(request.Form("solutionName"))
times = Trim(request.Form("time"))
expireTime = Trim(request.Form("expireTime"))
money = Trim(request.Form("money"))
price = Trim(request.Form("price"))
referral = Trim(request.Form("referral"))
desUrl = Trim(request.Form("url"))
imagePath = Trim(request.Form("imageUrl"))
regTime = Trim(request.Form("created"))
isHot = Trim(request.Form("isHot"))
isTop = Trim(request.Form("isTop"))
id = Trim(request("id"))
types = Trim(request("type"))

if isNull(columnId) or columnId = "" then
	columnId = 0
end if


if isNull(articleId) or articleId = "" then
	articleId = 0
end if

if isNull(isHot) or isHot = "" then
	isHot = 0
end if

if isNull(isTop) or isTop = "" then
	isTop = 0
end if

if not isNull(typeId) and int(typeId)=1 then
	url = "showClinicList.html"
else
	url = "showSolutionList.html"
end if

%>
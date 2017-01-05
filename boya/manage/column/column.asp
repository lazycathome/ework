<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")

dim columnId,columnName,des,display,sql,id,url,typeId
columnId = 		Trim(request.Form("columnId"))
columnName = 	Trim(request.Form("name"))
des = 			Trim(request.Form("description"))
display = 		Trim(request.Form("display"))
id = 			Trim(request("id"))
typeId = 		Trim(request("typeId"))
if typeId=1 then
	url = "showArticlesList.html"
elseif typeId = 2 then
	url = "showClinicList.html"
elseif typeId = 3 then
	url = "showSolutionList.html"
end if

%>
<!--#include file="../common.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")

dim id,adName,typeId,typeNames,content,url,sql
id = 			Trim(request("id"))
adName = 		Trim(request.Form("adName"))
typeId = 		Trim(request.Form("typeId"))
typeNames = 	Trim(request.Form("typeName"))
content = 		Trim(request.Form("content"))
url = "showADList.html"


function createContent(typeId, content)
	dim myArray，str, length
	'如果是幻灯片需要裁减成固定格式
	if(int(typeId) =2) then
		myArray = split(content,"|")
		str = "var data = ["
		length = myArray.len
		for i = Lbound(myArray) to Ubound(myArray) 
			if not (i=length) then
				str = str&"{src:'"&myArray(i)&"'},"
			else
				str = str&"{src:'"&myArray(i)&"'}"
			end if
		next
		str = str&"];"
	end if
	createContent = content
end function
%>
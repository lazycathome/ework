<!--#include file="../common/common.asp"-->
<%
url = "index.asp"

content = replaceStr(request.Form("content"))
title = replaceStr(request.Form("title"))
tel = replaceStr(request.Form("tel"))
username = replaceStr(request.Form("username"))
ip = getUserIP()

%>
<%@LANGUAGE="VBSCRIPT" CODEPAGE="65001"%>
<%
dim pageSize,userKey,accountKey
'分页记录数
pageSize = 30
'用户加密的key
userKey = "9edu"
'管理账户加密
accountKey = "9#edu"

if(session("drmsysadmin")="") then
	'Response.Redirect "/manage/login.asp"
end if
%>
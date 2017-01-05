<!--#include file="account.asp"-->
<!--#include file="../../common/md5.asp"-->
<%
if password = password2 then

end if

response.Write(md5(password))
sql = "INSERT INTO ACCOUNTS(USERNAME,PWD,DESCRIPTION)VALUES('"&username&"','"&md5(accountKey&password)&"','"&des&"')"
conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
response.End()
%>
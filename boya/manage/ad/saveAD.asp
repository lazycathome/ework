<!--#include file="column.asp"-->
<%

sql = "INSERT INTO AD(ADNAME,TYPEID,TYPENAME,CONTENT)VALUES('"&adName&"',"&typeId&",'"&typeNames&"','"&content&"')"
conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
%>
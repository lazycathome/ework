<!--#include file="column.asp"-->
<%

sql = "INSERT INTO COLUMNS(TYPEID,COLUMNNAME,PARENTID,DISPLAY,DESCRIPTION)VALUES("&typeId&",'"&columnName&"',"&columnId&","&display&",'"&des&"')"
response.Write(sql)
conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
%>
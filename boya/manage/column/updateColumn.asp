<!--#include file="column.asp"-->
<%

sql = "UPDATE COLUMNS SET PARENTID="&columnId&",COLUMNNAME='"&columnName&"',DESCRIPTION='"&des&"',DISPLAY="&display&" WHERE ID="&id

conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
%>
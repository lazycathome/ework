<!--#include file="column.asp"-->
<%

sql = "UPDATE AD SET TYPEID="&typeId&",ADNAME='"&adName&"',TYPENAME='"&typeNames&"',content="&content&" WHERE ID="&id
conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
%>
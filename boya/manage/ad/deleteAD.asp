<!--#include file="column.asp"-->
<%

id = Trim(request("id"))
sql = "DELETE FROM AD WHERE ID="&id
conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
%>
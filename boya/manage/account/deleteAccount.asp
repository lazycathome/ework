<!--#include file="column.asp"-->
<%

id = Trim(request("id"))
sql = "UPDATE ACCOUNTS SET DISPLAY=1 WHERE ID="&id
conn.Execute(sql)
conn.close:set conn=nothing
response.Redirect(url)
%>
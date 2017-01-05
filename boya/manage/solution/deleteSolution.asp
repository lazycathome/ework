<!--#include file="solution.asp"-->
<%
sql = "UPDATE SOLUTION SET DISPLAY = 1 WHERE ID ="&id
conn.execute(sql)

conn.close:set conn = nothing
response.Redirect(url)
%>
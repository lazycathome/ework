<!--#include file="articles.asp"-->
<%
sql = "UPDATE ARTICLES SET COLUMNID="&columnId&", COLUMNNAME = '"&columnName&"', SUBJECT = '"&subject&"',CONTENT = '"&content&"', AUTHOR = '"&author&"', SOURCE = '"&sources&"', KEYWORD = '"&keyword&"', ISHOT = '"&isHot&"', ISTOP = '"&isTop&"', REGTIME = '"&regTime&"',TYPEID="&typeId&" WHERE ID ="&id
conn.execute(sql)

conn.close:set conn = nothing
response.Redirect(url)
%>
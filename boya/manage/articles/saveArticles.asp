<!--#include file="articles.asp"-->
<%
sql = "INSERT INTO ARTICLES(COLUMNID, COLUMNNAME, SUBJECT, CONTENT, AUTHOR, SOURCE, KEYWORD, ISHOT, ISTOP, TYPEID, REGTIME) VALUES ("&columnId&",'"&columnName&"','"&subject&"','"&content&"','"&author&"','"&sources&"','"&keyword&"',"&isHot&","&isTop&","&typeId&",'"&regTime&"')"

conn.execute(sql)

conn.close:set conn = nothing
response.Redirect(url)
%>
<!--#include file="solution.asp"-->
<%
sql = "INSERT INTO SOLUTION(COLUMNID, ARTICLEID, SOLUTIONNAME, TIMES, EXPIRETIME, TYPE, MONEYS, PRICE, REFERRAL, DESURL, IMAGEPATH, ISHOT, ISTOP, TYPEID) VALUES ("&columnId&","&articleId&",'"&solutionName&"','"&times&"','"&expireTime&"','"&types&"','"&money&"','"&price&"','"&referral&"','"&desUrl&"','"&imagePath&"',"&isHot&","&isTop&","&typeId&")"

response.Write(sql)
'response.End()

conn.execute(sql)

conn.close:set conn = nothing
response.Redirect(url)
%>
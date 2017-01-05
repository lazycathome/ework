<!--#include file="solution.asp"-->
<%
sql = "UPDATE SOLUTION SET COLUMNID="&columnId&", ARTICLEID="&articleId&", SOLUTIONNAME = '"&solutionName&"', TIMES = '"&times&"', EXPIRETIME = '"&expireTime&"', TYPE = '"&types&"', MONEYS = '"&money&"', PRICE = '"&price&"', REFERRAL = '"&referral&"', DESURL = '"&desUrl&"', IMAGEPATH = '"&imagePath&"', ISHOT = '"&isHot&"', ISTOP = '"&isTop&"' WHERE ID ="&id
conn.execute(sql)

conn.close:set conn = nothing
response.Redirect(url)
%>
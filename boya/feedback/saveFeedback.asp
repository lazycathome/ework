<!--#include file="feedback.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")

sql = "INSERT INTO FEEDBACK(USERNAME, TITLE, TEL, CONTENT, IP, REGTIME) VALUES('"&username&"', '"&title&"', '"&tel&"', '"&content&"', '"&ip&"', '"&now()&"')"

'sql = "INSERT INTO COMMENT(SUMMARY)VALUES('"&title&"')"
'response.Write(sql)
conn.Execute(sql)
conn.close:set conn=nothing
response.Write("<script>alert('留言成功');location.href='"&url&"';</script>")
response.End()
%>
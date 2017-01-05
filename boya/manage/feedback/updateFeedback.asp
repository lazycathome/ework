<!--#include file="feedback.asp"-->
<%
Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
sql = "UPDATE FEEDBACK SET REPLYCONTENT='"&replyContent&"',AUTHOR='"&session("drmsysadmin")&"', lastTime='"&now()&"', reply='是' WHERE ID="&id

'response.Write(sql)

conn.execute(sql)


conn.close:set conn=nothing
response.Write("<script>alert('回复成功');location.href='"&url&"';</script>")
response.End()
%>
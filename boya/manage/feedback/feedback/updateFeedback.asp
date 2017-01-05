<!--#include file="feedback.asp"-->
<%

set rs = server.CreateObject("ADODB.recordset")


existing = false
if not (rs.eof or rs.bof) then
	existing = true
	sql = "UPDATE FEEDBACK SET REPLYCONTENT='"&replyContent&"',AUTHOR='"&session("drmsysadmin")&"', lastTime='"&now()&"', reply='是' WHERE ID="&id
	conn.execute(sql)
end if

rs.close
set rs = nothing

conn.close:set conn=nothing
response.Write("<script>alert('修改成功');location.href='"&url&"';</script>")
response.End()
%>
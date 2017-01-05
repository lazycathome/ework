<!--#include file="account.asp"-->
<!--#include file="../../common/md5.asp"-->
<%
dim existing

sql = "SELECT * FROM ACCOUNTS WHERE USERNAME='"&username&"' and PWD = '"&md5(accountKey&password)&"'"

set rs = server.CreateObject("ADODB.recordset")
rs.open sql,conn,2,1

existing = false
if not (rs.eof or rs.bof) then
	existing = true
	sql = "UPDATE ACCOUNTS SET PWD='"&md5(accountKey&passwordNew)&"',DESCRIPTION='"&des&"' WHERE USERNAME='" & userName & "' AND PWD='" & md5(accountKey&password) & "' AND ID="&id
	conn.execute(sql)
end if

rs.close
set rs = nothing

conn.close:set conn=nothing
if not existing then 
	response.Write("<script>alert('修改失败请重试');location.href='"&url&"';</script>")
else
	response.Write("<script>alert('修改成功');location.href='"&url&"';</script>")
end if 
response.End()
%>
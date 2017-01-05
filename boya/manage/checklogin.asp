<!--#include file="../common/md5.asp"-->
<%
'管理账户加密
accountKey = "9#edu"
usr=Request.Form("usr")
psd=request.form("psd")
'response.Write(usr)

usr=replace(usr,"'","")
psd=replace(psd,"'","")

Set conn = Server.CreateObject("ADODB.Connection")
conn.Open application("connWebsite")
Set rs = Server.CreateObject("Adodb.RecordSet")

sql = "select id from ACCOUNTS where username='"& usr &"' and PWD='"& md5(accountKey&psd) &"'"

response.Write(sql)

rs.Open sql,conn,2,1

if rs.EOF then
  rs.Close:set rs=nothing
  conn.Close:set conn=nothing
  response.Write("<center><br><br><br>用户名或密码错误！<a href=javascript:history.go(-1)>返回</a></center>")
  response.End 

end if

  rs.Close:set rs=nothing
  conn.Close:set conn=nothing
  
session("drmsysadmin")=usr
Response.Redirect"core/manage.asp?"& time()
 
%>
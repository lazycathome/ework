<!--#include file="../common/common.asp"-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博雅课程设置</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
dim content, subject, regtime
id = trim(request("id"))
id = IIf(IsNumeric(id), id, 0)
if id = 0 then
  response.Redirect("/")
  response.end
end if 
getAticleContent(id)
%>
<!--#include file="../top.asp"-->
<div class="list_con">
  <!--#include file="../common/left.asp"-->
  <div class="list_con_right">
    <h4><img src="../images/boya_24.jpg" width="17" height="19" /><a href="/">首页</a>&gt;<a href="/newdynamic">最新动态</a>&gt;<%= subject %></h4>
    <div class="list_con_right_pad">
        <h3><%= subject %></h3>
        <%= content %>
        <p class="r">博雅学习中心<br />
        <%= regtime %></p>
   </div>

  </div>
  <div class="clear"></div>

  <!--#include file="../common/footer.asp"-->
  <div class="clear"></div>
</div>
<!--#include file="../bottom.asp"-->
</body>
</html>

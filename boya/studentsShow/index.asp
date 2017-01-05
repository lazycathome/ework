<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学员展示</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--#include file="../top.asp"-->
<!--#include file="queryStudent.asp"-->
<div class="list_con">
  <div class="con_zs">
    <h4 align="center"><img src="../images/boya_03.jpg" width="996" height="47" /></h4>
    <ul>
      asdfasdfasdf
      <%= getStudents(99, 8) %>
      
      <div class="clear"></div>
    </ul>
  </div>
  
  <%= getStudents(100, 5) %>


  <div class="clear"></div>

  <!--#include file="../common/footer.asp"-->
  <div class="clear"></div>
</div>
<!--#include file="../bottom.asp"-->
</body>
</html>

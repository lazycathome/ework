<!--#include file="../common/common.asp"-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博雅首页</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--#include file="../top.asp"-->
<div class="list_con">
  <!--#include file="../common/left.asp"-->
  <div class="list_con_right">
    <h4><img src="../images/boya_24.jpg" width="17" height="19" /><a href="#">首页</a>&gt;<a href="#">最新动态</a></h4>
    <div>
      <ul>
        <li>
          <%= getAticleList("index.asp", 1, currpage) %>
          
        </li>
      </ul>
    </div>
    <h3>&nbsp;</h3>
  </div>
  <div class="clear"></div>
  <!--#include file="../common/footer.asp"-->
</div>
<!--#include file="../bottom.asp"-->
</body>
</html>

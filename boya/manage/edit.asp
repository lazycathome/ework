<%@LANGUAGE="VBSCRIPT" CODEPAGE="936"%>
<!--#include file="FCKeditor/fckeditor.asp" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
</head>

<body>

<form id="form1" name="form1" method="post" action="">

<% 

 Dim oFCKeditor
 Set oFCKeditor = New FCKeditor
 oFCKeditor.BasePath = "/FCKeditor/"  '设置编辑器的路径，我站点根目录下的一个目录
 
 oFCKeditor.Config("SkinPath") = "/FCKeditor/editor/skins/silver/" '这行去掉为默认样式 还可以把silver换成 office2003样式
 oFCKeditor.ToolbarSet = "Default"   '默认工具栏   后台自己用
 'oFCKeditor.ToolbarSet = "Basic"    '基础工具栏   用户页面用 比较安全 
 oFCKeditor.Width = "100%" '宽度
 oFCKeditor.Height = "600" '高度

 oFCKeditor.Value = "初始值" '这个是给编辑器初始值
 oFCKeditor.Create "logbody" '以后编辑器里的内容都是由这个logbody取得，命名由你定

 %>
<input type="submit" name="Submit" value="提交" />
</form>
<br />
<%=request("logbody")%></body>
</html>

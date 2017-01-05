<!--#include file="FCKeditor/fckeditor.asp" -->
<% 


Function createFckEditor(FCKcontent, FCKname, height, width, toolbar)
 Set oFCKeditor = New FCKeditor
 oFCKeditor.BasePath = "/manage/fckeditor/"  '设置编辑器的路径，我站点根目录下的一个目录
 
 oFCKeditor.Config("SkinPath") = "/manage/fckeditor/editor/skins/silver/" '这行去掉为默认样式 还可以把silver换成 office2003样式
 oFCKeditor.ToolbarSet = toolbar
 'oFCKeditor.ToolbarSet = "Default"   '默认工具栏   后台自己用
 'oFCKeditor.ToolbarSet = "Basic"    '基础工具栏   用户页面用 比较安全 
 'oFCKeditor.Width = "100%" '宽度
 'oFCKeditor.Height = "600" '高度
 oFCKeditor.Width = width '宽度
 oFCKeditor.Height = height '高度

 oFCKeditor.Value = FCKcontent '这个是给编辑器初始值
 oFCKeditor.Create FCKname '以后编辑器里的内容都是由这个FCKname取得，命名由你定
 
End Function

'createFckEditor "123","content",600,700,"Default" '调用方式
%>
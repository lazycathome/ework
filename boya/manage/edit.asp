<%@LANGUAGE="VBSCRIPT" CODEPAGE="936"%>
<!--#include file="FCKeditor/fckeditor.asp" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ޱ����ĵ�</title>
</head>

<body>

<form id="form1" name="form1" method="post" action="">

<% 

 Dim oFCKeditor
 Set oFCKeditor = New FCKeditor
 oFCKeditor.BasePath = "/FCKeditor/"  '���ñ༭����·������վ���Ŀ¼�µ�һ��Ŀ¼
 
 oFCKeditor.Config("SkinPath") = "/FCKeditor/editor/skins/silver/" '����ȥ��ΪĬ����ʽ �����԰�silver���� office2003��ʽ
 oFCKeditor.ToolbarSet = "Default"   'Ĭ�Ϲ�����   ��̨�Լ���
 'oFCKeditor.ToolbarSet = "Basic"    '����������   �û�ҳ���� �Ƚϰ�ȫ 
 oFCKeditor.Width = "100%" '���
 oFCKeditor.Height = "600" '�߶�

 oFCKeditor.Value = "��ʼֵ" '����Ǹ��༭����ʼֵ
 oFCKeditor.Create "logbody" '�Ժ�༭��������ݶ��������logbodyȡ�ã��������㶨

 %>
<input type="submit" name="Submit" value="�ύ" />
</form>
<br />
<%=request("logbody")%></body>
</html>

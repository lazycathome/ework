<!--#include file="FCKeditor/fckeditor.asp" -->
<% 


Function createFckEditor(FCKcontent, FCKname, height, width, toolbar)
 Set oFCKeditor = New FCKeditor
 oFCKeditor.BasePath = "/manage/fckeditor/"  '���ñ༭����·������վ���Ŀ¼�µ�һ��Ŀ¼
 
 oFCKeditor.Config("SkinPath") = "/manage/fckeditor/editor/skins/silver/" '����ȥ��ΪĬ����ʽ �����԰�silver���� office2003��ʽ
 oFCKeditor.ToolbarSet = toolbar
 'oFCKeditor.ToolbarSet = "Default"   'Ĭ�Ϲ�����   ��̨�Լ���
 'oFCKeditor.ToolbarSet = "Basic"    '����������   �û�ҳ���� �Ƚϰ�ȫ 
 'oFCKeditor.Width = "100%" '���
 'oFCKeditor.Height = "600" '�߶�
 oFCKeditor.Width = width '���
 oFCKeditor.Height = height '�߶�

 oFCKeditor.Value = FCKcontent '����Ǹ��༭����ʼֵ
 oFCKeditor.Create FCKname '�Ժ�༭��������ݶ��������FCKnameȡ�ã��������㶨
 
End Function

'createFckEditor "123","content",600,700,"Default" '���÷�ʽ
%>
<% OPTION EXPLICIT %>
<!--#include file="upload.inc"-->
<%
dim upload, fileName

set upload = new upload_5xSoft

fileName = server.mapPath(upload.form("romoteFile"))
upload.file("localFile").saveAs fileName

set upload = nothing
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>上传成功</title>
<script language=javascript>
function init()
{
	parent.document.getElementById('uploadFileHere').style.display = 'none';
}
</script>
</head>

<body onload="init();"></body>

</html>

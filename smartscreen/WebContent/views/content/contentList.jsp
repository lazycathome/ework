<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>内容列表</title>
<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.js" type="text/javascript"></script>

</head>
<body>
<div class="container">
	<table class="table table-striped table-bordered">
		<c:forEach items="${contentList }" var="content" step="1">
			<tr>
				<td>${content.id }</td>
				<td>${content.name }</td>
				
				<td>
					<c:if test="${fn:contains(content.templateInfo,'.jpg')}">
						<img src="../downLoad/${content.templateInfo}" style="width:50px;heigth:50px;" />
					</c:if>
					<c:if test="${!fn:contains(content.templateInfo,'.jpg')}">
						${content.templateInfo}"
					</c:if>
				</td>
				<td>${content.showlevel }</td>
				<td>${content.category }</td>
				<td><a href="edit?id=${content.id }" class="btn btn-info">编辑</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
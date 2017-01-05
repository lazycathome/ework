<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form action="update" method="post" class="form" enctype="multipart/form-data">
			<div class="form-group">
				<label for="category" class="control-lable">展示内容屏幕类型：</label>
				<div class="radio">
				  <label>
				    <input type="radio" name="category" id="optionsRadios1" value="widthScreen" checked>
				   宽屏
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="category" id="optionsRadios2" value="heightScreen" >
				    竖屏
				  </label>
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="control-lable">展示内容类型：</label>
				<div class="radio">
				  <label>
				    <input type="radio" name="type" id="optionsRadios3" value="single" checked>
				   单屏
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="type" id="optionsRadios4" value="inside">
				    画中画
				  </label>
				</div>
			</div>
			<div>
				<input type="hidden" value="${content.id }" name="id">
				<label>展示内容标题：</label>
				<input type="text" name="name" value="${content.name }"/><br>
				<label>作者：</label><input type="text" name="author" value="${content.author }"/><br>
				<label>背景音乐地址：</label><input type="text" style="width:300px;" name="audioUrl" value="${content.audioUrl }" /><br>
				
				<label>展示等级：</label><input type="text" name="showlevel" value="${content.showlevel }"/><br>
				<label>内容标签：</label><input type="text" name="lable" value="${content.lable }"/><br>
				<label>图片：</label><input  type="file" id="imgFile" name="upload" onchange="showImag(this)"/><br>
				<div>
					<img alt="背景图片" src="../downLoad/${content.templateInfo}" style="width:100px;height:100px">
				</div>
				
			</div>
			<div>
				<input type="submit" value="提交"/><br>
			</div>
		</form>
		
	</div>
</body>
</html>
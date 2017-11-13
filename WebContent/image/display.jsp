<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>display_image</title>
<!-- 为了使页面美观，决定使用boostrap框架 -->
<script type = "text/javascript" src = "${pageContext.request.contextPath }/static/jquery/jquery-1.12.1.js"></script>
<script type = "text/javascript" src = "${pageContext.request.contextPath }/static/bootstrap/bootstrap.min.js"></script>
<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/static/bootstrap/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			
		
			<div class="col-md-6">
			
				<table class="table col-md-6">
				<tr><td>图片名称：</td><td>${image.name}</td></tr>
				<tr><td>图片：</td><td><img  class="img-rounded img-responsive" src= "${image.path }" style="width:234;height: 131px;"></td></tr>
				<tr><td><a href = "../index.jsp">继续提交图片</a></td></tr>
			</table>
			</div>
		
			<div class="col-md-6">
			
			</div>
		</div>
	</div>
	
</body>
</html>
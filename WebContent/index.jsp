<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>commit_image</title>
<!-- 为了使页面美观，决定使用boostrap框架 -->
<script type = "text/javascript" src = "${pageContext.request.contextPath }/static/jquery/jquery-1.12.1.js"></script>
<script type = "text/javascript" src = "${pageContext.request.contextPath }/static/bootstrap/bootstrap.min.js"></script>
<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/static/bootstrap/bootstrap.min.css">
</head>
<body>	
	<!-- 我们需要有个一个表单，里面只包含上传图片的控件就行了 -->
	<div class="container">
		<h1 class="text-center text-info">上传图片</h1>
		<!-- 开始写表单控件 -->
		<!-- 只有把表单的数据设置为二进制之后才可以上传图片、文件。 -->
		<form enctype="multipart/form-data"
		 id="form1" role = "form" method="post" action = "${pageContext.request.contextPath }/servlet/imageServlet">
		 	<!-- 表单控件一般放在一个类为form-group的div里 ,并且让它占据12格-->
		 	<div class="form-group col-md-12">
		 		<label for="name" class="col-md-2 control-label">图片名称：</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="name" id="name">
				</div>
		 	</div>
		 	<div class="form-group col-md-12">
		 		<!-- 占据两格，并且用了label控件类 -->
		 		<!-- label的for属性绑定的事控件的id -->
		 		<label for="image" class="col-md-2 control-label">图片上传：</label>
				<div class="col-md-8">
					<!-- 把input的类型设为file用于文件上传 -->
					<input type="file" class="form-control" name="image" id="image">
				</div>
				<!-- 这里还需要一个按钮来提交图片 -->
				<div class="col-md-2">
					<button type="submit" class="btn btn-primary btn-sm">提交</button>
				</div>
		 	</div>
			
		 </form>
	</div>
</body>
</html>
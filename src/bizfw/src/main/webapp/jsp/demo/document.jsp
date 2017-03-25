<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>文件上传</title>
<link rel="stylesheet" type="text/css" href="js/include/webuploader.css">

<script type="text/javascript" src="js/include/angular.js"></script>
<script type="text/javascript" src="js/include/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/include/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/demo/document.js"></script>
<script type="text/javascript" src="js/include/webuploader.js"></script>
</head>

<body>
	<div ng-app="app" ng-controller="myCtrl">
		<div id="thelist" class="uploader-list"></div>
		<form id="form" enctype="multipart/form-data">
			上传文件：<input type="file" name="file" id="file"><br />
			<input type="button" value="提交" ng-click="upload()"/>
		</form>
	</div>
</body>
</html>

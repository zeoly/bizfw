<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>
<script src="js/include/angular.js"></script>
<script src="js/include/jquery-3.1.1.js"></script>
<script src="js/demo/register.js"></script>
</head>

<body>
	<div ng-app="app" ng-controller="myCtrl">
		<form id="form">
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" ng-model="peopleInfo.code" name="peopleInfo.code"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="text" ng-model="peopleInfo.password" name="peopleInfo.password"></td>
				</tr>
				<tr>
					<td>机构</td>
					<td><input type="text" ng-model="peopleInfo.deptCode" name="peopleInfo.deptCode"></td>
				</tr>
				<tr>
					<td><input type="button" value="注册" ng-click="register()"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

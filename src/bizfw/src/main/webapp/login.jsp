<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<meta http-equiv="Pragma" content="no-cache"> 
	<meta http-equiv="Cache-Control" content="no-cache"> 
	<meta http-equiv="Expires" content="0"> 
	<title>BIM协同平台</title> 
	<script src="js/include/angular/angular1.5.8.js"></script>
	<script src="js/include/jquery/jquery-3.1.1.js"></script>
	<script src="js/common/common.js"></script>
	<script src="login.js"></script>
	<style type="text/css">
*{font:13px/1.5 '微软雅黑',Verdana,Helvetica,Arial,sans-serif;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;-box-sizing:border-box;padding:0;margin:0;list-style:none;box-sizing:border-box}
body,html{height:100%;overflow:hidden}
body{background:url(./img/bg1.jpg) no-repeat center;background-size:cover}
a{color:#27A9E3;text-decoration:none;cursor:pointer}
.login{margin:12% 15% auto auto;min-height:360px;max-width:360px;padding:40px;background-color:#ffffff;border-radius:10px;box-sizing:border-box}
a.logo{display:block;height:58px;width:167px;margin:0 auto 30px auto;background-size:167px 42px}
.message{margin:0 0 0 -58px;padding:13px 10px 13px 50px;background:#27A9E3;position:relative;color:#fff;font-size:24px}
#darkbannerwrap{background:url(./img/aiwrap.png);width:18px;height:10px;margin:0 0 10px -58px;position:relative}
input[type=text],input[type=file],input[type=password],input[type=email],select{border:1px solid #DCDEE0;vertical-align:middle;border-radius:3px;height:50px;padding:0px 16px;font-size:14px;color:#555555;outline:none;width:100%}
input[type=text]:focus,input[type=file]:focus,input[type=password]:focus,input[type=email]:focus,select:focus{border:1px solid #27A9E3}
a.act-but,a.submit{display:inline-block;vertical-align:middle;padding:12px 24px;margin:0px;font-size:18px;line-height:24px;text-align:center;white-space:nowrap;vertical-align:middle;cursor:pointer;color:#ffffff;background-color:#27A9E3;border-radius:3px;border:none;-webkit-appearance:none;outline:none;width:100%}
hr.hr15{height:15px;border:none;margin:0px;padding:0px;width:100%}
hr.hr20{height:20px;border:none;margin:0px;padding:0px;width:100%}
.copyright{font-size:14px;color:rgba(255,255,255,0.85);display:block;position:absolute;bottom:15px;right:15px}
	</style>
</head> 
<body> 
  <script> 
    var basePath="<%=basePath%>";
  </script> 
	<div class="login" ng-app="app" ng-controller="myCtrl">
		<div class="message">高山湾BIM协同平台登录</div>
		<div id="darkbannerwrap"></div>
		<form id="form" action="#" name="f" method="post">
			<input name="action" value="login" type="hidden">
			<input name="username" placeholder="用户名" required="" type="text">
			<hr class="hr15">
			<input name="password" placeholder="密码" required="" type="password">
			<hr class="hr15">
			<div><a class="act-but submit" ng-click="login()" style="width:100%;">登录</a></div>
			<hr class="hr20">
			<!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
		</form>


	</div>

</body>
</html>
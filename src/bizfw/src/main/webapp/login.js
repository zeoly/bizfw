var app = angular.module('app', []);
app.controller('myCtrl', function($scope, $http) {

	$scope.login = function() {
		$.ajax({
			url : basePath + "/loginAction/login.do",
			type : 'POST',
			async : false,
			data : $("#form").serialize(),
			success : function(data){
				if (data == "Y"){
					window.location.href = "index.jsp";
				}else{
					// 响应成功
					alert(data);
				}
			}
		});
	}
});
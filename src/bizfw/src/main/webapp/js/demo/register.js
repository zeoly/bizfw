var app = angular.module('app', []);
app.controller('myCtrl', function($scope, $http) {
	$scope.peopleInfo = new Object();
	$scope.peopleInfo.code = 'rrrr';
	$scope.peopleInfo.password = '123';
	$scope.peopleInfo.deptCode = '222';

	$scope.register = function() {
		$.ajax({
			url : "http://localhost:8080/bizfw/po/registerAction_register.action",
			type : 'POST',
			data : $("#form").serialize(),
			success : function(data){
				// 响应成功
				alert(data);
			},
			error : function(data){
				// 处理响应失败
				alert('fail');
			}
		});
	}
});
$(function () {
　　$.ajaxSetup({
　　　　// 请求失败遇到异常触发
	type : "POST",
	error : function(xhr, textStatus, errorThrown){
		var responseCode = xhr.status;
		if (500 == responseCode){
			var obj = JSON.parse(xhr.responseText);
			alert(obj.errorMsg);
		}
	}
　　});
});
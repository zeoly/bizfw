var app = angular.module('app', []);
app.controller('myCtrl', function($scope, $http) {
	
	var uploader = WebUploader.create({
		auto: false,
	    // swf文件路径
	    //swf: BASE_URL + '/js/incluede/Uploader.swf',
	    // 文件接收服务端。
	    server: 'http://localhost:8080/bizfw/document/documentAction_upload.action',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#file',
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false
	});
	
	uploader.on( 'fileQueued', function( file ) {
		$("#thelist").append( '<div id="' + file.id + '" class="item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<p class="state">等待上传...</p>' +
	    '</div>' );
	});
	
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress .progress-bar');

	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<div class="progress progress-striped active">' +
	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	          '</div>' +
	        '</div>').appendTo( $li ).find('.progress-bar');
	    }

	    $li.find('p.state').text('上传中');
 
	    $percent.css( 'width', percentage * 100 + '%' );
	});
	
	uploader.on( 'uploadSuccess', function( file ) {
		alert('succ');
	});
	
	uploader.on( 'uploadError', function( file ) {  
		alert('error');
	});
	
	$scope.webupload = function(){
		uploader.upload();
	};

	$scope.upload = function() {
        $.ajaxFileUpload({
            url: 'http://localhost:8080/bizfw/document/documentAction_upload.action', 
            type: 'post',
            secureuri: false, //一般设置为false
            fileElementId: 'file', // 上传文件的id、name属性名
            dataType: 'text', //返回值类型，一般设置为json、application/json
            success: function(data, status){  
                alert(data);
            },
            error: function(data, status, e){ 
                alert(e);
            }
        });
	};
});
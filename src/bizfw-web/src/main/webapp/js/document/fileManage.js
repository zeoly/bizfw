'use strict';

app.controller('FileCtrl', ['$scope', '$http', 'uiGridConstants', '$uibModal', '$log','Upload', '$timeout', function($scope, $http, uiGridConstants, $uibModal, $log,Upload, $timeout) {
  $scope.conlog = function(scope) {
    console.log(scope);
  };

  $scope.editFile = function(file){
	    if(file){
	    	file.editing = true;
	    }
	  };
  $scope.doneEditing = function(file){
	  	file.editing = false;
		    if (file.name!=file.modifyName) {
		    	file.name = file.modifyName;
		    	$scope.modifyFileName(file);
		    }
		  };
	  
  var fileSize = function(size) {
    if (size < 1024) {
      return size + " B";
    } else if (size < 1048576) {
      return (size / 1024).toFixed(2) + " KB";
    } else if (size < 1073741824) {
      return (size / 1048576).toFixed(2) + " MB";
    } else {
      return (size / 1073741824).toFixed(2) + " GB";
    }
  };

  $scope.openFile = function(file) {
    var url = $scope.getBaseUrl('/download' + file.url);
    window.location.href = url;
    //	  $.ajax({
    //	      url: $scope.getBaseUrl('/fileAction/downloadFile.do'),
    //	      async: false,
    //	      type: 'POST',
    //	      data:{
    //	    	  'fileId' : file.idBfDocument
    //	      },
    //	      success: function(data) {
    //	        alert('1');
    //	      }
    //	    });
  };

  // ZZJG_tree controller
  $scope.treeOptions = {
    accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;
    },
  };
  $scope.collhide = true;

  $scope.initAuthFolderTree = function() {
    $.ajax({
      url: $scope.getBaseUrl('/fileAction/getAuthFolderTree.do'),
      async: false,
      type: 'POST',
      success: function(data) {
        $scope.folderdata = [data];
      }
    });
  };
  $scope.initAuthFolderTree();

  $scope.getfileList = function(folder) {
	if (folder.type==1) {
		$scope.openFileHistoryDialog(folder);
	}else{
	    $scope.currFolder = folder;
	    $.ajax({
	      url: $scope.getBaseUrl('/fileAction/getContentOfFolder.do'),
	      async: false,
	      type: 'POST',
	      data: {
	        'folderId': folder.idBfDocument
	      },
	      success: function(data) {
	        //        $scope.conlog(data);
	        $scope.ffData = data;
	        for (var i = 0; i < $scope.ffData.length; i++) {
	          $scope.ffData[i].ext = fileSuffix($scope.ffData[i].extension);
	          $scope.ffData[i].modifyName = $scope.ffData[i].name;
	          if (!$scope.ffData[i].size) {$scope.ffData[i].fSize = '0 B'} else {
	          $scope.ffData[i].fSize = fileSize($scope.ffData[i].size);
	          }
	        }
	        console.log('complete');
	      }
	    });
	}
  };

  $scope.addFile = function(file, errFiles) {
      $scope.f = file;
      $scope.errFile = errFiles && errFiles[0];
            
      if (file) {
          file.upload = Upload.upload({
              url: $scope.getBaseUrl('/fileAction/addFile.do'),
              fields : {
            	  'folderId' : $scope.currFolder.idBfDocument
              },
              'file' : file
          });

          file.upload.then(function (response) {
              $timeout(function () {
            	  alert('上传成功！');
            	  $scope.getfileList($scope.currFolder);
            	  file.progress=null;
//                  file.result = response.data;
              });
          }, function (response) {
              if (response.status > 0)
                  $scope.errorMsg = response.status + ': ' + response.data;
          }, function (evt) {
              file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
          });
      }   
  };
  
  $scope.modifyFileName = function(item){
	  $.ajax({
	      url: $scope.getBaseUrl('/fileAction/modifyFile.do'),
	      async: false,
	      type: 'POST',
	      data: item,
	      success: function(data) {
	    	  alert("修改文件夹成功");
	      }
	  });
  };
  
  $scope.updateFile = function(file, errFiles, oldFile) {
      $scope.f = file;
      $scope.errFile = errFiles && errFiles[0];
      
      var fileName = file.name;
      var fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
      
      if (fileExtension != oldFile.extension){
    	  alert("新文件与原文件类型不同，请重新选择");
    	  return;
      }
      
      if (file) {
          file.upload = Upload.upload({
              url: $scope.getBaseUrl('/fileAction/updateFile.do'),
              fields : {
            	  'fileId' : oldFile.idBfDocument
              },
              'file' : file
          });

          file.upload.then(function (response) {
              $timeout(function () {
            	  alert('上传成功！');
            	  $scope.getfileList($scope.currFolder);
            	  file.progress=null;
//                  file.result = response.data;
              });
          }, function (response) {
              if (response.status > 0)
                  $scope.errorMsg = response.status + ': ' + response.data;
          }, function (evt) {
              file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
          });
      }
  };
  
  $scope.deleteFile = function(file){
	  if (confirm("确认删除文件?")) {
		  $.ajax({
		      url: $scope.getBaseUrl('/fileAction/deleteFile.do'),
		      async: false,
		      type: 'POST',
		      data: {
		    	  'documentId': file.idBfDocument
		      },
		      success: function(data) {
		    	  alert("删除文件成功");
		    	  $scope.getfileList($scope.currFolder);
		      }
		    });
	  }
  };
  
  $scope.openFileHistoryDialog = function(file) {
	var modalInstance = $uibModal.open({
		templateUrl: 'fileHistoryDialog',
		controller: 'FileHistoryDialogCtrl',
		scope: $scope,
		resolve: {
			file: function() {
				return file;
			}
		}
	});

	modalInstance.result.then(function(selectedItem) {
		//$scope.initDept();
	});
  };
  
  $scope.collapseAll = function() {
    $scope.$broadcast('angular-ui-tree:collapse-all');
    $scope.collhide = false;
  };
  $scope.expandAll = function() {
    $scope.$broadcast('angular-ui-tree:expand-all');
    $scope.collhide = true;
  };

  var fileSuffix = function(target) {
    var picSuffixArray = ['bmp', 'jpg', 'tiff', 'gif', 'pcx', 'tga', 'exif', 'fpx', 'svg', 'psd', 'cdr', 'pcd', 'dxf', 'ufo', 'eps', 'ai', 'raw', 'png'];
    var videoSuffixArray = ['mp4', '3gp', 'avi', 'mkv', 'wmv', 'mpg', 'vob', 'flv', 'swf', 'mov'];
    var audioSuffixArray = ['mp3', 'ogg', 'wav', 'ape', 'cda', 'au', 'midi', 'mac', 'aac'];
    var codeSuffixArray = ['html', 'css', 'js', 'jsp', 'php', 'xml', 'txt'];
    var docSuffixArray = ['doc', 'docx'];
    var pptSuffixArray = ['ppt', 'pptx'];
    var xlsSuffixArray = ['xls', 'xlsx'];
    var zipSuffixArray = ['rar', 'zip', '7z'];
    if (!target) {
      return 'fa-folder-o';
    }
    if (docSuffixArray.indexOf(target) != -1) {
      return 'fa-file-word-o';
    }
    if (xlsSuffixArray.indexOf(target) != -1) {
      return 'fa-file-excel-o';
    }
    if (pptSuffixArray.indexOf(target) != -1) {
      return 'fa-file-powerpoint-o';
    }
    if (picSuffixArray.indexOf(target) != -1) {
      return 'fa-file-image-o';
    }
    if (codeSuffixArray.indexOf(target) != -1) {
      return 'fa-file-text-o';
    }
    if (videoSuffixArray.indexOf(target) != -1) {
      return 'fa-file-video-o';
    }
    if (audioSuffixArray.indexOf(target) != -1) {
      return 'fa-file-audio-o';
    }
    if (zipSuffixArray.indexOf(target) != -1) {
      return 'fa-file-zip-o';
    }
    return 'fa-file-o';
  };

  $scope.placement = {
    options: [
      'top',
      'top-left',
      'top-right',
      'bottom',
      'bottom-left',
      'bottom-right',
      'left',
      'left-top',
      'left-bottom',
      'right',
      'right-top',
      'right-bottom'
    ],
    selected: 'top'
  };

}]);

app.controller('FileHistoryDialogCtrl', ['$scope', '$uibModalInstance', 'file', function($scope, $uibModalInstance, file) {
	
	  $.ajax({
	      url: $scope.getBaseUrl('/fileAction/getHistoryFiles.do'),
	      async: false,
	      type: 'POST',
	      data: {
	    	  'documentId' : file.idBfDocument
	      },
	      success: function(data) {
	    	  $scope.historyData = data;
	      }
	  });

	$scope.enterkey = function(eventkey) {
		var keycode = window.event ? eventkey.keyCode : eventkey.which;
		if (keycode == 13) {
			$scope.ok();
		}
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}]);

//# sourceURL=fileManage.js
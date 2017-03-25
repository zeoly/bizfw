'use strict';

app.controller('folderCtrl', ['$scope', '$http', '$uibModal', '$log', function($scope, $http, $uibModal, $log) {

  $scope.treeOptions = {
    accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;
    },
  };

  $scope.collapseAll = function() {
    $scope.$broadcast('angular-ui-tree:collapse-all');
    $scope.collhide = false;
  };
  $scope.expandAll = function() {
    $scope.$broadcast('angular-ui-tree:expand-all');
    $scope.collhide = true;
  };
  
  $scope.initRole = function() {
    $.ajax({
      url : $scope.getBaseUrl('/roleAction/getRoleList.do'),
      async : false,
      type : 'POST',
      success : function(data){
        $scope.roledata = data;
      }
    });
  };
  
  $scope.initRole();
  
   $scope.initFolderTree = function() {
       $.ajax({
           url: $scope.getBaseUrl('/folderAction/getAllFolderTree.do'),
           async: false,
           type: 'POST',
           success: function(data) {
               $scope.folderdata = [data];
           }
       });
   };
   
   $scope.initFolderTree();
  $scope.collhide = true;

  //JS层级初始化
 $scope.multiplerole = {};

  $scope.showFolderDetail = function(folder) {
    $scope.folder = folder;
    $.ajax({
	    url: $scope.getBaseUrl('/folderAction/getRoleOfFolder.do'),
	    type: 'POST',
	    async : false,
	    data: {
	      'folderId': folder.idBfDocument
	    },
	    success: function(data) {
	      $scope.role.selected = data;
	    }
	});
  };

  $scope.deleteFolder = function(folder) {
    if (confirm("确认删除文件夹[" + folder.name + "]?")) {
      $.ajax({
        url: $scope.getBaseUrl('/folderAction/deleteFolder.do'),
        type: 'POST',
        data: {
          'documentId': folder.idBfDocument
        },
        success: function(data) {
          alert('删除成功');
          $scope.initFolderTree();
        }
      });
    }
  };


  $scope.openFolderInfoDialog = function(folder, type) {
    var modalInstance = $uibModal.open({
      templateUrl: 'folderInfoDialog',
      controller: 'folderInfoDialogCtrl',
      scope: $scope,
      resolve: {
        folder: function() {
          return folder;
        },
	  	  type: function() {
			  return type;
		  }
      }
    });

    modalInstance.result.then(function(selectedItem) {
      $scope.initFolderTree();
    });
  };

  $scope.role = {};
  
  $scope.saveSelected = function() {
	  var selected = $scope.role.selected;
	  var idArray = [];
	  for (var i = 0; i < selected.length; i++){
		  idArray.push(selected[i].idBfRole);
	  }
	  $scope.folder.roleIdList = idArray;
      $.ajax({
	      url: $scope.getBaseUrl('/folderAction/setRoleOfFolder.do'),
	      type: 'POST',
	      data : {
	    	  'roleIdList' : idArray,
	    	  'folderId' : $scope.folder.idBfDocument
	    	  },
	      success: function(data) {
	        alert('设置成功');
	      }
	  });
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

app.controller('folderInfoDialogCtrl', ['$scope', '$uibModalInstance', 'folder', 'type', function($scope, $uibModalInstance, folder, type) {
  if (type == 1) {
    $scope.folder = folder;
  } else {
    $scope.folder = {};
  }

  $scope.enterkey = function(eventkey) {
    var keycode = window.event ? eventkey.keyCode : eventkey.which;
    if (keycode == 13) {
      $scope.ok();
    }
  };

  $scope.saveFolderInfo = function() {
    if (type == 0) {
      $scope.folder.ownerDocumentId = folder.idBfDocument;
      $.ajax({
        url: $scope.getBaseUrl('/folderAction/addFolder.do'),
        type: 'POST',
        data: $scope.folder,
        success: function(data) {
          $uibModalInstance.close($scope.folder);
          alert("添加文件夹成功");
        }
      });
    } else {
      $scope.folder.childList = [];
      $.ajax({
        url: $scope.getBaseUrl('/folderAction/modifyFolder.do'),
        type: 'POST',
        data: $scope.folder,
        success: function(data) {
          $uibModalInstance.close($scope.folder);
          alert("修改文件夹成功");
        }
      });
    }
  };

  $scope.cancel = function() {
    $uibModalInstance.dismiss('cancel');
  };

}]);
//# sourceURL=folderManage.js
'use strict';

app.controller('RoleCtrl', ['$scope', 'uiGridConstants', '$uibModal', '$log', function($scope, uiGridConstants, $uibModal, $log) {
	
	$scope.roledata = [];
	
	$scope.init = function() {
		$.ajax({
			url : $scope.getBaseUrl('/roleAction/getRoleList.do'),
			async : false,
			type : 'POST',
			success : function(data){
				$scope.roledata = data;
			}
		});
	};

  $scope.gridOptions = {
	data: 'roledata',
    enableRowSelection: false,
    multiSelect: false,
    enableSelectAll: false,
    gridMenuShowHideColumns: false,
    selectionRowHeaderWidth: 35,
    enableColumnMenus: false,
    rowHeight: 30,
    showGridFooter: true,
    paginationPageSizes: [10, 15, 20],
    paginationPageSize: 10
  };
  
  $scope.gridOptions.columnDefs = [{
    field: 'name',
    displayName: '角色名',
    width: '20%',
    enableSorting: false
  }, {
	field: 'description',
    displayName: '角色描述',
    width: '40%',
    enableSorting: false
  }, {
	field: 'createDate',
    displayName: '创建时间',
    width: '15%'
  }, {
	field: 'updateDate',
    displayName: '修改时间',
    width: '15%',
    sort: {
        direction: uiGridConstants.ASC,
        priority: 0,
    }
  }, {
	field: 'action',
    displayName: "操作",
    width: '*',
    enableSorting: false,
    cellTemplate: '<a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="修改信息" data-nodrag ng-click="grid.appScope.openRoleInfo(this.$parent.$parent.row.entity)"><span class="glyphicon glyphicon-pencil"></span></a><a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="删除人员" ng-click="grid.appScope.removeRole(this.$parent.$parent.row.entity)"><span class="glyphicon glyphicon-remove"></span></a>'
  }];

  $scope.MaximizemSize = function() {
    var newHeight = Math.floor(document.body.clientHeight - 180);
    angular.element(document.getElementsByClassName('mygrid')[0]).css('height', newHeight + 'px');
  };

  $scope.openRoleInfo = function(role) {
    var modalInstance = $uibModal.open({
      templateUrl: 'roleInfoDialog',
      controller: 'ModalInstCtrl',
      scope: $scope,
      resolve: {
        role: function() {
          return role;
        }
      }
    });
    modalInstance.result.then(function(selectedItem) {
      $scope.init();
    });
  };

  $scope.removeRole = function(node) {
	if (confirm("确认删除角色[" + node.name +"]?")){
	    $.ajax({
			url : $scope.getBaseUrl('/roleAction/deleteRole.do'),
			type : 'POST',
			async : false,
			data : {'roleId' : node.idBfRole},
			success : function(data){
				alert('删除成功');
				$scope.init();
			}
		});
	}
  };
  
  $scope.init();

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

app.controller('ModalInstCtrl', ['$scope', '$uibModalInstance', 'role', function($scope, $uibModalInstance, role) {
  if (role){
	$scope.role = role;
  }else{
	$scope.role = {};
  }
  
  $scope.enterkey = function(eventkey) {
    var keycode = window.event ? eventkey.keyCode : eventkey.which;
    if (keycode == 13) {
      $scope.ok();
    }
  };

  $scope.confirm = function() {
    if ($scope.role.idBfRole) {
    	var param = {
    		'name':$scope.role.name,
    		'description':$scope.role.description,
    		'idBfRole':$scope.role.idBfRole
    	};
    	$.ajax({
    		url : $scope.getBaseUrl('/roleAction/modifyRole.do'),
    		type : 'POST',
    		data : param,
    		success : function(data){
    			alert('修改成功!');
    		    $uibModalInstance.close($scope.role);
    		}
    	});
    } else {
    	$.ajax({
    		url : $scope.getBaseUrl('/roleAction/addRole.do'),
    		type : 'POST',
    		data : $scope.role,
    		success : function(data){
    			alert('添加成功!');
    		    $uibModalInstance.close($scope.role);
    		}
    	});
    };
  };

  $scope.cancel = function() {
    $uibModalInstance.dismiss();
  };

}]);
//# sourceURL=roleManage.js
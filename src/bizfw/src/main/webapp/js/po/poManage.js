'use strict';

app.controller('DeptCtrl', ['$scope', 'uiGridConstants', '$uibModal', '$log', function($scope, uiGridConstants, $uibModal, $log) {

	$scope.peopleData = [];

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

	$scope.initDept = function() {
		$.ajax({
			url: $scope.getBaseUrl('/departmentAction/getDepartmentTree.do'),
			async: false,
			type: 'POST',
			success: function(data) {
				$scope.data = [data];
			}
		});
	};

	$scope.initRole = function() {
		$.ajax({
			url: $scope.getBaseUrl('/roleAction/getRoleList.do'),
			type: 'POST',
			success: function(data) {
				$scope.roledata = data;
			}
		});
	};

	$scope.collhide = true;
	$scope.initDept();
	$scope.initRole();

	$scope.getPeopleList = function(dept) {
		$scope.currDept = dept;
		$.ajax({
			url: $scope.getBaseUrl('/peopleAction/getPeopleListByDepartment.do'),
			async: false,
			type: 'POST',
			data: {
				'departmentId': dept.idBfDepartment
			},
			success: function(data) {
				$scope.peopleData = data;
				console.log('complete');
			}
		});
	};

	$scope.deleteDept = function(dept) {
		if (confirm("确认删除机构[" + dept.name + "]?")) {
			$.ajax({
				url: $scope.getBaseUrl('/departmentAction/deleteDepartment.do'),
				type: 'POST',
				data: {
					'departmentId': dept.idBfDepartment
				},
				success: function(data) {
					alert('删除成功');
					$scope.initDept();
				}
			});
		}
	};
	$scope.MaximizemSize = function() {
		var newHeight = Math.floor(document.body.clientHeight - 200);
		angular.element(document.getElementsByClassName('mygrid')[0]).css('height', newHeight + 'px');
	};
	$scope.gridOptions = {
		data: 'peopleData',
		enableRowSelection: false,
		enableSelectAll: false,
		selectionRowHeaderWidth: 35,
		enableColumnMenus: false,
		rowHeight: 30,
		showGridFooter: true,
		paginationPageSizes: [10, 15, 20],
		paginationPageSize: 10
	};

	$scope.gridOptions.columnDefs = [{
		field: 'code',
		displayName: '用户名',
		width: '15%'
	}, {
		field: 'name',
		displayName: '姓名',
		width: '15%',
		enableSorting: false
	}, {
		field: 'status',
		displayName: '状态',
		width: '10%',
		cellTemplate: '<div class="ui-grid-cell-contents">{{grid.appScope.peopleStatus(this.$parent.$parent.row.entity.status)}}<div>',
		enableSorting: false
	}, {
		field: 'createDate',
		width: '20%',
		displayName: '创建时间'
	}, {
		field: 'updateDate',
		displayName: '修改时间',
		width: '20%',
		sort: {
			direction: uiGridConstants.ASC,
			priority: 0,
		}
	}, {
		field: 'action',
		displayName: "操作",
		width: '*',
		enableSorting: false,
		cellTemplate: '<a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="修改信息" data-nodrag ng-click="grid.appScope.openPeopleInfoDialog(this.$parent.$parent.row.entity)"><span class="glyphicon glyphicon-pencil"></span></a><a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="删除人员" ng-click="grid.appScope.deletePeople(this.$parent.$parent.row.entity)"><span class="glyphicon glyphicon-remove"></span></a><a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="解锁" ng-show="this.$parent.$parent.row.entity.status==2" ng-click="grid.appScope.unlockPeople(this.$parent.$parent.row.entity)"><span class="fa fa-unlock"></span></a>'
	}];
	
	$scope.peopleStatus = function(status){
			if (status == '0'){
				return "失效";
			} else if (status == '1'){
				return "正常";
			} else if (status == '2'){
				return "锁定";
			} else if (status == '3'){
				return "待审核";
			} else {
				return status;
			}
	};

	$scope.openDeptInfoDialog = function(dept, type) {
		var modalInstance = $uibModal.open({
			templateUrl: 'deptInfoDialog',
			controller: 'DeptInfoDialogCtrl',
			scope: $scope,
			resolve: {
				dept: function() {
					return dept;
				},
				type: function() {
					return type;
				}
			}
		});

		modalInstance.result.then(function(selectedItem) {
			$scope.initDept();
		});
	};

	$scope.openPeopleInfoDialog = function(people) {
		var modalInstance = $uibModal.open({
			templateUrl: 'peopleInfoDialog',
			controller: 'PeopleInfoDialogCtrl',
			scope: $scope,
			resolve: {
				people: function() {
					return people;
				}
			}
		});

		modalInstance.result.then(function(selectedItem) {
			$scope.getPeopleList($scope.currDept);
		});
	};

	$scope.deletePeople = function(people) {
		if (confirm("确认删除人员[" + people.name + "]?")) {
			$.ajax({
				url: $scope.getBaseUrl('/peopleAction/deletePeople.do'),
				type: 'POST',
				data: {
					'peopleId': people.idBfPeople
				},
				success: function(data) {
					alert('删除成功');
					$scope.getPeopleList($scope.currDept)();
				}
			});
		}
	};

	$scope.unlockPeople = function(people) {
		$.ajax({
			url: $scope.getBaseUrl('/peopleAction/unlockPeople.do'),
			type: 'POST',
			data: {
				'peopleId': people.idBfPeople
			},
			success: function(data) {
				alert('解锁成功');
				$scope.getPeopleList($scope.currDept);
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

app.controller('DeptInfoDialogCtrl', ['$scope', '$uibModalInstance', 'dept', 'type', function($scope, $uibModalInstance, dept, type) {
	if (type == 1) {
		$scope.department = dept;
	} else {
		$scope.department = {};
	}


	$scope.enterkey = function(eventkey) {
		var keycode = window.event ? eventkey.keyCode : eventkey.which;
		if (keycode == 13) {
			$scope.ok();
		}
	};

	$scope.saveDeptInfo = function() {
		if (type == 0) {
			$scope.department.parentDepartmentId = dept.idBfDepartment;
			$.ajax({
				url: $scope.getBaseUrl('/departmentAction/addDepartment.do'),
				type: 'POST',
				data: $scope.department,
				success: function(data) {
					$uibModalInstance.close($scope.department);
					alert("添加机构成功");
				}
			});
		} else {
			$scope.department.childDepartmentList = [];
			$.ajax({
				url: $scope.getBaseUrl('/departmentAction/modifyDepartment.do'),
				type: 'POST',
				data: $scope.department,
				success: function(data) {
					$uibModalInstance.close($scope.department);
					alert("修改机构成功");
				}
			});
		}
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}]);

app.controller('PeopleInfoDialogCtrl', ['$scope', '$uibModalInstance', 'people', function($scope, $uibModalInstance, people) {
	$scope.role = [];
	if (people) {
		$scope.people = people;
		$.ajax({
			url: $scope.getBaseUrl('/roleAction/getRoleOfPeople.do'),
			type: 'POST',
			async: false,
			data: {
				'peopleId': people.idBfPeople
			},
			success: function(data) {
				$scope.role.selected = data;
			}
		});
	} else {
		$scope.people = {};
	}


	$scope.enterkey = function(eventkey) {
		var keycode = window.event ? eventkey.keyCode : eventkey.which;
		if (keycode == 13) {
			$scope.ok();
		}
	};

	$scope.savePeopleInfo = function() {
		var roleList = $scope.role.selected;
		var roleIdArray = [];
		for (var i = 0; i < roleList.length; i++) {
			roleIdArray.push(roleList[i].idBfRole);
		}
		if (people) {
			$.ajax({
				url: $scope.getBaseUrl('/peopleAction/modifyPeople.do'),
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify({
					'people': $scope.people,
					'roleIdList': roleIdArray
				}),
				success: function(data) {
					$uibModalInstance.close($scope.people);
					alert("修改成功");
				}
			});
		} else {
			$scope.people.departmentId = $scope.currDept.idBfDepartment;
			$.ajax({
				url: $scope.getBaseUrl('/peopleAction/addPeople.do'),
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify({
					'people': $scope.people,
					'roleIdList': roleIdArray
				}),
				success: function(data) {
					$uibModalInstance.close($scope.people);
					alert("添加人员成功");
				}
			});
		}
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}]);
//# sourceURL=poManage.js
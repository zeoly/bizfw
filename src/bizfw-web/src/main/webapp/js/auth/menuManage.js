app.controller('menuCtrl', ['$scope', '$http', '$filter','uiGridConstants', '$uibModal', function($scope, $http, $filter,uiGridConstants, $uibModal) {

  $scope.treeOptions = {
    accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;
    },
  };
  
  $scope.initMenuList = function(){
	  $.ajax({
		url : $scope.getBaseUrl('/menuAction/getAllMenu.do'),
		async : false,
		type : 'POST',
		success : function(data){
			$scope.menudata = data;
			$scope.data = [data];
		}
	  });
  };
  
  $scope.initMenuList();
  
  $scope.showMenuDetail = function(menu){
	  $scope.parentMenu = getParent($scope.menudata, menu);
	  $scope.menu = menu;
  };
  
  $scope.inputNewMenu = function(menu) {
	  $scope.parentMenu = menu;
	  $scope.menu = {};
	  $scope.menu.parentMenuId = $scope.parentMenu.idBfMenu;
  };

  $scope.saveMenu = function() {
	  if ($scope.menu.idBfMenu){
		  $scope.menu.childList = [];
		  $.ajax({
			url : $scope.getBaseUrl('/menuAction/modifyMenu.do'),
			type : 'POST',
			data : $scope.menu,
			success : function(data){
				alert('修改成功');
				$scope.initMenuList();
			}
		  });
	  } else {
		  $.ajax({
			url : $scope.getBaseUrl('/menuAction/addMenu.do'),
			type : 'POST',
			data : $scope.menu,
			success : function(data){
				alert('添加成功');
				$scope.initMenuList();
			}
		  });
	  }
  };
  
  $scope.deleteMenu = function(node){
	  if (confirm("确认删除菜单[" + node.name +"]?")){
		  $.ajax({
			url : $scope.getBaseUrl('/menuAction/deleteMenu.do'),
			type : 'POST',
			data : {'menuId' : node.idBfMenu},
			success : function(data){
				alert('删除成功');
				$scope.initMenuList();
			}
		  });
	  }
  };

  $scope.collapseAll = function() {
    $scope.$broadcast('angular-ui-tree:collapse-all');
    $scope.collhide = false;
  };

  $scope.expandAll = function() {
    $scope.$broadcast('angular-ui-tree:expand-all');
    $scope.collhide = true;
  };

  function getParent(rootNode, target){
	  if (rootNode.idBfMenu == target.parentMenuId){
		  return rootNode;
	  } else {
		  var list = rootNode.childList;
		  for (var i = 0; i < list.length; i++){
			  var temp = getParent(list[i], target);
			  if (temp){
				  return temp;
			  }
		  }
	  }
  };


}]);
//# sourceURL=menuManage.js
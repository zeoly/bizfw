app.controller('privilegeCtrl', ['$scope', '$http', '$filter', function($scope, $http, $filter) {
	
	// 所有角色列表
	$scope.roledata = [];
	// 菜单树
	$scope.menudata = [];
	// 已选择菜单id列表
	$scope.selectedMenuId = [];

  // 插件引入
  $scope.treeOptions = {
    accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;
    },
  };
  
  $scope.initRolelist = function(){
	$.ajax({
		url : $scope.getBaseUrl('/roleAction/getRoleList.do'),
		async : false,
		type : 'POST',
		success : function(data){
			$scope.roledata = data;
		}
	});
  };
  
  $scope.initMenuTree = function() {
	  $.ajax({
		url : $scope.getBaseUrl('/menuAction/getAllMenu.do'),
		async : false,
		type : 'POST',
		success : function(data){
			$scope.menudata = [data];
		}
	  });
  };
  
  $scope.initRolelist();
  $scope.initMenuTree();

  /**
   * 读取角色已配置菜单并展示
   */
  $scope.showPrivilegeDetail = function(role) {
	$scope.role = role;
	$.ajax({
		url : $scope.getBaseUrl('/menuAction/getMenuOfRole.do'),
		async : false,
		data : {'roleId' : role.idBfRole},
		type : 'POST',
		success : function(data){
			$scope.selectedMenuId = [];
			$scope.setSelectedMenu($scope.menudata, data);
		}
	});
  };
  
  /**
   * 根据已关联菜单初始化机构树
   */
  $scope.setSelectedMenu = function(menulist, selectedList){
	  for (var i = 0; i < menulist.length; i++){
		  if ($scope.isContain(selectedList, menulist[i])){
			  menulist[i].isSelected = true;
			  $scope.selectedMenuId.push(menulist[i].idBfMenu);
		  } else {
			  menulist[i].isSelected = false;
		  }
		  if (menulist[i].childList){
			  $scope.setSelectedMenu(menulist[i].childList, selectedList);
		  }
	  }
  };
  
  /**
   * list中是否包含menu
   */
  $scope.isContain = function(list, menu){
	  for (var i = 0; i < list.length; i++){
		  if (menu.idBfMenu == list[i].idBfMenu){
			  return true;
		  }
	  }
	  return false;
  };

  $scope.inputNewMenu = function(scope) {
    $scope.menu = {};
  };

  /**
   * 保存角色关联菜单信息
   */
  $scope.saveSelectedMenu = function() {
	$.ajax({
		url : $scope.getBaseUrl('/menuAction/setMenuOfRole.do'),
		data : {
			'roleId' : $scope.role.idBfRole,
			'menuIdList' : $scope.selectedMenuId
		},
		type : 'POST',
		success : function(data){
			alert("保存成功");
		}
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

  $scope.isChecked = function(id) {
    return $scope.selected.indexOf(id) >= 0;
  };

  $scope.saveSelected = function(selected) {
    return $scope.selected.indexOf(selected) >= 0;
  };
  
  /**
   * 选择一个菜单后：1、更新已选择数组，2、如果为选中，联动所有父节点，3、联动所有子节点
   */
  $scope.updateMenuSelection = function(menu){
	  if (menu.isSelected){
		  $scope.selectedMenuId.push(menu.idBfMenu);
		  $scope.selectMenu(menu);
		  $scope.selectParentMenu(menu);
	  } else {
		  var idx = $scope.selectedMenuId.indexOf(menu.idBfMenu);
	      $scope.selectedMenuId.splice(idx, 1);
	      $scope.unSelectMenu(menu);
	  }
  };
  
  /**
   * 父子菜单联动选择
   */
  $scope.selectMenu = function(menu){
	  if (!menu.isSelected){
		  menu.isSelected = true;
		  $scope.selectedMenuId.push(menu.idBfMenu);
	  }
	  if (menu.childList && menu.childList.length > 0){
		  for (var i = 0; i < menu.childList.length; i++){
			  $scope.selectMenu(menu.childList[i]);
		  }
	  }
  };
  
  /**
   * 父子菜单联动反选
   */
  $scope.unSelectMenu = function(menu){
	  if (menu.isSelected){
		  menu.isSelected = false;
		  var idx = $scope.selectedMenuId.indexOf(menu.idBfMenu);
	      $scope.selectedMenuId.splice(idx, 1);
	  }
	  if (menu.childList && menu.childList.length > 0){
		  for (var i = 0; i < menu.childList.length; i++){
			  $scope.unSelectMenu(menu.childList[i]);
		  }
	  }
  };
  
  /**
   * 选中联动父菜单
   */
  $scope.selectParentMenu = function(menu){
	  if (menu.parentMenuId){
		  var parentMenu = $scope.getParentMenu($scope.menudata[0], menu);
		  if (!parentMenu.isSelected){
			  parentMenu.isSelected = true;
			  $scope.selectedMenuId.push(parentMenu.idBfMenu);
			  $scope.selectParentMenu(parentMenu);
		  }
	  }
  };
  
  /**
   * 获取父菜单
   */
  $scope.getParentMenu = function(root, menu){
	  if (root.idBfMenu == menu.parentMenuId){
		  return root;
	  } else {
		  if (root.childList && root.childList.length > 0){
			  for (var i = 0; i < root.childList.length; i++){
				  var temp = $scope.getParentMenu(root.childList[i], menu);
				  if (temp){
					  return temp;
				  }
			  }
		  }
	  }
  };

}]);
//# sourceURL=rolePrivilege.js
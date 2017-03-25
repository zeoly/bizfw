'use strict';

app.controller('menuCtrl', ['$scope', '$http', '$filter','uiGridConstants', '$uibModal', '$log', function($scope, $http,$filter, uiGridConstants, $uibModal, $log) {

  $http.get('js/common/app/contact/contacts.json').then(function(resp) {
    $scope.items = resp.data.items;
    $scope.item = $filter('orderBy')($scope.items, 'first')[0];
    $scope.item.selected = true;
  });
  $scope.filter = '';
  $scope.editItem = function(item) {
    if (item && item.selected) {
      item.editing = true;
    }
  };
  $scope.doneEditing = function(item){
    item.editing = false;
  };
  $scope.conlog = function(scope) {
    console.log(scope);
  };
  // ZZJG_tree controller
  $scope.treeOptions = {
    accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;
    },
  };
  $scope.collhide = true;
  $scope.remove = function(scope) {
    scope.remove();
  };
  $scope.newSubItem = function(scope, subItemName) {
    var nodeData = scope.$modelValue;
    nodeData.nodes.push({
      id: nodeData.id * 10 + nodeData.nodes.length,
      title: subItemName,
      nodes: []
    });
  };

  $scope.modifyItem = function(scope, subItemName) {
    $scope.conlog(scope);
    var nodeData = scope.$modelValue;
    nodeData.title = subItemName;
  };

  $scope.dept = [];
  var dept = [];
  $scope.newItem = function(scope) {
    var data = $scope.data;
    data.push({
      id: data[data.length - 1] + 1,
      title: scope,
      FileUrl: [],
      nodes: []
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

  // Load folders
  $http.get('./json/folderlist.json').success(function(data) {
    // 浏览器console端口打印读取的数据
    // console.log(data.folderlist);
    $scope.folderdata = data.folderlist;
  });


  // WJGL_grid controller
  $scope.gridOptions = {
    enableRowSelection: true,
    enableSelectAll: true,
    selectionRowHeaderWidth: 35,
    rowHeight: 25,
    showGridFooter: true,
  };
  $scope.gridOptions.multiSelect = true;
  $scope.info = {};
  $scope.gridOptions.columnDefs = [{
    name: 'fid',
    maxwidth: 100,
    displayName: '文件编号'
  }, {
    name: 'fname',
    displayName: '文件名称'
  }, {
    name: 'uptime',
    displayName: '上传时间'
  }, {
    name: 'cog',
    displayName: '操作',
    cellTemplate: '<a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="修改信息" data-nodrag ng-click="grid.appScope.addFile()"><span class="glyphicon glyphicon-pencil"></span></a><a class="pull-left btn-xs" tooltip-placement="top-right" uib-tooltip="删除人员" ng-click="grid.appScope.removeFirstRow()"><span class="glyphicon glyphicon-remove"></span></a>'
  }];


  $scope.addFile = function() {
    var n = $scope.gridOptions.data.length + 1;
    $scope.gridOptions.data.push({
      'fid': 'FileNo.' + n,
      'fname': '工程资料' + Math.floor(Math.random() * 100),
      'uptime': '初步设计文件',
      'cog': true
    });
  };
  $scope.removeFirstRow = function() {
    //if($scope.gridOpts.data.length > 0){
    $scope.gridOptions.data.splice(0, 1);
    //}
  };
  $scope.deleteClient = function(tempDeleteRowEntity) {
    for (var i = 0; i < $scope.gridOptions.data.length; i++) {
      if ($scope.gridOptions.data[i] === tempDeleteRowEntity) {
        $scope.gridOptions.data.splice(i, 1);
        break;
      }
    }
  };
  $scope.selectAll = function() {
    $scope.gridApi.selection.selectAllRows();
  };

  $scope.selectone = function() {
    $scope.gridApi.selection.selectRow($scope.gridOptions.data[0]);
  };

  $scope.getselect = function() {
    console.log($scope.gridApi.selection.getSelectedgridRows());
  };

  $scope.gridOptions.onRegisterApi = function(gridApi) {
    //set gridApi on scope
    $scope.gridApi = gridApi;
    gridApi.selection.on.rowSelectionChanged($scope, function(row) {
      var msg = 'row selected ' + row.isSelected;
      $log.log(msg);
    });

    gridApi.selection.on.rowSelectionChangedBatch($scope, function(rows) {
      var msg = 'rows changed ' + rows.length;
      $log.log(msg);
    });
  };

  var yygrid = $scope.folderdata;
  // $scope.gridOptions.data = yygrid[0].FileUrl;

  $scope.UrlFile = function(scope) {
    var nodeData = scope.$modelValue;
    $scope.gridOptions.data = nodeData.FileUrl;
    // $scope.conlog(nodeData);
  };

  //Modal Controller
  $scope.items = ['item1', 'item2', 'item3'];
  // $scope.items = ['item1', 'item2', 'item3'];
  $scope.openNewDept = function(size) {
    var modalInstance = $uibModal.open({
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstCtrl',
      size: size,
      resolve: {
        items: function() {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function(selectedItem) {
      $scope.newItem(selectedItem);
      $scope.dept.name = selectedItem;
    }, function() {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $scope.openNewSubDept = function(scope, size) {
    var nodeDataStorage = scope;
    var modalInstance = $uibModal.open({
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstCtrl',
      size: size,
      resolve: {
        items: function() {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function(selectedItem) {
      $scope.newSubItem(nodeDataStorage, selectedItem);
      $scope.dept.name = selectedItem;
    }, function() {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $scope.openmodifyDept = function(scope, size) {
    var nodeDataStorage = scope;
    var modalInstance = $uibModal.open({
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstCtrl',
      size: size,
      resolve: {
        items: function() {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function(selectedItem) {
      $scope.modifyItem(nodeDataStorage, selectedItem);
      $scope.dept.name = selectedItem;
    }, function() {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $scope.dynamicPopover = {
    content: 'Hello, World!',
    templateUrl: 'myPopoverTemplate.html',
    title: 'Title'
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

app.controller('ModalInstCtrl', ['$scope', '$uibModalInstance', 'items', function($scope, $uibModalInstance, items) {
  $scope.dept = [];
  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.enterkey = function(eventkey) {
    var keycode = window.event ? eventkey.keyCode : eventkey.which;
    if (keycode == 13) {
      $scope.ok();
    }
  };

  $scope.ok = function() {
    $uibModalInstance.close($scope.dept.name);
    // $uibModalInstance.close($scope.selected.item);
  };

  $scope.cancel = function() {
    $uibModalInstance.dismiss('cancel');
  };

  $scope.conlog = function() {
    console.log($scope);
  };
}]);
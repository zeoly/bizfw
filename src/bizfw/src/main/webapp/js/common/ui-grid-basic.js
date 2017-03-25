"use strict";

 app.controller('YYGL', ['$scope', 'uiGridConstants', function ($scope, uiGridConstants) {
  
  $scope.gridOptions = {
    enableRowSelection: true,
    enableSelectAll: true,
    selectionRowHeaderWidth: 35,
    rowHeight: 25,
    showGridFooter:true,
  };

  $scope.gridOptions.columnDefs = [
    { name: 'id', displayName:'账号'},
    { name: 'name', displayName:'姓名'},
    { name: 'department', displayName:'部门'},
    { name: 'job', displayName:'职务'},
    { name: 'cog', displayName:'操作'}
  ];

 $scope.addData = function() {
    var n = $scope.gridOptions.data.length + 1;
    $scope.gridOptions.data.push({
                'id': 'No.' + n,
                'name': 'Person' + Math.floor(Math.random()*100),
                'department': '综合部',
                'job': '工作人员',
                'cog': true
              });
  }; 
 
  $scope.gridOptions.multiSelect = true;
 
    $scope.info = {};

  $scope.removeFirstRow = function() {
    //if($scope.gridOpts.data.length > 0){
       $scope.gridOptions.data.splice(0,1);
    //}
  };

   $scope.selectAll = function() {
      $scope.gridApi.selection.selectAllRows();
    };

   $scope.selectone = function() {
      $scope.gridApi.selection.selectRow($scope.gridOptions.data[0]);
    };

    $scope.getselect = function() {
       console.log( $scope.gridApi.selection.getSelectedgridRows());
  };
 
 $scope.gridOptions.onRegisterApi = function(gridApi){
      //set gridApi on scope
      $scope.gridApi = gridApi;
      gridApi.selection.on.rowSelectionChanged($scope,function(row){
        var msg = 'row selected ' + row.isSelected;
        $log.log(msg);
      });
 
      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        var msg = 'rows changed ' + rows.length;
        $log.log(msg);
      });
    };

  var zhbdata = [
    {
      "id": "Cox",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    },
    {
      "id": "Cox",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    },
    {
      "id": "Cox",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    },
    {
      "id": "Cox",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    }
  ];


  var zgbdata = [
    {
      "id": "zgb",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    },
    {
      "id": "zgb",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    },
    {
      "id": "zgb",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    },
    {
      "id": "zgb",
      "name": "Carney",
      "department": "Enormo",
      "job": "male"
    }
  ];
  $scope.gridOptions.data=zhbdata;

  $scope.swapData = function() {
    if ($scope.gridOptions.data === zhbdata) {
      $scope.gridOptions.data = zgbdata;
    }
    else {
      $scope.gridOptions.data = zhbdata;
    }
  };


}]);




app.controller('MainCtrl', ['$scope', '$http', '$log', '$timeout', 'uiGridConstants', function ($scope, $http, $log, $timeout, uiGridConstants) {
  $scope.gridOptions = {
    enableRowSelection: true,
    enableSelectAll: true,
    selectionRowHeaderWidth: 35,
    rowHeight: 35,
    showGridFooter:true
  };
 
  $scope.gridOptions.columnDefs = [
    { name: 'id' },
    { name: 'name'},
    { name: 'age', displayName: 'Age (not focusable)', allowCellFocus : false },
    { name: 'address' }
  ];
 
  $scope.gridOptions.multiSelect = true;
 
    $scope.info = {};
 
 $scope.addData12 = function() {
    var n = $scope.gridOptions.data.length + 1;
    $scope.gridOptions.data.push({
                'id': 'No.' + n,
                'name': 'Person' + Math.floor(Math.random()*100),
                'age': '19',
                'address': '19st'
              });
  }; 

    $scope.toggleMultiSelect = function() {
      $scope.gridApi.selection.setMultiSelect(!$scope.gridApi.grid.options.multiSelect);
    };
 
    $scope.toggleModifierKeysToMultiSelect = function() {
      $scope.gridApi.selection.setModifierKeysToMultiSelect(!$scope.gridApi.grid.options.modifierKeysToMultiSelect);
    };
 
    $scope.selectAll = function() {
      $scope.gridApi.selection.selectAllRows();
    };
 
    $scope.clearAll = function() {
      $scope.gridApi.selection.clearSelectedRows();
    };
 
    $scope.toggleRow1 = function() {
      $scope.gridApi.selection.toggleRowSelection($scope.gridOptions.data[0]);
    };
 
    $scope.toggleFullRowSelection = function() {
      $scope.gridOptions.enableFullRowSelection = !$scope.gridOptions.enableFullRowSelection;
      $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
    };
 
    $scope.setSelectable = function() {
      $scope.gridApi.selection.clearSelectedRows();
 
      $scope.gridOptions.isRowSelectable = function(row){
        if(row.entity.age > 30){
          return false;
        } else {
          return true;
        }
      };
      $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
 
      $scope.gridOptions.data[0].age = 31;
      $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.EDIT);
    };
 
    $scope.gridOptions.onRegisterApi = function(gridApi){
      //set gridApi on scope
      $scope.gridApi = gridApi;
      gridApi.selection.on.rowSelectionChanged($scope,function(row){
        var msg = 'row selected ' + row.isSelected;
        $log.log(msg);
      });
 
      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        var msg = 'rows changed ' + rows.length;
        $log.log(msg);
      });
    };




}]);
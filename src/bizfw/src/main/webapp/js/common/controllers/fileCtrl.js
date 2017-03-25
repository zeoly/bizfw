'use strict';

    app.controller('FileCtrl', ['$scope', function ($scope) {
      
      //ui-tree module
      $scope.treeOptions = {
      accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;},
      };

      $scope.remove = function (scope) {
        scope.remove();
      };

      $scope.toggle = function (scope) {
        scope.toggle();
      };

      $scope.newSubItem1 = function (item) {  
            var name=window.prompt("请输入节点的名称");  
            if(name===""||name===undefined){  
                return;  
            }  
            if(item.orgTypeId===undefined){  
                item.orgTypeId=-1;  
            }  
//             var json={"level":parseInt(item.level)+1,"name":name,"parentTypeId":item.orgTypeId,"children":[]};  
//             if(item.children===null||item.children.length===0){  
//                 item.children=[];  
//                 json.sequence=1;  
//             }else{  
//                 var maxSequence=parseInt(item.children[item.children.length-1].sequence);  
//                 json.sequence=maxSequence+1;  
//             }  
//             OrganizationService.saveOrgType(json).then(function(result) {  
// //                item.children.push(json);  
//                 $scope.init();  
//             }); 
            };            

      $scope.moveLastToTheBeginning = function () {
        var a = $scope.data.pop();
        $scope.data.splice(0, 0, a);
      };

      $scope.addsubItem = function () {
        var a = $scope.data.pop();
        $scope.data.splice(0, 0, a);
      };

      $scope.resettree = function () {
        $scope.data = angular.copy(origdata);
      };

      $scope.newSubItem = function (scope) {
        var nodeData = scope.$modelValue;
        nodeData.nodes.push({
          id: nodeData.id * 10 + nodeData.nodes.length,
          title: nodeData.title + '.' + (nodeData.nodes.length + 1),
          nodes: []
        });
      };

      $scope.collhide = true;
      $scope.collapseAll = function () {
        $scope.$broadcast('angular-ui-tree:collapse-all');
        $scope.collhide = false;
      };

      $scope.expandAll = function () {
        $scope.$broadcast('angular-ui-tree:expand-all');
        $scope.collhide = true;
      };


      $scope.data = [{
        'id': 1,
        'title': '高山湾项目',
        'url':'zhbdata',
        'nodes': [{
        'id': 11,
        'title': '前期资料',
        'url':'htdata',
        'nodes': []
      },{
        'id': 12,
        'title': '设计资料',
        'url':'htdata',
        'nodes': []
      },{
        'id': 13,
        'title': 'BIM资料',
        'url':'htdata',
        'nodes': []
      }]
      }, {
        'id': 2,
        'title': 'XXX项目',
        'url':'zgbdata',
        'nodes': []
      }, {
        'id': 3,
        'title': 'xxx项目2',
        'url':'htdata',
        'nodes': []
      }, {
        'id': 4,
        'title': '设计单位',
        'url':'sjdata',
        'nodes': [
          {
            'id': 41,
            'title': '建筑图纸',
            'url':'jzdata',
            'nodes': []
          }, {
            'id': 42,
            'title': '机电图纸',
            'url':'jddata',
            'nodes': []
          }
        ]
      }];

var origdata = angular.copy($scope.data);

//UI-GRID 分割线-------------------------------
$scope.gridOptions = {
    enableRowSelection: true,
    enableSelectAll: true,
    selectionRowHeaderWidth: 35,
    rowHeight: 25,
    showGridFooter:true,
  };

  $scope.gridOptions.columnDefs = [
    { name: 'fid', maxwidth: 100,displayName:'文件编号'},
    { name: 'fname', displayName:'文件名称'},
    { name: 'uptime', displayName:'上传时间'},
    { name: 'cog', displayName:'操作'}
  ];

 $scope.addData = function() {
    var n = $scope.gridOptions.data.length + 1;
    $scope.gridOptions.data.push({
                'fid': 'No.' + n,
                'fname': 'Person' + Math.floor(Math.random()*100),
                'uptime': '初步设计文件',
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
      "fid": "Cox",
      "fname": "Carney",
      "uptime": "Enormo",
      "cog": "male"
    },
    {
      "fid": "Cox",
      "fname": "Carney",
      "uptime": "Enormo",
      "cog": "male"
    },
    {
      "fid": "Cox",
      "fname": "Carney",
      "uptime": "Enormo",
      "cog": "male"
    },
    {
      "fid": "Cox",
      "fname": "Carney",
      "uptime": "Enormo",
      "cog": "male"
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
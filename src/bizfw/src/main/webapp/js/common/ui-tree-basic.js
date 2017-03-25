'use strict';

    app.controller('ZZJG', ['$scope', function ($scope) {
      $scope.treeOptions = {
      accept: function(sourceNodeScope, destNodesScope, destIndex) {
      return true;},
      };

      $scope.hideGrid=true;
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
        'title': '综合部',
        'url':'zhbdata',
        'nodes': []
      }, {
        'id': 2,
        'title': '总工办',
        'url':'zgbdata',
        'nodes': []
      }, {
        'id': 3,
        'title': '合同成本部',
        'url':'htdata',
        'nodes': []
      }, {
        'id': 4,
        'title': '项目管理部',
        'url':'xmgldata',
        'nodes': [
          {
            'id': 41,
            'title': '高山湾项目',
            'url':'gswdata',
            'nodes': []
          },
          {
            'id': 42,
            'title': 'XXX项目',
            'url':'xxxdata',
            'nodes': []
          }
        ]
      }, {
        'id': 4,
        'title': '设计单位',
        'url':'sjdata',
        'nodes': [
          {
            'id': 31,
            'title': '建筑设计',
            'url':'jzdata',
            'nodes': []
          }, {
            'id': 32,
            'title': '机电设计',
            'url':'jddata',
            'nodes': []
          }
        ]
      }];
    }]);
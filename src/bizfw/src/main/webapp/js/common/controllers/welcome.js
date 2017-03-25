'use strict';

/* Controllers */

  // bootstrap controller
  app.controller('welcomeCtrl', ['$scope', function($scope) {
    $scope.alerts = [
      { al_type: 'success',
      al_name: 'file-o', 
      msg_type: '文档消息',
      msg: '员工1 上传 初步设计图纸2016.9月版本' 
      },
      { al_type: 'info', 
      al_name: 'user', 
      msg_type: '人员消息',
      msg: '员工2 由 组长XX 添加至 XXX项目组' 
      },
      { al_type: 'danger', 
      al_name: 'user', 
      msg_type: '人员消息',
      msg: '员工2 已经离开 XXX项目组' 
      }
    ];
    $scope.alength = $scope.alerts.length;
    $scope.conlog = function() {
      console.log($scope.alerts.length);
    };
    $scope.addAlert = function() {
      $scope.alerts.push({type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.'});
    };

    $scope.closeAlert = function(index) {
      $scope.alerts.splice(index, 1);
      $scope.alength = $scope.alerts.length;
    };
  }]); 

//# sourceURL=welcome.js
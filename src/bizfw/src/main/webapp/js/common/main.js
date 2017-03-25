'use strict';

/* Controllers */

angular.module('app').controller('AppCtrl', ['$scope', '$localStorage', '$window', '$uibModal',
  function($scope, $localStorage, $window, $uibModal) {
    // add 'ie' classes to html
    var isIE = !!navigator.userAgent.match(/MSIE/i);
    isIE && angular.element($window.document.body).addClass('ie');
    isSmartDevice($window) && angular.element($window.document.body).addClass('smart');

    // config
    $scope.app = {
      name: '导航栏',
      version: '0.1.1',
      // for chart colors
      color: {
        primary: '#7266ba',
        info: '#23b7e5',
        success: '#27c24c',
        warning: '#fad733',
        danger: '#f05050',
        light: '#e8eff0',
        dark: '#3a3f51',
        black: '#1c2b36'
      },
      settings: {
        themeID: 1,
        navbarHeaderColor: 'bg-danger dk',
        navbarCollapseColor: 'bg-danger dk',
        asideColor: 'bg-white',
        headerFixed: true,
        asideFixed: false,
        asideFolded: false,
        asideDock: false,
        container: false
      }
    }

    // save settings to local storage
    if (angular.isDefined($localStorage.settings)) {
      $scope.app.settings = $localStorage.settings;
    } else {
      $localStorage.settings = $scope.app.settings;
    }
    $scope.$watch('app.settings', function() {
      if ($scope.app.settings.asideDock && $scope.app.settings.asideFixed) {
        // aside dock and fixed must set the header fixed.
        $scope.app.settings.headerFixed = true;
      }
      // save to local storage
      $localStorage.settings = $scope.app.settings;
    }, true);

    function isSmartDevice($window) {
      // Adapted from http://www.detectmobilebrowsers.com
      var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
      // Checks for iOs, Android, Blackberry, Opera Mini, and Windows
      // mobile devices
      return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
    }

    $scope.peopleName = peopleName;

    $scope.getBaseUrl = function(url) {
      return basePath + url;
    }

    $scope.logout = function() {
      $.ajax({
        url: $scope.getBaseUrl("/loginAction/logout.do"),
        type: 'POST',
        success: function(data) {
          if (data) {
            window.location.href = "login.jsp";
          } else {
            // 响应成功
            alert("注销失败");
          }
        }
      });
    };

    $scope.openChangePwd = function() {
      var modalInstance = $uibModal.open({
        templateUrl: 'changePwdDialog',
        controller: 'ChangePwdDialogCtrl',
        size: 'sm',
        scope: $scope
      });

      modalInstance.result.then(function(selectedItem) {
        $scope.getPeopleList($scope.currDept);
      });
    };

    $scope.nav = {};
    $scope.navXtglArray = ['系统管理', '组织架构', '角色管理', '菜单配置', '角色权限管理'];
    $scope.navWjglArray = ['文件管理', '路径管理', '文件协作'];

    $scope.getmenulist = function() {
      $.ajax({
        url: $scope.getBaseUrl('/menuAction/getMenuTree.do'),
        async: false,
        type: 'POST',
        success: function(data) {
          for (var i = 0; i < data.length; i++) {
            if (data[i].name=='系统管理') {
              $scope.nav.xtgl = true;
              if (data[i].childList&&data[i].childList.length!=0) {
                for (var j = 0; j < data[i].childList.length; j++) {
                  if (data[i].childList[j].name=='组织架构') {
                    $scope.nav.zzjg = true;
                  }
                  if (data[i].childList[j].name=='角色管理') {
                    $scope.nav.jsgl = true;
                  }
                  if (data[i].childList[j].name=='菜单配置') {
                    $scope.nav.cdpz = true;
                  }
                  if (data[i].childList[j].name=='角色权限管理') {
                    $scope.nav.jsqxgl = true;
                  }
                }
              }
            }
            if (data[i].name=='文件管理') {
              $scope.nav.wjgl = true;
              if (data[i].childList&&data[i].childList.length!=0) {
                for (var j = 0; j < data[i].childList.length; j++) {
                  if (data[i].childList[j].name=='路径管理') {
                    $scope.nav.ljgl = true;
                  }
                  if (data[i].childList[j].name=='文件协作') {
                    $scope.nav.wjxz = true;
                  }
                }
              }
            }
          }
        }
      });
    };

    $scope.getmenulist();


  }
]);

app.controller('ChangePwdDialogCtrl', ['$scope', '$uibModalInstance', function($scope, $uibModalInstance) {

  $scope.a = {};

  $scope.changePwd = function() {
    $.ajax({
      url: $scope.getBaseUrl('/peopleAction/modifyPassword.do'),
      type: 'POST',
      data: {
        'oldPassword': $scope.a.oldPassword,
        'newPassword': $scope.a.newPassword
      },
      success: function(data) {
        $uibModalInstance.close('success');
        alert("修改成功");
      }
    });
  };

  $scope.cancel = function() {
    $uibModalInstance.dismiss('cancel');
  };

}]);
'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    ['$rootScope', '$state', '$stateParams',
      function($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
      }
    ]
  )
  .config(
    ['$stateProvider', '$urlRouterProvider',
      function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider
          .otherwise('/app/welcome');
        $stateProvider
          .state('app', {
            abstract: true,
            url: '/app',
            templateUrl: 'page/app.html'
          })
          .state('app.welcome', {
            url: '/welcome',
            templateUrl: 'page/welcome.html',
          })
          .state('app.poManage', {
            url: '',
            templateUrl: 'page/po/poManage.html',
          })
          .state('app.roleManage', {
            url: '/roleManage',
            templateUrl: 'page/auth/roleManage.html',
          })
          .state('app.menuManage', {
            url: '/menuManage',
            templateUrl: 'page/auth/menuManage.html',
          })
          .state('app.rolePrivilege', {
            url: '/rolePrivilege',
            templateUrl: 'page/auth/rolePrivilege.html',
          })
          .state('apps.rolePrivilegetest', {
            url: '/rolePrivilegetest',
            templateUrl: 'page/auth/rolePrivilegetest.html',
          })
          .state('app.folderManage', {
            url: '/folderManage',
            templateUrl: 'page/document/folderManage.html',
          })
          .state('app.fileManage', {
            url: '/fileManage',
            templateUrl: 'page/document/fileManage.html',
            resolve: {               deps: ['$ocLazyLoad',
                  function($ocLazyLoad) {
                    return $ocLazyLoad.load('web-explorer').then(
                      function() {
                        return $ocLazyLoad.load('js/document/fileManage.js');
                      }
                    );
                  }
                ]
              }
          })
          .state('apps', {
            abstract: true,
            url: '/apps',
            templateUrl: 'page/layout.html'
          });

      }
    ]
  );
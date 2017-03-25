// lazyload config

angular.module('app')
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
  .constant('JQ_CONFIG', {
      easyPieChart:   ['js/include/jquery/charts/easypiechart/jquery.easy-pie-chart.js'],
      sparkline:      ['js/include/jquery/charts/sparkline/jquery.sparkline.min.js'],
      plot:           ['js/include/jquery/charts/flot/jquery.flot.min.js', 
                          'js/include/jquery/charts/flot/jquery.flot.resize.js',
                          'js/include/jquery/charts/flot/jquery.flot.tooltip.min.js',
                          'js/include/jquery/charts/flot/jquery.flot.spline.js',
                          'js/include/jquery/charts/flot/jquery.flot.orderBars.js',
                          'js/include/jquery/charts/flot/jquery.flot.pie.min.js'],
      slimScroll:     ['js/include/jquery/slimscroll/jquery.slimscroll.min.js'],
      sortable:       ['js/include/jquery/sortable/jquery.sortable.js'],
      nestable:       ['js/include/jquery/nestable/jquery.nestable.js',
                          'js/include/jquery/nestable/nestable.css'],
      filestyle:      ['js/include/jquery/file/bootstrap-filestyle.min.js'],
      slider:         ['js/include/jquery/slider/bootstrap-slider.js',
                          'js/include/jquery/slider/slider.css'],
      chosen:         ['js/include/jquery/chosen/chosen.jquery.min.js',
                          'js/include/jquery/chosen/chosen.css'],
      TouchSpin:      ['js/include/jquery/spinner/jquery.bootstrap-touchspin.min.js',
                          'js/include/jquery/spinner/jquery.bootstrap-touchspin.css'],
      wysiwyg:        ['js/include/jquery/wysiwyg/bootstrap-wysiwyg.js',
                          'js/include/jquery/wysiwyg/jquery.hotkeys.js'],
      dataTable:      ['js/include/jquery/datatables/jquery.dataTables.min.js',
                          'js/include/jquery/datatables/dataTables.bootstrap.js',
                          'js/include/jquery/datatables/dataTables.bootstrap.css'],
      vectorMap:      ['js/include/jquery/jvectormap/jquery-jvectormap.min.js', 
                          'js/include/jquery/jvectormap/jquery-jvectormap-world-mill-en.js',
                          'js/include/jquery/jvectormap/jquery-jvectormap-us-aea-en.js',
                          'js/include/jquery/jvectormap/jquery-jvectormap.css'],
      footable:       ['js/include/jquery/footable/footable.all.min.js',
                          'js/include/jquery/footable/footable.core.css']
      }
  )
/**
 * @license Angular UI Tree v2.22.1
 * (c) 2010-2016. https://github.com/angular-ui-tree/angular-ui-tree
 * License: MIT
 */

  // oclazyload config
  .config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  false,
          events: true,
          modules: [
              {
                  name: 'ngGrid',
                  files: [
                      'js/include/modules/ng-grid/ng-grid.min.js',
                      'js/include/modules/ng-grid/ng-grid.min.css',
                      'js/include/modules/ng-grid/theme.css'
                  ]
              },
              {
                  name: 'ui.select',
                  files: [
                      'js/include/modules/angular-ui-select/select.min.js',
                      'js/include/modules/angular-ui-select/select.min.css'
                  ]
              },
              {
                  name:'angularFileUpload',
                  files: [
                    'js/include/modules/angular-file-upload/angular-file-upload.min.js'
                  ]
              },
              {
                  name:'ui.calendar',
                  files: ['js/include/modules/angular-ui-calendar/calendar.js']
              },
              {
                  name: 'ngImgCrop',
                  files: [
                      'js/include/modules/ngImgCrop/ng-img-crop.js',
                      'js/include/modules/ngImgCrop/ng-img-crop.css'
                  ]
              },
              {
                  name: 'angularBootstrapNavTree',
                  files: [
                      'js/include/modules/angular-bootstrap-nav-tree/abn_tree_directive.js',
                      'js/include/modules/angular-bootstrap-nav-tree/abn_tree.css'
                  ]
              },
              {
                  name: 'toaster',
                  files: [
                      'js/include/modules/angularjs-toaster/toaster.js',
                      'js/include/modules/angularjs-toaster/toaster.css'
                  ]
              },
              {
                  name: 'textAngular',
                  files: [
                      'js/include/modules/textAngular/textAngular-sanitize.min.js',
                      'js/include/modules/textAngular/textAngular.min.js'
                  ]
              },
              {
                  name: 'vr.directives.slider',
                  files: [
                      'js/include/modules/angular-slider/angular-slider.min.js',
                      'js/include/modules/angular-slider/angular-slider.css'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular',
                  files: [
                      'js/include/modules/videogular/videogular.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.controls',
                  files: [
                      'js/include/modules/videogular/plugins/controls.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.buffering',
                  files: [
                      'js/include/modules/videogular/plugins/buffering.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.overlayplay',
                  files: [
                      'js/include/modules/videogular/plugins/overlay-play.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.poster',
                  files: [
                      'js/include/modules/videogular/plugins/poster.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.imaads',
                  files: [
                      'js/include/modules/videogular/plugins/ima-ads.min.js'
                  ]
              },
              {
                  name: 'web-explorer',
                  files: [
                    'css/web-explorer.css',
                      'js/include/modules/videogular/plugins/ima-ads.min.js'
                  ]
              }
          ]
      });
  }])
;
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.thinkequip.bizfw.po.model.People"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
People people = (People) request.getSession().getAttribute("peopleInfo");
String peopleName = "";
if (people != null){
	peopleName = people.getName();
}
%>
<!DOCTYPE html>
<html lang="cn" data-ng-app="app">
<head>
  <meta charset="utf-8" />
  <title>BIM协同平台</title>
  <meta name="description" content="林同棪内部平台" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="css/animate.css" type="text/css" />
  <link rel="stylesheet" href="css/font-awesome.css" type="text/css" />
  <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="css/font.css" type="text/css" />
  <link rel="stylesheet" href="css/app.css" type="text/css" />
  <link rel="stylesheet" href="css/angular-ui-tree.css" type="text/css" />
  <link rel="stylesheet" href="css/angular-ui-grid.css" type="text/css" />
  <link rel="stylesheet" href="css/angular-ui-select.css" type="text/css" />

</head>
<body ng-controller="AppCtrl">
  <script> 
    var basePath="<%=basePath%>";
    var peopleName = "<%=peopleName%>";
  </script> 
  <div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}" ui-view></div>


  <!-- jQuery -->
  <script src="js/vendors/jQuery/jquery-3.1.1.js"></script>

  <!-- Angular -->
  <script src="js/vendors/angular/angular.js"></script>
  <script src="js/vendors/angular/angular-animate.js"></script>
  <script src="js/vendors/angular/angular-cookies.js"></script>
<!--   <script src="js/vendors/angular/angular-resource.js"></script>
  <script src="js/vendors/angular/angular-sanitize.js"></script>
  <script src="js/vendors/angular/angular-touch.js"></script> -->

  <!-- Vendor -->
  <script src="js/vendors/ui-router/ui-router.js"></script> 
  <script src="js/vendors/ngStorage/ngStorage.js"></script>
  <script src="js/vendors/ui-tree/ui-tree.js"></script> 
  <script src="js/vendors/ui-grid/ui-grid.js"></script> 
  <script src="js/vendors/ui-select/select.js"></script> 
  <!-- bootstrap -->
  <script src="js/vendors/ui-bootstrap/ui-bootstrap-tpls.js"></script>
  <!-- lazyload -->
  <script src="js/vendors/ocLazyLoad/ocLazyLoad.js"></script>

  <!-- App -->
  <script src="js/common/common.js"></script>
  <script src="js/common/app.js"></script>
  <script src="js/common/config.js"></script>
  <script src="js/common/config.lazyload.js"></script>
  <script src="js/common/tylin.router.js"></script>
  <script src="js/common/main.js"></script>
  <script src="js/common/services/ui-load.js"></script>
  <script src="js/common/filters/fromNow.js"></script>
  <script src="js/common/directives/setnganimate.js"></script>
  <script src="js/common/directives/ui-butterbar.js"></script>
  <script src="js/common/directives/ui-focus.js"></script>
  <script src="js/common/directives/ui-fullscreen.js"></script>
  <script src="js/common/directives/ui-jq.js"></script>
  <script src="js/common/directives/ui-module.js"></script>
  <script src="js/common/directives/ui-nav.js"></script>
  <script src="js/common/directives/ui-scroll.js"></script>
  <script src="js/common/directives/ui-shift.js"></script>
  <script src="js/common/directives/ui-toggleclass.js"></script>
  <script src="js/common/directives/ui-validate.js"></script>
  <script src="js/common/controllers/bootstrap.js"></script>

  <!-- Lazy loading -->
  <script type="text/javascript" src="js/vendors/ng-file-upload/ng-file-upload-all.js"></script>
</body>
</html>
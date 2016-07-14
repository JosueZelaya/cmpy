<%-- 
    Document   : boot
    Created on : 24-may-2015, 8:01:11
    Author     : genaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html ng-app="cmpy">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ComprameYa!</title>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/publicaciones.css" rel="stylesheet">
        <style type="text/css">
            [ng\:cloak], [ng-cloak], .ng-cloak {
                display: none !important;
            }
            .angular-google-map-container { height: 400px; }
        </style>
    </head>
    <body ng-cloak class="ng-cloak">
        
        <!-- MAIN CONTENT -->
        <!--<div class="container">-->

            <!-- THIS IS WHERE WE WILL INJECT OUR CONTENT ============================== -->
            <div ui-view></div>

        <!--</div>-->        
        
        <%@include file="common/loginModal.jsp" %>

        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/lodash/lodash.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-simple-logger/dist/angular-simple-logger.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-google-maps/dist/angular-google-maps.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/ng-flow/dist/ng-flow-standalone.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacion.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacionController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/categorias.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/catService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/catController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/menuPrincipal.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anuncios.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anunciosService.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anunciosController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/maps.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapService.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapControllers.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/utils.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/modalController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cmpy/cmpy.module.js" ></script>
        
    </body>
</html>

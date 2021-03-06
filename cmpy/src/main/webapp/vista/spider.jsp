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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html ng-app="cmpy">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>ComprameYa!</title>

        <!--<style> .ng-cloak { display: none !important; } </style>-->
        <link href="${pageContext.request.contextPath}/resources/images/favicon_trans.png" rel="icon" type="image/png">
        <style type="text/css">
            [ng\:cloak], [ng-cloak], .ng-cloak {
                display: none !important;
            }
            .angular-google-map-container { height: 400px; }
        </style>
    </head>
    <body ng-cloak class="ng-cloak">

        <nav id="navBar" class="navbar navbar-default navbar-static-top" role="navigation" ng-controller="menuPrincipalController">
            <div class="container">
                <div class="navbar-header"> 
                    <a href="/" class="pull-left" ui-sref='home' ui-sref-opts="{reload:true}">
                        <img ng-src="/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="$ComprameYa!">
                    </a>
                </div> 
                <a href="/Spider/scanearTiendasOverSSL">Run Spider Secure Scanner</a>
                <a href="/Spider/scanearTiendasInsecure">Run Spider Insecure Scanner</a>
                <div class="navbar-right navbar-collapse collapse" uib-collapse="navCollapsed">   

                    <ul class="nav navbar-nav"> 
                        <a href="" ng-click="logout()">Salir <span class="glyphicon glyphicon-off" aria-hidden="true"></span></a>
                    </ul>                    
                </div>
            </div>
        </nav>
        Saludo: ${parametro}
        Resultado: ${name}

<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script> 
        <script type='text/javascript' src='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.js'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/lodash/lodash.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/underscore/underscore-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-simple-logger/dist/angular-simple-logger.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-google-maps/dist/angular-google-maps.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/ng-flow/dist/ng-flow-standalone.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-swipe/dist/angular-swipe.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/matchmedia/matchMedia.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/matchmedia-ng/matchmedia-ng.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-confirm-modal/angular-confirm.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/sockjs/sockjs.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/stomp-websocket/lib/stomp.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.tpls.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacion.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacionController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/notificacion.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/notificacionService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/PushNotificationService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/notificacionController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/categorias.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/catService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/catController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/menuPrincipal.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/menuPrincipalController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/busquedaService.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anuncios.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anunciosService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/comentariosService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/recursoService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/tiendaService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anunciosController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/misPublicacionesController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/publicacionesFiltradasController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/venderController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/empresasController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/productoController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/ubicacionesController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/maps.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapService.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapControllers.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/utils.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/modalController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/loadingDirective.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mensajes/mensajes.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mensajes/mensajesService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mensajes/mensajesController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cmpy/cmpy.module.js" ></script>

        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/commentbox.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/publicaciones.css" rel="stylesheet">-->

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/min/app.min.js" ></script>
        <link href="${pageContext.request.contextPath}/resources/min/css/style.min.css" rel="stylesheet" type="text/css" media='all'>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        
    </body>
</html>

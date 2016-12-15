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

        <!-- MAIN CONTENT -->
        <nav id="navBar" class="navbar navbar-default navbar-static-top" role="navigation" ng-controller="menuPrincipalController">
            <div class="container">
                <div class="navbar-header"> 

                    <button ng-if="!authenticated" type="button" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="open('loginModal.html')">                
                        Iniciar Sesion <span class="glyphicon glyphicon-user">
                    </button>            

                    <button ng-show="authenticated" type="button" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="navCollapsed = !navCollapsed">
                        <span class="sr-only">Toggle navigation</span>
                        <div ng-if="!imageUrl">
                            <img ng-show="localAccount" height="28px" width="28px" ng-src="/resources/images/default.jpg" alt="..." />
                        </div>
                        <div ng-if="imageUrl">
                            <img ng-show="localAccount" height="28px" width="28px" ng-src="/images/getThumbnail/{{imageUrl}}" alt="..." />
                            <img ng-show="!localAccount" height="28px" width="28px" ng-src="{{imageUrl}}" alt="..." />
                        </div>                        
                    </button>            

                    <button ng-show="authenticated" id="botonAvisos" class="navbar-toggle navbar-brand" aria-expanded="false" aria-controls="navbar" ng-click="collapseNot = !collapseNot">
                        <span class="sr-only">Toggle navigation</span>    
                        Avisos
                        <span class="badge badge-notify">{{totalNotificaciones + NoLeidosTotal}}</span>
                    </button>   

                    <button id="botonInfo" class="navbar-toggle navbar-brand" aria-expanded="false" aria-controls="navbar" ng-click="infoCollapsed = !infoCollapsed">
                        <span class="sr-only">Toggle navigation</span>    
                        Ayuda
                        <span class="glyphicon glyphicon-info-sign"></span>
                    </button>

                    <a href="/" class="pull-left" ui-sref='home' ui-sref-opts="{reload:true}">
                        <img ng-src="/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="$ComprameYa!">
                    </a>

                </div>                 
                <div class="navbar-right navbar-collapse collapse" uib-collapse="navCollapsed">

                    <ul class="nav navbar-nav"> 

                        <li ng-if="!authenticated">
                            <a href="https://youtu.be/cmfOtIizd70" target="_blank">Video de ayuda
                                <span class="glyphicon glyphicon-facetime-video"></span>
                            </a>
                        </li>

                        <!--<div ng-show="usuario.login === '' || usuario.login === 'anonymousUser'">-->
                        <li ng-if="!authenticated">
                            <a href="#" ng-click="open('loginModal.html')">Iniciar Sesion <span class="glyphicon glyphicon-user"></a>
                        </li>                           
                        <!--</div>-->
                        <!--<div ng-show="usuario.login !== '' && usuario.login !== 'anonymousUser'">-->
                        <li class="dropdown" uib-dropdown="" ng-if="authenticated && navCollapsed">
                            <a ng-if="imageUrl" href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">                                
                                Yo
                                <img ng-show="localAccount" height="28px" width="28px" ng-src="/images/getThumbnail/{{imageUrl}}" alt="..." />
                                <img ng-show="!localAccount" height="28px" width="28px" ng-src="{{imageUrl}}" alt="..." />
                            </a>
                            <a ng-if="!imageUrl" href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">                                                                
                                Yo
                                <img ng-show="localAccount" height="28px" width="28px" ng-src="/resources/images/default.jpg" alt="..." />
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li ng-if="authenticated">
                                    <a href="#" ui-sref="home.misPublicaciones"> 
                                        Mis Anuncios
                                        <span class="glyphicon glyphicon-shopping-cart"></span>
                                    </a>
                                </li>
                                <li><a href="#">Cambiar Clave</a></li>
                                <li><a href="#">Edita Perfil</a></li>
                                <li><a href="#">Configuracion</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">Sesión</li>                                     
                                <li><a href="" ng-click="logout()">Salir <span class="glyphicon glyphicon-off" aria-hidden="true"></span></a></li>                                
                            </ul>
                        </li>


                        <li ng-if="authenticated && !navCollapsed">
                            <a href="#" ui-sref="home.misPublicaciones"> 
                                Mis Anuncios
                                <span class="glyphicon glyphicon-shopping-cart"></span>
                            </a>
                        </li>
                        <li ng-if="authenticated && !navCollapsed"><a href="#">Cambiar Clave</a></li>
                        <li ng-if="authenticated && !navCollapsed"><a href="#">Edita Perfil</a></li>
                        <li ng-if="authenticated && !navCollapsed"><a href="#">Configuracion</a></li>
                        <li ng-if="authenticated && !navCollapsed" class="divider"></li>
                        <li ng-if="authenticated && !navCollapsed" class="dropdown-header">Sesión</li>                                     
                        <li ng-if="authenticated && !navCollapsed"><a href="" ng-click="logout()">Salir <span class="glyphicon glyphicon-off" aria-hidden="true"></span></a></li>                                

                        <!--</div>-->
                    </ul>                    
                </div>
                <div class="navbar-right navbar-collapse collapse" uib-collapse="collapseNot">
                    <ul class="nav navbar-nav"> 

                        <li class="dropdown" uib-dropdown="" ng-if="authenticated">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> 
                                <span class="glyphicon glyphicon-envelope"></span>
                                <span class="badge badge-notify">{{NoLeidosTotal}}</span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li ng-repeat="mensaje in mensajes">
                                    <a href="" 
                                       ng-click="setMensajeUsuarioLeido(mensaje.usuarioEmisor.id);" 
                                       ui-sref="home.vistaMensaje({usuarioId:mensaje.usuarioEmisor.id,usuarioNombre:mensaje.usuarioEmisor.persona.nombre,asunto:mensaje.titulo})"
                                       ng-click="collapseNot = !collapseNot">
                                        <span class="badge pull-right">{{mensaje.total}}</span>
                                        {{mensaje.texto.slice(0, 50)}}...  <span class="text-muted" style="font-size: 11px;float:right;margin-right:10px">{{mensaje.usuarioEmisor.persona.nombre}} </span>
                                    </a>
                                </li> 
                                <li style="text-align: center">
                                    <a href="" 
                                       ui-sref="home.vistaMensaje({usuarioId:0,usuarioNombre:'',asunto:''})"
                                       ng-click="collapseNot = !collapseNot">
                                        Ver Bandeja...
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown" uib-dropdown="" ng-if="authenticated">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                <span class="glyphicon glyphicon-comment"></span>
                                <span class="badge badge-notify">{{totalNotificaciones}}</span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li ng-repeat="notificacionUsuario in notificaciones">
                                    <a href="" 
                                       ng-click="quitarVisibilidad(notificacionUsuario.id, $index)" 
                                       ui-sref-opts="{reload:true}"
                                       ui-sref="home.vistaProducto({publicacionId: {{notificacionUsuario.notificacion.link}}, '#': 'commentBox'})"
                                       ng-click="collapseNot = !collapseNot">
                                        {{notificacionUsuario.notificacion.mensaje.slice(0, 50)}}...
                                    </a>
                                </li>
                                <li style="text-align: center">
                                    <a href=""  
                                       ui-sref="home.vistaNotificaciones({'#': 'publicacionesProductos'})"
                                       ng-click="collapseNot = !collapseNot">
                                        Ver Todas ...
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div uib-dropdown="" class="dropdown navbar-right navbar-collapse collapse" uib-collapse="infoCollapsed">                    
                    <ul ng-if="infoCollapsed" class="nav navbar-nav">
                        <li class="dropdown" uib-dropdown="">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">                                
                                Información
                                <span class="glyphicon glyphicon-info-sign"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li>
                                    <a href="https://youtu.be/cmfOtIizd70" target="_blank">Video de ayuda
                                        <span class="glyphicon glyphicon-facetime-video"></span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" ui-sref="home.about({'#': 'publicacionesProductos'})" ng-click="infoCollapsed = !infoCollapsed"> 
                                        Nosotros
                                    </a>
                                </li>                                

                                <li>
                                    <a href="#" ui-sref="home.terminosServicio({'#': 'publicacionesProductos'})" ng-click="infoCollapsed = !infoCollapsed"> 
                                        Terminos
                                    </a>
                                </li>

                                <li>
                                    <a href="#" ui-sref="home.crearTienda({'#': 'publicacionesProductos'})" ng-click="infoCollapsed = !infoCollapsed"> 
                                        Negocios
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul ng-if="!infoCollapsed" class="nav navbar-nav">
                        <li>
                            <a href="https://youtu.be/cmfOtIizd70" target="_blank">Video de ayuda
                                <span class="glyphicon glyphicon-facetime-video"></span>
                            </a>
                        </li>
                        <li>
                            <a href="#" ui-sref="home.about({'#': 'publicacionesProductos'})" ng-click="infoCollapsed = !infoCollapsed"> 
                                Nosotros
                            </a>
                        </li>                                

                        <li>
                            <a href="#" ui-sref="home.terminosServicio({'#': 'publicacionesProductos'})" ng-click="infoCollapsed = !infoCollapsed"> 
                                Terminos
                            </a>
                        </li>

                        <li>
                            <a href="#" ui-sref="home.crearTienda({'#': 'publicacionesProductos'})" ng-click="infoCollapsed = !infoCollapsed"> 
                                Negocios
                            </a>
                        </li>
                    </ul>
                </div>
                <form class="navbar-form navbar-left" style="width: 100%" role="search">
                    <div class="input-group">
                        <input ng-keyup="$event.keyCode == 13 && buscar(terminoBusqueda)" type="text" class="form-control" placeholder="Buscar producto..." ng-model="terminoBusqueda">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" ui-sref-opts="{reload:true}" ui-sref="home.busqueda({terminoBusqueda: terminoBusqueda})">BuscarYa!</button>
                        </span>
                    </div>                    
                </form>
            </div>
        </nav>

        <!-- THIS IS WHERE WE WILL INJECT OUR CONTENT ============================== -->
        <div ui-view></div>

        <%@include file="common/loginModal.jsp" %>

        <!-- PARA TRABAJAR EN DESARROLLO USAR ESTOS SCRIPTS -->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-sanitize/angular-sanitize.min.js"></script> 
        <script type='text/javascript' src='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.js'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/lodash/dist/lodash.min.js"></script>
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/busquedaController.js" ></script>
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
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/commentbox.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/publicaciones.css" rel="stylesheet">-->


        <!-- PARA PASAR A PRODUCCIÓN USAR ESTOS SCRIPTS Y COMENTAR LOS ANTERIORES -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/min/app.min.js" ></script>
        <link href="${pageContext.request.contextPath}/resources/min/css/style.min.css" rel="stylesheet" type="text/css" media='all'>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />

    </body>
</html>

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
                        Hola! {{username}} 
                        <img ng-show="localAccount" height="28px" width="28px" ng-src="/images/getThumbnail/{{imageUrl}}" alt="..." />
                        <img ng-show="!localAccount" height="28px" width="28px" ng-src="{{imageUrl}}" alt="..." />
                    </button>            

                    <button ng-show="authenticated" id="botonAvisos" class="navbar-toggle navbar-brand" aria-expanded="false" aria-controls="navbar" ng-click="collapseNot = !collapseNot">
                        <span class="sr-only">Toggle navigation</span>    
                        Avisos
                        <span class="badge badge-notify">{{totalNotificaciones + NoLeidosTotal}}</span>
                    </button>   

                    <a href="/" class="pull-left" ui-sref='home' ui-sref-opts="{reload:true}">
                        <img ng-src="/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="$ComprameYa!">
                    </a>
                </div> 

                <div class="navbar-right navbar-collapse collapse" uib-collapse="navCollapsed">   

                    <ul class="nav navbar-nav"> 
                        
                        <li>
                            <a href="#" ui-sref="home.about"> 
                                Acerca de
                            </a>
                        </li>
                        
                        <li>
                            <a href="#" ui-sref="home.terminosServicio"> 
                                Terminos de Servicio
                            </a>
                        </li>
                        
                        <li>
                            <a href="#" ui-sref="home.crearTienda"> 
                                Negocios
                            </a>
                        </li>
                        
                        <li ng-if="authenticated">
                            <a href="#" ui-sref="home.misPublicaciones"> 
                                Mis Publiaciones
                            </a>
                        </li>

                        <!--<div ng-show="usuario.login === '' || usuario.login === 'anonymousUser'">-->
                        <li ng-if="!authenticated">
                            <a href="#" ng-click="open('loginModal.html')">Iniciar Sesion <span class="glyphicon glyphicon-user"></a>
                        </li>                           
                        <!--</div>-->
                        <!--<div ng-show="usuario.login !== '' && usuario.login !== 'anonymousUser'">-->
                        <li class="dropdown" uib-dropdown="" ng-if="authenticated">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                Mi Cuenta
                                <img ng-show="localAccount" height="28px" width="28px" ng-src="/images/getThumbnail/{{imageUrl}}" alt="..." />
                                <img ng-show="!localAccount" height="28px" width="28px" ng-src="{{imageUrl}}" alt="..." />
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Cambiar Clave</a></li>
                                <li><a href="#">Edita Perfil</a></li>
                                <li><a href="#">Configuracion</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">Sesión</li>                                     
                                <li><a href="" ng-click="logout()">Salir <span class="glyphicon glyphicon-off" aria-hidden="true"></span></a></li>                                
                            </ul>
                        </li>                        
                        <!--</div>-->
                    </ul>                    
                </div>

                <div class="navbar-right navbar-collapse collapse" uib-collapse="collapseNot">
                    <ul class="nav navbar-nav"> 

                        <li class="dropdown" uib-dropdown="" ng-if="authenticated">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> 
                                Mensajes
                                <span class="badge badge-notify">{{NoLeidosTotal}}</span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li ng-repeat="mensaje in mensajes">
                                    <a href="" 
                                       ng-click="setMensajeUsuarioLeido(mensaje.usuarioEmisor.id);" 
                                       ui-sref="home.vistaMensaje({usuarioId:mensaje.usuarioEmisor.id,usuarioNombre:mensaje.usuarioEmisor.persona.nombre,asunto:mensaje.titulo})">
                                        <span class="badge pull-right">{{mensaje.total}}</span>
                                        {{mensaje.texto.slice(0, 50)}}...  <span class="text-muted" style="font-size: 11px;float:right;margin-right:10px">{{mensaje.usuarioEmisor.persona.nombre}} </span>
                                    </a>
                                </li> 
                                <li style="text-align: center">
                                    <a href="" 
                                       ui-sref="home.vistaMensaje({usuarioId:0,usuarioNombre:'',asunto:''})">
                                        Ver Bandeja...
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown" uib-dropdown="" ng-if="authenticated">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                Notificaciones 
                                <span class="badge badge-notify">{{totalNotificaciones}}</span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li ng-repeat="notificacionUsuario in notificaciones">
                                    <a href="" 
                                       ng-click="quitarVisibilidad(notificacionUsuario.id, $index)" 
                                       ui-sref-opts="{reload:true}"
                                       ui-sref="home.vistaProducto({publicacionId: {{notificacionUsuario.notificacion.link}}, '#': 'commentBox'})">
                                        {{notificacionUsuario.notificacion.mensaje.slice(0, 50)}}...
                                    </a>
                                </li>
                                <li style="text-align: center">
                                    <a href=""  
                                       ui-sref="home.vistaNotificaciones({'#': 'publicacionesProductos'})">
                                        Ver Todas ...
                                    </a>
                                </li>
                            </ul>
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
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/min/app.min.js" ></script>
        <link href="${pageContext.request.contextPath}/resources/min/css/style.min.css" rel="stylesheet" type="text/css" media='all'>

        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />

    </body>
</html>

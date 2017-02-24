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
        <base href="/">
    </head>
    <body ng-cloak class="ng-cloak" flow-prevent-drop>

        <!-- MAIN CONTENT -->
        <nav id="navBar" class="navbar navbar-default navbar-static-top" role="navigation" ng-controller="menuPrincipalController">
            <div class="container">
                <div class="navbar-header"> 

                    <button ng-if="!authenticated" type="button" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="open('loginModal.html')">                
                        Entrar <span class="glyphicon glyphicon-user">
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

                    <button ng-show="authenticated" id="botonAvisos" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="collapseNot = !collapseNot">                                
                        </span> <span class="badge badge-notify">{{totalNotificaciones + NoLeidosTotal}}</span>
                        Avisos
                    </button>   

                    <button id="botonInfo" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="infoCollapsed = !infoCollapsed">                                                   
                        <span class="glyphicon glyphicon-info-sign"></span>
                        Info 
                    </button>
                    
                    <button id="botonVisitas" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" uib-tooltip="visitas recibidas" tooltip-placement="bottom">
                        <span class="glyphicon glyphicon-eye-open"></span>
                        {{visitas}}                        
                    </button>
                    
                    <a href="#" ui-sref="home.sorteo({'#': 'publicacionesProductos'})" id="botonSorteo" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" uib-tooltip="ver el sorteo" tooltip-placement="bottom">
                        <span class="glyphicon glyphicon-eye-open"></span>
                        sorteo
                    </a>

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
                                <li ng-if="localAccount" ng-click="open('changePassModal.html')"><a href="#">Cambiar Clave</a></li>
                                <li ng-if="authenticated" ng-click="open('updateCellModal.html')"><a href="#">Actualizar Teléfono</a></li>
                                <li><a href="#">Editar Perfil</a></li>
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
                        <li ng-if="localAccount && authenticated && !navCollapsed"><a href="#" ng-click="open('changePassModal.html'); cancel()">Cambiar Clave</a></li>
                        <li ng-if="authenticated && !navCollapsed"><a href="#" ng-click="open('updateCellModal.html'); cancel()">Actualizar Teléfono</a></li>
                        <li ng-if="authenticated && !navCollapsed"><a href="#">Editar Perfil</a></li>
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
                        <li>
                            <a uib-tooltip="visitas recibidas" tooltip-placement="bottom">{{visitas}}
                                <span class="glyphicon glyphicon-eye-open"></span>
                            </a>
                        </li>
                        <li>
                            <a href="#" ui-sref="home.sorteo({'#': 'publicacionesProductos'})" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" uib-tooltip="ver el sorteo" tooltip-placement="bottom">
                                <span class="glyphicon glyphicon-eye-open"></span>
                                sorteo
                            </a>
                        </li>
                        <li class="dropdown" uib-dropdown="">
                            <a href="#" uib-dropdown-toggle="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">                                
                                Info
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
                        <input ng-keyup="$event.keyCode == 13 && buscar(terminoBusqueda)" type="text" class="form-control input-lg" placeholder="Buscar producto..." ng-model="terminoBusqueda">
                        <span class="input-group-btn">
                            <button class="btn btn-default btn-lg" type="button" ui-sref-opts="{reload:true}" ui-sref="home.busqueda({terminoBusqueda: terminoBusqueda})">BuscarYa!</button>
                        </span>
                    </div>                    
                </form>
            </div>
        </nav>

        <!-- THIS IS WHERE WE WILL INJECT OUR CONTENT ============================== -->
        <div ui-view></div>

        <%@include file="common/loginModal.jsp" %>

        <script type="text/ng-template" id="changePassModal.html">
            <div class="modal-body" ng-app="cmpy.autenticacion" ng-controller="changePassController">
                <div ng-class="valtitulo">
                    <label for="passActual" class="control-label">Ingrese contraseña actual</label>
                    <input ng-keyup="$event.keyCode == 13 && changePass()" ng-model="passActual" id="passActual" type="password" class="form-control"/>
                    <div class="alert alert-danger alert-dismissible fade in" role="alert" ng-if="wrongPass">
                        {{wrongPassMsj}}
                    </div>
                </div>
                <div ng-class="valtitulo">
                    <label for="passNuevo" class="control-label">Ingrese contraseña nueva</label>
                    <input ng-keyup="$event.keyCode == 13 && changePass()" ng-model="passNuevo" id="passNuevo" type="password" class="form-control"/>
                    <div class="alert alert-danger alert-dismissible fade in" role="alert" ng-if="passNotEqual">
                        {{msjPasswordNotEqual}}
                    </div>
                </div>
                <div ng-class="valtitulo">
                    <label for="passConfirmacion" class="control-label">Confirme la nueva contraseña</label>
                    <input ng-keyup="$event.keyCode == 13 && changePass()" ng-model="passConfirmacion" id="passConfirmacion" type="password" class="form-control"/>
                    <div class="alert alert-danger alert-dismissible fade in" role="alert" ng-if="passNotEqual">
                        {{msjPasswordNotEqual}}
                    </div>
                </div>
                <br/>
                <button ng-click="changePass()" type="button" class="btn btn-success" uib-tooltip="Aplicar el cambio de contraseña" tooltip-placement="bottom">
                    Aplicar Cambio
                </button>
                <button ng-click="cancel()" type="button" class="btn btn-warning" uib-tooltip="Cancelar cambio de contraseña" tooltip-placement="bottom">
                    Cancelar
                </button>                
            </div>
        </script>
        
        <script type="text/ng-template" id="resetPassModal.html">
            <div class="modal-body" ng-app="cmpy.autenticacion" ng-controller="resetPassController">
                <div ng-class="valtitulo">
                    <label for="emailReset" class="control-label">Ingrese su dirección de correo:</label>
                    <input ng-keyup="$event.keyCode == 13 && resetPass()" ng-model="emailReset" id="emailReset" type="text" class="form-control"/>
                    <div class="alert alert-danger alert-dismissible fade in" role="alert" ng-if="wrongEmail">
                        {{wrongEmailMsj}}
                    </div>
                </div>        
                <br/>
                <button ng-disabled="!ready" ng-click="resetPass()" type="button" class="btn btn-success" uib-tooltip="Envia un email a tu correo para resetear la contraseña" tooltip-placement="bottom">
                    Resetear Password
                </button>
                <button ng-click="cancel()" type="button" class="btn btn-warning" uib-tooltip="Cancelar cambio de contraseña" tooltip-placement="bottom">
                    Cancelar
                </button>                
            </div>
        </script>
        
        <script type="text/ng-template" id="updateCellModal.html">
            <div class="modal-body" ng-app="cmpy.autenticacion" ng-controller="updateCellController">
                <div ng-class="valtitulo">
                    <label for="updatedCell" class="control-label">Ingrese su nuevo número telefónico:</label>
                    <input ng-keyup="$event.keyCode == 13 && updateCell()" ng-model="updatedCell" id="updatedCell" type="text" class="form-control"/>
                    <div class="alert alert-danger alert-dismissible fade in" role="alert" ng-if="wrongCell">
                        {{wrongCellMsj}}
                    </div>
                </div>        
                <br/>
                <button ng-disabled="!ready" ng-click="updateCell()" type="button" class="btn btn-success" uib-tooltip="Actualiza tu teléfono ahora" tooltip-placement="bottom">
                    Actualizar Teléfono
                </button>
                <button ng-click="cancel()" type="button" class="btn btn-warning" uib-tooltip="Cancelar actualizacion de teléfono" tooltip-placement="bottom">
                    Cancelar
                </button>                
            </div>
        </script>

        <script type="text/javascript" src="https://widget.cloudinary.com/global/all.js"></script>
        <%--PARA TRABAJAR EN DESARROLLO USAR ESTOS SCRIPTS --%>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/blueimp-load-image/js/load-image.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script> 
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/changePassController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/resetPassController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/updateCellController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacionService.js" ></script>
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sorteo/sorteo.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sorteo/sorteoController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/maps.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapService.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapControllers.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/utils.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/modalController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/loadingDirective.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/utilsService.js" ></script>
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
        <link href="${pageContext.request.contextPath}/resources/css/index/publicaciones.css" rel="stylesheet">--%>


        <%-- PARA PASAR A PRODUCCIÓN USAR ESTOS SCRIPTS Y COMENTAR LOS ANTERIORES --%>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/min/app.min.js" ></script>
        <link href="${pageContext.request.contextPath}/resources/min/css/style.min.css" rel="stylesheet" type="text/css" media='all'>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        
    </body>
    <canvas height='1' id='confetti' width='1' ng-show="confeti"></canvas>
</html>

<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }

    
var xmlHttp;
function srvTime(){
    try {
        //FF, Opera, Safari, Chrome
        xmlHttp = new XMLHttpRequest();
    }
    catch (err1) {
        //IE
        try {
            xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
        }
        catch (err2) {
            try {
                xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
            }
            catch (eerr3) {
                //AJAX not supported, use CPU time.
                alert("AJAX not supported");
            }
        }
    }
    xmlHttp.open('HEAD',window.location.href.toString(),false);
    xmlHttp.setRequestHeader("Content-Type", "text/html");
    xmlHttp.send('');
    return xmlHttp.getResponseHeader("Date");
    
    
}

var st = srvTime();
var date = new Date(st);  


ahora = date;

//var ahora = new Date();
var sorteo = new Date(2017, 1, 25, 20);
var dif = sorteo - ahora;

var horas = Math.floor(dif / 1000 / 60 / 60, 0 );
var minutos = Math.floor(((dif / 1000 / 60 / 60) - horas) * 60,0);
var segundos = Math.floor(((((dif / 1000 / 60 / 60) - horas) * 60) - minutos) * 60,0);



function imprimirCuentaAtras(){
  var strhoras = horas < 10 ? "0"+horas.toString():horas.toString();
  var strminutos = minutos < 10 ? "0"+minutos.toString():minutos.toString();
  var strsegundos = segundos < 10 ? "0"+segundos.toString():segundos.toString();
	return strhoras +":"+ strminutos +":"+ strsegundos
}

function ticktock(){


	if(segundos>0){
  	segundos--;
  }
  else
  {
  	segundos=59;
    if(minutos>0)
    {
    	minutos--;
    }
    else
    {
    	minutos=59;
      if(horas>0)
      {
      	horas--;	
      }
    }
  }
  
  if(horas==0&&minutos==0&&segundos==0)
  {
  	stopCuenta();
  }
  
  
}

var miCuenta;

function startCuenta(){
    
   clearInterval(miCuenta); 
    
    
   miCuenta = setInterval(
   function(){ 
   ticktock(); 
   document.getElementById("cuenta_atras").innerHTML=imprimirCuentaAtras();
   }, 1000);   
    
}

function stopCuenta(){
	clearInterval(miCuenta);
} 

var confetti_global;

            var retina = window.devicePixelRatio,

                // Math shorthands
                PI = Math.PI,
                sqrt = Math.sqrt,
                round = Math.round,
                random = Math.random,
                cos = Math.cos,
                sin = Math.sin,

                // Local WindowAnimationTiming interface
                rAF = window.requestAnimationFrame,
                cAF = window.cancelAnimationFrame || window.cancelRequestAnimationFrame,
                _now = Date.now || function () {return new Date().getTime();};

            // Local WindowAnimationTiming interface polyfill
            (function (w) {
              /**
                                            * Fallback implementation.
                                            */
              var prev = _now();
              function fallback(fn) {
                var curr = _now();
                var ms = Math.max(0, 16 - (curr - prev));
                var req = setTimeout(fn, ms);
                prev = curr;
                return req;
              }

              /**
                                            * Cancel.
                                            */
              var cancel = w.cancelAnimationFrame
              || w.webkitCancelAnimationFrame
              || w.clearTimeout;

              rAF = w.requestAnimationFrame
              || w.webkitRequestAnimationFrame
              || fallback;

              cAF = function(id){
                cancel.call(w, id);
              };
            }(window));

            document.addEventListener("DOMContentLoaded", function() {
              var speed = 50,
                  duration = (1.0 / speed),
                  confettiRibbonCount = 10,
                  ribbonPaperCount = 10,
                  ribbonPaperDist = 8.0,
                  ribbonPaperThick = 8.0,
                  confettiPaperCount = 10,
                  DEG_TO_RAD = PI / 180,
                  RAD_TO_DEG = 180 / PI,
                  colors = [
                    ["#df0049", "#660671"],
                    ["#00e857", "#005291"],
                    ["#2bebbc", "#05798a"],
                    ["#ffd200", "#b06c00"]
                  ];

              function Vector2(_x, _y) {
                this.x = _x, this.y = _y;
                this.Length = function() {
                  return sqrt(this.SqrLength());
                }
                this.SqrLength = function() {
                  return this.x * this.x + this.y * this.y;
                }
                this.Add = function(_vec) {
                  this.x += _vec.x;
                  this.y += _vec.y;
                }
                this.Sub = function(_vec) {
                  this.x -= _vec.x;
                  this.y -= _vec.y;
                }
                this.Div = function(_f) {
                  this.x /= _f;
                  this.y /= _f;
                }
                this.Mul = function(_f) {
                  this.x *= _f;
                  this.y *= _f;
                }
                this.Normalize = function() {
                  var sqrLen = this.SqrLength();
                  if (sqrLen != 0) {
                    var factor = 1.0 / sqrt(sqrLen);
                    this.x *= factor;
                    this.y *= factor;
                  }
                }
                this.Normalized = function() {
                  var sqrLen = this.SqrLength();
                  if (sqrLen != 0) {
                    var factor = 1.0 / sqrt(sqrLen);
                    return new Vector2(this.x * factor, this.y * factor);
                  }
                  return new Vector2(0, 0);
                }
              }
              Vector2.Lerp = function(_vec0, _vec1, _t) {
                return new Vector2((_vec1.x - _vec0.x) * _t + _vec0.x, (_vec1.y - _vec0.y) * _t + _vec0.y);
              }
              Vector2.Distance = function(_vec0, _vec1) {
                return sqrt(Vector2.SqrDistance(_vec0, _vec1));
              }
              Vector2.SqrDistance = function(_vec0, _vec1) {
                var x = _vec0.x - _vec1.x;
                var y = _vec0.y - _vec1.y;
                return (x * x + y * y + z * z);
              }
              Vector2.Scale = function(_vec0, _vec1) {
                return new Vector2(_vec0.x * _vec1.x, _vec0.y * _vec1.y);
              }
              Vector2.Min = function(_vec0, _vec1) {
                return new Vector2(Math.min(_vec0.x, _vec1.x), Math.min(_vec0.y, _vec1.y));
              }
              Vector2.Max = function(_vec0, _vec1) {
                return new Vector2(Math.max(_vec0.x, _vec1.x), Math.max(_vec0.y, _vec1.y));
              }
              Vector2.ClampMagnitude = function(_vec0, _len) {
                var vecNorm = _vec0.Normalized;
                return new Vector2(vecNorm.x * _len, vecNorm.y * _len);
              }
              Vector2.Sub = function(_vec0, _vec1) {
                return new Vector2(_vec0.x - _vec1.x, _vec0.y - _vec1.y, _vec0.z - _vec1.z);
              }

              function EulerMass(_x, _y, _mass, _drag) {
                this.position = new Vector2(_x, _y);
                this.mass = _mass;
                this.drag = _drag;
                this.force = new Vector2(0, 0);
                this.velocity = new Vector2(0, 0);
                this.AddForce = function(_f) {
                  this.force.Add(_f);
                }
                this.Integrate = function(_dt) {
                  var acc = this.CurrentForce(this.position);
                  acc.Div(this.mass);
                  var posDelta = new Vector2(this.velocity.x, this.velocity.y);
                  posDelta.Mul(_dt);
                  this.position.Add(posDelta);
                  acc.Mul(_dt);
                  this.velocity.Add(acc);
                  this.force = new Vector2(0, 0);
                }
                this.CurrentForce = function(_pos, _vel) {
                  var totalForce = new Vector2(this.force.x, this.force.y);
                  var speed = this.velocity.Length();
                  var dragVel = new Vector2(this.velocity.x, this.velocity.y);
                  dragVel.Mul(this.drag * this.mass * speed);
                  totalForce.Sub(dragVel);
                  return totalForce;
                }
              }

              function ConfettiPaper(_x, _y) {
                this.pos = new Vector2(_x, _y);
                this.rotationSpeed = (random() * 600 + 800);
                this.angle = DEG_TO_RAD * random() * 360;
                this.rotation = DEG_TO_RAD * random() * 360;
                this.cosA = 1.0;
                this.size = 5.0;
                this.oscillationSpeed = (random() * 1.5 + 0.5);
                this.xSpeed = 40.0;
                this.ySpeed = (random() * 60 + 50.0);
                this.corners = new Array();
                this.time = random();
                var ci = round(random() * (colors.length - 1));
                this.frontColor = colors[ci][0];
                this.backColor = colors[ci][1];
                for (var i = 0; i < 4; i++) {
                  var dx = cos(this.angle + DEG_TO_RAD * (i * 90 + 45));
                  var dy = sin(this.angle + DEG_TO_RAD * (i * 90 + 45));
                  this.corners[i] = new Vector2(dx, dy);
                }
                this.Update = function(_dt) {
                  this.time += _dt;
                  this.rotation += this.rotationSpeed * _dt;
                  this.cosA = cos(DEG_TO_RAD * this.rotation);
                  this.pos.x += cos(this.time * this.oscillationSpeed) * this.xSpeed * _dt
                  this.pos.y += this.ySpeed * _dt;
                  if (this.pos.y > ConfettiPaper.bounds.y) {
                    this.pos.x = random() * ConfettiPaper.bounds.x;
                    this.pos.y = 0;
                  }
                }
                this.Draw = function(_g) {
                  if (this.cosA > 0) {
                    _g.fillStyle = this.frontColor;
                  } else {
                    _g.fillStyle = this.backColor;
                  }
                  _g.beginPath();
                  _g.moveTo((this.pos.x + this.corners[0].x * this.size) * retina, (this.pos.y + this.corners[0].y * this.size * this.cosA) * retina);
                  for (var i = 1; i < 4; i++) {
                    _g.lineTo((this.pos.x + this.corners[i].x * this.size) * retina, (this.pos.y + this.corners[i].y * this.size * this.cosA) * retina);
                  }
                  _g.closePath();
                  _g.fill();
                }
              }
              ConfettiPaper.bounds = new Vector2(0, 0);

              function ConfettiRibbon(_x, _y, _count, _dist, _thickness, _angle, _mass, _drag) {
                this.particleDist = _dist;
                this.particleCount = _count;
                this.particleMass = _mass;
                this.particleDrag = _drag;
                this.particles = new Array();
                var ci = round(random() * (colors.length - 1));
                this.frontColor = colors[ci][0];
                this.backColor = colors[ci][1];
                this.xOff = (cos(DEG_TO_RAD * _angle) * _thickness);
                this.yOff = (sin(DEG_TO_RAD * _angle) * _thickness);
                this.position = new Vector2(_x, _y);
                this.prevPosition = new Vector2(_x, _y);
                this.velocityInherit = (random() * 2 + 4);
                this.time = random() * 100;
                this.oscillationSpeed = (random() * 2 + 2);
                this.oscillationDistance = (random() * 40 + 40);
                this.ySpeed = (random() * 40 + 80);
                for (var i = 0; i < this.particleCount; i++) {
                  this.particles[i] = new EulerMass(_x, _y - i * this.particleDist, this.particleMass, this.particleDrag);
                }
                this.Update = function(_dt) {
                  var i = 0;
                  this.time += _dt * this.oscillationSpeed;
                  this.position.y += this.ySpeed * _dt;
                  this.position.x += cos(this.time) * this.oscillationDistance * _dt;
                  this.particles[0].position = this.position;
                  var dX = this.prevPosition.x - this.position.x;
                  var dY = this.prevPosition.y - this.position.y;
                  var delta = sqrt(dX * dX + dY * dY);
                  this.prevPosition = new Vector2(this.position.x, this.position.y);
                  for (i = 1; i < this.particleCount; i++) {
                    var dirP = Vector2.Sub(this.particles[i - 1].position, this.particles[i].position);
                    dirP.Normalize();
                    dirP.Mul((delta / _dt) * this.velocityInherit);
                    this.particles[i].AddForce(dirP);
                  }
                  for (i = 1; i < this.particleCount; i++) {
                    this.particles[i].Integrate(_dt);
                  }
                  for (i = 1; i < this.particleCount; i++) {
                    var rp2 = new Vector2(this.particles[i].position.x, this.particles[i].position.y);
                    rp2.Sub(this.particles[i - 1].position);
                    rp2.Normalize();
                    rp2.Mul(this.particleDist);
                    rp2.Add(this.particles[i - 1].position);
                    this.particles[i].position = rp2;
                  }
                  if (this.position.y > ConfettiRibbon.bounds.y + this.particleDist * this.particleCount) {
                    this.Reset();
                  }
                }
                this.Reset = function() {
                  this.position.y = -random() * ConfettiRibbon.bounds.y;
                  this.position.x = random() * ConfettiRibbon.bounds.x;
                  this.prevPosition = new Vector2(this.position.x, this.position.y);
                  this.velocityInherit = random() * 2 + 4;
                  this.time = random() * 100;
                  this.oscillationSpeed = random() * 2.0 + 1.5;
                  this.oscillationDistance = (random() * 40 + 40);
                  this.ySpeed = random() * 40 + 80;
                  var ci = round(random() * (colors.length - 1));
                  this.frontColor = colors[ci][0];
                  this.backColor = colors[ci][1];
                  this.particles = new Array();
                  for (var i = 0; i < this.particleCount; i++) {
                    this.particles[i] = new EulerMass(this.position.x, this.position.y - i * this.particleDist, this.particleMass, this.particleDrag);
                  }
                }
                this.Draw = function(_g) {
                  for (var i = 0; i < this.particleCount - 1; i++) {
                    var p0 = new Vector2(this.particles[i].position.x + this.xOff, this.particles[i].position.y + this.yOff);
                    var p1 = new Vector2(this.particles[i + 1].position.x + this.xOff, this.particles[i + 1].position.y + this.yOff);
                    if (this.Side(this.particles[i].position.x, this.particles[i].position.y, this.particles[i + 1].position.x, this.particles[i + 1].position.y, p1.x, p1.y) < 0) {
                      _g.fillStyle = this.frontColor;
                      _g.strokeStyle = this.frontColor;
                    } else {
                      _g.fillStyle = this.backColor;
                      _g.strokeStyle = this.backColor;
                    }
                    if (i == 0) {
                      _g.beginPath();
                      _g.moveTo(this.particles[i].position.x * retina, this.particles[i].position.y * retina);
                      _g.lineTo(this.particles[i + 1].position.x * retina, this.particles[i + 1].position.y * retina);
                      _g.lineTo(((this.particles[i + 1].position.x + p1.x) * 0.5) * retina, ((this.particles[i + 1].position.y + p1.y) * 0.5) * retina);
                      _g.closePath();
                      _g.stroke();
                      _g.fill();
                      _g.beginPath();
                      _g.moveTo(p1.x * retina, p1.y * retina);
                      _g.lineTo(p0.x * retina, p0.y * retina);
                      _g.lineTo(((this.particles[i + 1].position.x + p1.x) * 0.5) * retina, ((this.particles[i + 1].position.y + p1.y) * 0.5) * retina);
                      _g.closePath();
                      _g.stroke();
                      _g.fill();
                    } else if (i == this.particleCount - 2) {
                      _g.beginPath();
                      _g.moveTo(this.particles[i].position.x * retina, this.particles[i].position.y * retina);
                      _g.lineTo(this.particles[i + 1].position.x * retina, this.particles[i + 1].position.y * retina);
                      _g.lineTo(((this.particles[i].position.x + p0.x) * 0.5) * retina, ((this.particles[i].position.y + p0.y) * 0.5) * retina);
                      _g.closePath();
                      _g.stroke();
                      _g.fill();
                      _g.beginPath();
                      _g.moveTo(p1.x * retina, p1.y * retina);
                      _g.lineTo(p0.x * retina, p0.y * retina);
                      _g.lineTo(((this.particles[i].position.x + p0.x) * 0.5) * retina, ((this.particles[i].position.y + p0.y) * 0.5) * retina);
                      _g.closePath();
                      _g.stroke();
                      _g.fill();
                    } else {
                      _g.beginPath();
                      _g.moveTo(this.particles[i].position.x * retina, this.particles[i].position.y * retina);
                      _g.lineTo(this.particles[i + 1].position.x * retina, this.particles[i + 1].position.y * retina);
                      _g.lineTo(p1.x * retina, p1.y * retina);
                      _g.lineTo(p0.x * retina, p0.y * retina);
                      _g.closePath();
                      _g.stroke();
                      _g.fill();
                    }
                  }
                }
                this.Side = function(x1, y1, x2, y2, x3, y3) {
                  return ((x1 - x2) * (y3 - y2) - (y1 - y2) * (x3 - x2));
                }
              }
              ConfettiRibbon.bounds = new Vector2(0, 0);
              confetti = {};
              confetti.Context = function(id) {
                var i = 0;
                var canvas = document.getElementById(id);
                var canvasParent = canvas.parentNode;
                var canvasWidth = canvasParent.offsetWidth;
                var canvasHeight = canvasParent.offsetHeight;
                canvas.width = canvasWidth * retina;
                canvas.height = canvasHeight * retina;
                var context = canvas.getContext('2d');
                var interval = null;
                var confettiRibbons = new Array();
                ConfettiRibbon.bounds = new Vector2(canvasWidth, canvasHeight);
                for (i = 0; i < confettiRibbonCount; i++) {
                  confettiRibbons[i] = new ConfettiRibbon(random() * canvasWidth, -random() * canvasHeight * 2, ribbonPaperCount, ribbonPaperDist, ribbonPaperThick, 45, 1, 0.05);
                }
                var confettiPapers = new Array();
                ConfettiPaper.bounds = new Vector2(canvasWidth, canvasHeight);
                for (i = 0; i < confettiPaperCount; i++) {
                  confettiPapers[i] = new ConfettiPaper(random() * canvasWidth, random() * canvasHeight);
                }
                this.resize = function() {
                  canvasWidth = canvasParent.offsetWidth;
                  canvasHeight = canvasParent.offsetHeight;
                  canvas.width = canvasWidth * retina;
                  canvas.height = canvasHeight * retina;
                  ConfettiPaper.bounds = new Vector2(canvasWidth, canvasHeight);
                  ConfettiRibbon.bounds = new Vector2(canvasWidth, canvasHeight);
                }
                this.start = function() {
                  this.stop()
                  var context = this;
                  this.update();
                }
                this.stop = function() {
                  cAF(this.interval);
                }
                this.update = function() {
                  var i = 0;
                  context.clearRect(0, 0, canvas.width, canvas.height);
                  for (i = 0; i < confettiPaperCount; i++) {
                    confettiPapers[i].Update(duration);
                    confettiPapers[i].Draw(context);
                  }
                  for (i = 0; i < confettiRibbonCount; i++) {
                    confettiRibbons[i].Update(duration);
                    confettiRibbons[i].Draw(context);
                  }
                  this.interval = rAF(function() {
                    confetti.update();
                  });
                }
              }
              var confetti = new confetti.Context('confetti');
              confetti_global = confetti;
              //confetti.start();
              window.addEventListener('resize', function(event){
                confetti.resize();
              });
            });            
           

    
    
     
    
</script>
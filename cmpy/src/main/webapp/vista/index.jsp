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
        <meta name="fragment" content="!">
        <meta name="description" content="Compra y Vende lo que deseas, crea tu propia tienda y más...">
        <meta name="keywords" content="HTML,CSS,XML,JavaScript,compra,compras,venta,ventas,vender,comprar,articulos,el salvador,ahuchapan,
              santa ana,sonsonate,la libertad,san salvador,chalatenango,cuscatlan,cabañas,san miguel,san francisco,la union,morazán,
              san vicente,la paz,503,telefonos,celulares,laptops,juegos,carros,civic,toyota,nissan,play,playstation,ps3,ps4,lcd,pantallas,
              vivienda,ropa,calzado,deportes">
        <meta name="author" content="TecnoBitz">
        <meta name="fragment" content="!">
        <script> window.prerenderReady = false; </script>
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

        <div id="fb-root"></div>
        
        <div class="container-fluid" style="margin-bottom:20px">
            <div class="row" id="header-page">
                
                <div class="container-fluid">
                <div class="row" id="menu-page">

                    <nav id="navBar" class="navbar navbar-default navbar-static-top" role="navigation" ng-controller="menuPrincipalController" style="opacity:0.9">
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

                                <button id="botonInfo" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" ng-click="infoCollapsed = !infoCollapsed" >                                                   
                                    <span class="glyphicon glyphicon-info-sign"></span>
                                    Info 
                                </button>

                                <button id="botonVisitas" class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" uib-tooltip="visitas recibidas" tooltip-placement="bottom">
                                    <span class="glyphicon glyphicon-eye-open"></span>
                                    {{visitas}}                        
                                </button>

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
                                            <li ng-if="localAccount" ng-click="open('editProfileModal.html')"><a href="#">Editar Perfil</a></li>
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
                                    <li ng-if="localAccount && authenticated && !navCollapsed"><a href="#" ng-click="open('editProfileModal.html'); cancel()">Editar Perfil</a></li>
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
                        <!--<div class="fb-share-button" data-layout="button_count" data-size="large" data-mobile-iframe="true" data-href="https://www.comprameya.com"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.comprameya.com%2F&amp;src=sdkpreparse">Compartir</a></div>-->
                    

                       <%--Nuevo Share--%>
                        
                        <div style="padding:10px">
                            <a class="btn btn-default btn-lg" href="#" ui-sref="home" style="padding: 5px 10px 6px 10px" title="Inicio" >
                                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-social-icon btn-facebook" href="http://facebook.com/sharer.php?u={{rutaactualencode()}}" title="Comparte en facebook" target="_blank">
                                <span class="fa fa-facebook"></span>
                             </a>
                            <a class="btn btn-social-icon btn-twitter" href="http://twitter.com/home?status=ComprameYa%20{{rutaactualencode()}}%20@ComprameYa" title="Comparte en twitter" target="_blank">
                                <span class="fa fa-twitter"></span>
                             </a> 
                        </div>
                    
                    </nav>               



                </div>
                <div class="row" style="padding-bottom:8px" >
                    <div class="col-md-12">
                        <a href="/" ui-sref='home' ui-sref-opts="{reload:true}">
                            <img style="height: 125px" ng-src="/resources/images/logo_normal_trans_sm.png" class="img-responsive center-block img-header" alt="$ComprameYa!">
                        </a>                 
                    </div>
                    <div class="col-md-12">
                        <form class="navbar-form navbar-center" role="search">
                           <div class="input-group" style="margin: 0 auto; width:80%">
                               <input type="text" class="form-control input-lg " placeholder="Buscar producto..." ng-model="terminoBusqueda" ng-model-options="{debounce: 1000}">
                               <span class="input-group-btn">
                                   <button class="btn btn-default btn-lg" type="button" ui-sref-opts="{reload:true}" ui-sref="home.busqueda({terminoBusqueda: terminoBusqueda})"><span class="glyphicon glyphicon-search"></span></button>
                               </span>
                           </div>                    
                       </form>               
                    </div>
                    <div class="col-md-12">
                        <button type="button" class="btn btn-default pull-left" aria-label="Menu categorias" onclick="openNav()">
                            <span class="glyphicon glyphicon-th" aria-hidden="true"></span>&nbsp;Categorias
                        </button>
                        <button ng-show="!authenticated" value="VenderYa!" type="button" class="btn btn-success pull-right" ng-click="open('loginModal.html')">Crear Anuncio</button>
                        <button ng-show="authenticated" value="VenderYa!" type="submit" class="btn btn-success pull-right" ng-click="open('venderModal.html')">
                            Crear Anuncio
                        </button>
                    </div>

                </div>
                </div>
            </div>
        </div>
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
        <script type="text/ng-template" id="editProfileModal.html">
             <div class="modal-body" ng-controller="profileController" >
                <form class="form-horizontal">
                  <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Nombre</label>
                    <div class="col-sm-10">
                      <div class="input-group">
                        <input type="text" ng-disabled="estado_nombre" ng-model="nombre" class="form-control" placeholder="Ejemplo: Juan Carlos" aria-describedby="basic-addon1">
                        <span class="input-group-btn">
                          <button class="btn btn-default" ng-click="estado_nombre=!estado_nombre;resetNombre()" type="button">{{etiqueta_nombre}}</button>
                        </span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Apellido</label>
                    <div class="col-sm-10">
                      <div class="input-group">
                        <input type="text" ng-disabled="estado_apellido" ng-model="apellido" class="form-control" placeholder="Ejemplo: Perez" aria-describedby="basic-addon1">
                        <span class="input-group-btn">
                          <button class="btn btn-default" ng-click="estado_apellido=!estado_apellido;resetApellido()" type="button">{{etiqueta_apellido}}</button>
                        </span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Telefono</label>
                    <div class="col-sm-10">
                      <div class="input-group">
                        <input type="text" id="telperfil" ng-disabled="estado_telefono" ng-model="telefono" class="form-control" placeholder="Ejemplo: 77778888" aria-describedby="basic-addon1">
                        <span class="input-group-btn">
                          <button class="btn btn-default" ng-click="estado_telefono=!estado_telefono;resetTelefono()" type="button">{{etiqueta_telefono}}</button>
                        </span>
                      </div>
                    </div>
                  </div>

                   <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Sexo</label>
                    <div class="col-sm-10">
                          <label class="radio-inline">
                            <input type="radio" ng-model="sexo" name="inlineRadioOptions" id="inlineRadio1" value="1"> Hombre
                          </label>
                          <label class="radio-inline">
                            <input type="radio" ng-model="sexo" name="inlineRadioOptions" id="inlineRadio2" value="0"> Mujer
                          </label>
                    </div>
                  </div>
                   <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Nacimiento</label>
                    <div class="col-sm-10">  
                        <select name="dia" ng-model="dia" class="fecha_account cuenta-save-1 text ui-corner-all form-input-text box-shadow-soft">
                          <option value="0" selected="selected">Día</option>
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                          <option value="6">6</option>
                          <option value="7">7</option>
                          <option value="8">8</option>
                          <option value="9">9</option>
                          <option value="10">10</option>
                          <option value="11">11</option>
                          <option value="12">12</option>
                          <option value="13">13</option>
                          <option value="14">14</option>
                          <option value="15">15</option>
                          <option value="16">16</option>
                          <option value="17">17</option>
                          <option value="18">18</option>
                          <option value="19">19</option>
                          <option value="20">20</option>
                          <option value="21">21</option>
                          <option value="22">22</option>
                          <option value="23">23</option>
                          <option value="24">24</option>
                          <option value="25">25</option>
                          <option value="26">26</option>
                          <option value="27">27</option>
                          <option value="28">28</option>
                          <option value="29">29</option>
                          <option value="30">30</option>
                          <option value="31">31</option>
                      </select>
                      <select name="mes" ng-model="mes" class="fecha_account cuenta-save-1 text ui-corner-all form-input-text box-shadow-soft">
                            <option value="0" selected="selected">Mes</option>
                            <option value="1">Enero</option>
                            <option value="2">Febrero</option>
                            <option value="3">Marzo</option>
                            <option value="4">Abril</option>
                            <option value="5">Mayo</option>
                            <option value="6">Junio</option>
                            <option value="7">Julio</option>
                            <option value="8">Agosto</option>
                            <option value="9">Septiembre</option>
                            <option value="10">Octubre</option>
                            <option value="11">Noviembre</option>
                            <option value="12">Diciembre</option>
                       </select>  
                        <select name="anio" ng-model="anio" class="fecha_account cuenta-save-1 text ui-corner-all form-input-text box-shadow-soft">
                            <option value="0" selected="selected">Año</option>
                            <option ng-repeat="anio in anios" value="{{anio}}">{{anio}}</option>
                        </select>
                        
                    </div>
             
                  </div>
                  <div class="alert alert-danger alert-dismissible fade in" role="alert" ng-show="!val_datos">
                    {{error}}
                  </div>

                </form>
                <button type="button" ng-click="updateProfile()" class="btn btn-success" uib-tooltip="Actualizar perfil" tooltip-placement="bottom">
                    Actualizar
                </button>
                <button ng-click="cancel()" type="button" class="btn btn-warning" uib-tooltip="Cancelar actualizacion" tooltip-placement="bottom">
                    Cancelar
                </button>         
            </div>
            
        </script>
        
        <script type="text/javascript">
            function openNav() {
                document.getElementById("mySidenav").style.width = "250px";
            }

            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
            }
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/ProfileController.js" ></script>   
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/ProfileService.js" ></script>   
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sorteo/confeti.js" ></script>
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
        <link href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-social/bootstrap-social.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/commentbox.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/publicaciones.css" rel="stylesheet">--%>


        <%-- PARA PASAR A PRODUCCIÓN USAR ESTOS SCRIPTS Y COMENTAR LOS ANTERIORES --%>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/min/app.min.js?1.0.2" ></script>
        <link href="${pageContext.request.contextPath}/resources/min/css/style.min.css?1.0.0" rel="stylesheet" type="text/css" media='all'>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.css" rel="stylesheet">
        
    </body>
    <canvas height='1' id='confetti' width='1' ng-show="confeti"></canvas>
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3 menu3" style="min-height:400px;background-color:#232f3e;">
         
            </div>            
            <div class="col-md-3 menu3" style="min-height:400px;background-color:#232f3e;">
                <h4 style="color:white"><b>Paginas Amigas</b></h4>
                <ul>
                    <li><a href="https://www.facebook.com/Studio-F64-111191842731695">Estudio F64</a></li>
                </ul>                 
            </div>
            <div class="col-md-3 menu3" style="min-height:400px;background-color:#232f3e;">
                <h4 style="color:white"><b>Enlaces</b></h4>
                <ul>
                    <li><a href="/contratar_tienda#publicacionesProductos">Negocios</a></li>
                    <li><a href="https://youtu.be/cmfOtIizd70">Video de ayuda</a></li>
                    <li><a href="/terminos#publicacionesProductos">Terminos</a></li>
                    <li><a href="https://www.facebook.com/PageComprameYa">Fanpage</a></li>
                     <li><a href="/about#publicacionesProductos">Nosotros</a></li>
                </ul>                     
            </div>
            <div  class="col-md-3 menu3" style="min-height:400px;background-color:#232f3e;">

            </div>
        </div>
    </div>
</html>
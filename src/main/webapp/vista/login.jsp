<%-- 
    Document   : login
    Created on : 07-15-2015, 09:24:11 AM
    Author     : jzelaya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<c:url var="rootURL" value="/"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Login - ComprameYa!</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a href="/cmpy" class="pull-left">
                  <img src="${pageContext.request.contextPath}/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="$ComprameYa!">
              </a>
            </div>
            <div id="navbar" class="navbar-right navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li>
              </ul>
            </div>
            <form class="navbar-form navbar-left" role="search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Buscar Productos...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">BuscarYa!</button>
                    </span>
                </div>
                <!--<button type="submit" class="btn btn-default">Submit</button>-->                
              </form>
          </div>
        </nav>
              
        <div class="container-fluid">
<!--          <div class="row">
            <div class="col-md-4 min_otros marcar container">
                <img src="${pageContext.request.contextPath}/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="imagen aqui">
            </div>
            <div class="col-md-8 min_otros marcar">Menu</div>
          </div>-->
          <div class="row">
            <div class="col-md-2 min_cont marcar">
                <div class="panel panel-default min_cont">
                    <div class="panel-heading">Categorías</div>
                    <div class="panel-body">
                      Listado de categorías
                    </div>
                </div>
            </div>              
            <div class="col-md-8 min_cont marcar">
                <div class="panel panel-default min_cont">                    
                    <div class="panel-heading">
                        <p>                            
                           Publicaciones
                           <button value="VenderYa!" type="submit" class="btn btn-success pull-right"  data-toggle="modal" data-target="#venderModal">
                            VenderYa!
                           </button>                            
                        </p>
                    </div>  
                    
                    <div class="panel-body">
                      <%@include file="loginForm.jsp" %>      
                    </div>
                </div>
            </div>
            <div class="col-md-2 min_cont marcar">
                
                <div class="panel panel-default min_cont">
                    <div class="panel-heading">Anuncios Pagados</div>
                    <div class="panel-body">
                        <div class="row">                          
                            <c:forEach items="${publicaciones}" var="publicacion">   

                            <div class="thumbnail col-lg-12">
                            <c:forEach items="${publicacion.recursoList}" var="recurso"> 
                            <img src="/cmpy/images/getImage/${recurso.ruta}" alt="imagen aqui">
                            <h3><c:out value='${publicacion.titulo}' /></h3>
                            <p><c:out value='${publicacion.descripcion}' /></p>
                            </c:forEach>                                
                            </div>

                            </c:forEach>            

                        </div>
                    </div>
                </div>
                
                
            </div>
          </div>
          
              <footer class="footer">
                <div class="container">
                <p class="pull-right"><a href="#">Subir</a></p>
                <p class="text-muted">&copy; 2015 TecnoGeek. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
                </div>
              </footer>
              
        </div> 
        
        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"/></script>                
    </body>
</html>

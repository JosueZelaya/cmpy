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
<html ng-app="appComprameYa">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ComprameYa!</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/inputFile/fileinput.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">     
        
        <%-- css de produccion --%>
        
        <%--<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/inputFile/fileinput.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">--%>     
        
        
    </head>
    <body>
        
        <%@include file="common/menuPrincipal.jsp" %>
        
        <div class="container-fluid">
<!--          <div class="row">
            <div class="col-md-4 min_otros marcar container">
                <img src="${pageContext.request.contextPath}/resources/images/logo_normal_trans_sm.png" class="img-responsive" alt="imagen aqui">
            </div>
            <div class="col-md-8 min_otros marcar">Menu</div>
          </div>-->
          <div class="row">
            <%@include file="common/menuCategorias.jsp" %>  
            <%@include file="common/panelPublicacionesProductos.jsp" %>  
            <%@include file="common/panelPublicacionesEmpresas.jsp" %>  
            
          </div>
            <%@include file="common/footer.jsp" %>
            
        </div>
        
        <c:choose>
            <c:when test="${username=='anonymousUser'}">      
                <div class="modal fade" id="loginModal">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title">Iniciar Sesion!</h4>
                        </div>
                        <div class="modal-body">

                          <%@include file="common/loginForm.jsp" %>

                        </div>      

                      </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->  
            </c:when>    
            <c:otherwise>    
                <div class="modal fade" id="anunciarModal">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title">Anunciate y Vende mas!</h4>
                        </div>
                        <div class="modal-body">
                            <%@include file="common/anuncioForm.jsp" %> 
                        </div>      

                      </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal --> 
                
                <div class="modal fade" id="venderModal">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title">Vende Ya!</h4>
                        </div>
                        <div class="modal-body">
                            <%@include file="common/venderForm.jsp" %> 
                        </div>      

                      </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal --> 
            </c:otherwise>
        </c:choose>   
                
                <%@include file="common/mapaUbicacion.jsp" %>
        
        <!-- Cargamos los scripts --> 
<!--        <script type="text/javascript" src="//raw.github.com/lodash/lodash/3.10.1/lodash.min.js"></script>
        <script type="text/javascript" src='//maps.googleapis.com/maps/api/js?sensor=false'></script>
        <script type="text/javascript" src="//raw.github.com/angular-ui/angular-google-maps/master/dist/angular-google-maps.js"></script>-->
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/frameworks/angular.min1.4.7.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/frameworks/angular-animate1.4.5.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-angular/ui-bootstrap-tpls-0.13.3.min.js"/></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/2.4.1/lodash.min.js" type="text/javascript"></script>
        <script src="http://cdn.rawgit.com/nmccready/angular-simple-logger/0.0.1/dist/index.js"></script>
        <script src="${pageContext.request.contextPath}/resources/frameworks/angular-google-maps-2.2.1.js" type="text/javascript"></script>
        <script src='https://maps.googleapis.com/maps/api/js?sensor=false'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/catControllers.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/mapControllers.js" ></script>
        <%-- js de produccion --%>
        
        <%--<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular-animate.min.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.13.3.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/catControllers.js" ></script>--%>

        


   <style>
        .angular-google-map-container { height: 400px }
    </style>
        
    </body>
</html>
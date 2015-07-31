<%-- 
    Document   : boot
    Created on : 24-may-2015, 8:01:11
    Author     : genaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ComprameYa!</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/inputFile/fileinput.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">        
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
        
        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"/></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/inputFile/fileinput.min.js"/></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js"/></script>        
    </body>
</html>
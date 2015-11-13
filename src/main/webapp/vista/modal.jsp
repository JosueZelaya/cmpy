<%-- 
    Document   : modal
    Created on : 05-nov-2015, 19:01:25
    Author     : jzelaya
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
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">     
    </head>
    <body>

        <%@include file="common/menuPrincipal.jsp" %>

        <div class="container-fluid">            
            <div class="row">
                <%@include file="common/menuCategorias.jsp" %>  
                <%@include file="common/panelPublicacionesProductos.jsp" %>  
                <%@include file="common/panelPublicacionesEmpresas.jsp" %>  

            </div>
            <%@include file="common/footer.jsp" %>

        </div>

        <c:choose>
            <c:when test="${username=='anonymousUser'}">      
                <%@include file="common/loginModal.jsp" %>
            </c:when>    
            <c:otherwise>    
                <%@include file="common/anunciarModal.jsp" %>
                <%@include file="common/venderModal.jsp" %>
            </c:otherwise>
        </c:choose>

        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/modalController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/catControllers.js" ></script>
        

    </body>
</html>

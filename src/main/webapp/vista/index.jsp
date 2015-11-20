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
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <style type="text/css">
            [ng\:cloak], [ng-cloak], .ng-cloak {
                display: none !important;
            }
        </style>
    </head>
    <body ng-cloak class="ng-cloak">

        <div ui-view="menuPrincipal">

        </div>

        <div ui-view="menuCategorias">

        </div>

        <div ui-view="panelPublicacionesProductos">

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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/lodash/lodash.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-simple-logger/dist/angular-simple-logger.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-google-maps/dist/angular-google-maps.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/navigation.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/catControllers.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/modalController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/mapControllers.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/menuPrincipalController.js" ></script>


    </body>
</html>

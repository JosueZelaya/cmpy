<%-- 
    Document   : error
    Created on : 06-06-2016, 06:53:51 PM
    Author     : alexander
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Registrarse</title>    
        <link href="${pageContext.request.contextPath}/resources/images/favicon_trans.png" rel="icon" type="image/png">
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/registration/index.css" rel="stylesheet">
    </head>
    <body style="margin-top:0px;">

        <%@ include file="common/menuPrincipal.jsp" %>

        <div class="container">

            <p style="text-align: center; color: red">
                <h1>${mensaje}</h1>
            </p>                       

        </div>

    </body>
</html>
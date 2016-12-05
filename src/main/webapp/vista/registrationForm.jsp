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
    </head>
    <body>

        <%@ include file="common/menuPrincipal.jsp" %>

        <div class="container">

            <div class="page-header">
                <h1>Registrarse</h1>
            </div>

            <a class="btn btn-block btn-social btn-facebook" href="${pageContext.request.contextPath}/auth/facebook">
                <img height="28" width="28" src="${pageContext.request.contextPath}/resources/images/facebook-icon.png" title="Entrar con Facebook">
                <span class="fa fa-facebook-official"></span>Crear una cuenta usando tu Facebook
            </a>

            <!--
                If the user is anonymous (not logged in), show the registration form.
            -->
            <sec:authorize access="isAnonymous()">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <!-- 
                            Ensure that when the form is submitted, a POST request is send to url
                            '/user/register'.
                        -->
                        <form:form action="${pageContext.request.contextPath}/user/register" commandName="user" method="POST" enctype="multipart/form-data" role="form">
                            <!-- Add CSRF token to the request. -->
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <!--
                                If the user is using social sign in, add the signInProvider
                                as a hidden field.
                            -->
                            <c:if test="${user.signInProvider != null}">
                                <form:hidden path="signInProvider"/>
                            </c:if>

                            <c:if test="${user.signInProvider != null}">
                                <img src="${user.imageUrl}" />
                            </c:if>

                            <c:if test="${user.signInProvider == null}">
                                <div class="form-group">
                                    <form:input cssClass="form-control" path="image" id="user-image" name="multipleFiles" type="file" class="file" multiple="true" data-preview-file-type="any"/>
                                </div> 
                            </c:if>    

                            <div class="row">
                                <div id="form-group-firstName" class="form-group col-lg-4">
                                    <label class="control-label" for="user-firstName">Nombre: </label>
                                    <!--
                                        Add the firstName field to the form and ensure 
                                        that validation errors are shown.
                                    -->
                                    <form:input id="user-firstName" path="firstName" cssClass="form-control"/>
                                    <form:errors id="error-firstName" path="firstName" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div id="form-group-lastName" class="form-group col-lg-4">
                                    <label class="control-label" for="user-lastName">Apellidos: </label>
                                    <!--
                                        Add the lastName field to the form and ensure
                                        that validation errors are shown.
                                    -->
                                    <form:input id="user-lastName" path="lastName" cssClass="form-control"/>
                                    <form:errors id="error-lastName" path="lastName" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="row">
                                <div id="form-group-email" class="form-group col-lg-4">
                                    <label class="control-label" for="user-email">Email: </label>
                                    <!-- 
                                        Add the email field to the form and ensure
                                        that validation errors are shown.
                                    -->
                                    <form:input id="user-email" path="email" cssClass="form-control"/>
                                    <form:errors id="error-email" path="email" cssClass="help-block"/>
                                </div>
                            </div>

                                <div class="row">
                                <div id="form-group-telephone" class="form-group col-lg-4">
                                    <label class="control-label" for="user-telephone">Telefono:</label>
                                    <form:input id="user-telephone" path="telephone" cssClass="form-control"/>
                                </div>
                            </div>
                            <!--
                                If the user is creating a normal user account, add password fields
                                to the form.
                            -->
                            <c:if test="${user.signInProvider == null}">
                                <div class="row">
                                    <div id="form-group-password" class="form-group col-lg-4">
                                        <label class="control-label" for="user-password">password: </label>
                                        <!--
                                            Add the password field to the form and ensure 
                                            that validation errors are shown.
                                        -->
                                        <form:password id="user-password" path="password" cssClass="form-control"/>
                                        <form:errors id="error-password" path="password" cssClass="help-block"/>
                                    </div>
                                </div>
                                <div class="row">
                                    <div id="form-group-passwordVerification" class="form-group col-lg-4">
                                        <label class="control-label" for="user-passwordVerification">comprobacion password: </label>
                                        <!-- 
                                            Add the passwordVerification field to the form and ensure
                                            that validation errors are shown.
                                        -->
                                        <form:password id="user-passwordVerification" path="passwordVerification" cssClass="form-control"/>
                                        <form:errors id="error-passwordVerification" path="passwordVerification" cssClass="help-block"/>
                                    </div>
                                </div>
                            </c:if>
                            <!-- Add the submit button to the form. -->
                            <button type="submit" class="btn btn-default">Registrarse</button>
                        </form:form>
                    </div>
                </div>
            </sec:authorize>
            <!--
                If the user is authenticated, show a help message instead
                of registration form.
            -->
            <sec:authorize access="isAuthenticated()">
                <p>El usuario ya está autenticado...</p>
            </sec:authorize>

        </div>

    </body>
</html>
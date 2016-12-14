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
                                    <label class="control-label" for="user-telephone">Telefono: </label>
                                    <span class="text-muted" style="font-size: 11px;">Se mostrara en las publicaciones - Opcional</span>
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

                            <p>
                                <b>
                                    Al registrarse en $ComprameYa! usted está indicando que acepta los términos de servicio
                                    establecidos para el uso de esta herramienta de comercio electrónico.
                                </b>                                
                            </p>

                            <a href="#openModal">Ver los términos de servicio</a>
                            <br/>
                            <div id="openModal" class="modalDialog">
                                <div>
                                    <a href="#close" title="Close" class="close">X</a>
                                    <h3><b>ComprameYa</b></h3><br/>

                                    <div class="bs-callout bs-callout-primary">
                                        <p>
                                            El sitio Web www.comprameya.com y sus diferentes grupos, conforman un portal electrónico a través del cual los usuarios pueden ofrecer en alquiler y venta todo tipo de bienes muebles.
                                            ComprameYa declara no tener necesariamente algún tipo de vinculación económica y/o empresarial con los usuarios que ofrezcan en alquiler y venta los objetos muebles a través del Portal, ni tampoco ser propietaria o poseedora de alguno de los bienes muebles ofrecidos en venta.
                                            En tal sentido, para el uso del presente sitio Web, los Usuarios se someterán a los términos y condiciones que se indican a continuación.
                                            <br/>                        
                                        </p>
                                    </div>
                                    <div class="bs-callout bs-callout-primary">
                                        <p>
                                            Condiciones generales de uso de ComprameYa, La utilización del presente Portal requiere de la aceptación previa del Usuario de los términos y condiciones incluidas en la presente página Web.
                                        </p>
                                    </div>
                                    <div class="bs-callout bs-callout-primary">
                                        <p>
                                            Veracidad de la información de ComprameYa y las personas naturales y/o responsables por ésta, asumen que la información que se suministra en la presente página Web es verídica. Sin embargo, ComprameYa no se responsabiliza en ningún modo por la total exactitud o confiabilidad de toda o parte de la información contenida en ComprameYa por lo tanto ComprameYa y las personas naturales y/o jurídicas vinculadas a ella no se hacen responsables por los daños o perjuicios que los usuarios del portal puedan sufrir debido a todo o parte del contenido de la información que aparece en la presente página Web.
                                            <br/>
                                            ComprameYa tampoco se responsabiliza por la veracidad, fiabilidad, oportunidad, exactitud o disponibilidad de los bienes publicados y promocionados por los oferentes o por terceros y por tal, tampoco se hará responsable por la correspondencia, comunicación, negociaciones, ofertas o contratación que el Usuario celebre con tal oferente o tercero.
                                        </p>
                                    </div>                
                                    <div class="bs-callout bs-callout-primary">

                                        <h4>Política de privacidad</h4>
                                        <br/>
                                        <p>
                                            Para utilizar los servicios ofrecidos por ComprameYa, los Usuarios deberán facilitar determinados datos de carácter personal; sin embargo, ComprameYa no se responsabiliza si terceros ajenos interceptan o acceden a cierta información o transmisiones de datos, en cuyo caso, el presente Portal o grupo no responderá por la información que sea revelada u obtenida irregularmente.
                                            Así mismo ComprameYa declara que no compartirá su información personal con terceros.
                                        </p>
                                    </div>
                                    <div class="bs-callout bs-callout-primary">

                                        <h4>Propiedad Intelectual</h4>
                                        <br/>
                                        <p>
                                            ComprameYa no otorga ninguna licencia de uso sobre las marcas, nombres comerciales y/o cualquier derecho de propiedad intelectual de terceros que aparezcan o puedan aparecer en la página Web, los cuales son de propiedad de sus respectivos titulares. Dichos derechos de propiedad intelectual podrán ser utilizados por los Usuarios del Portal o consumidores en general únicamente con fines informativos.
                                        </p>
                                    </div>
                                    <div class="bs-callout bs-callout-primary">

                                        <h4>Responsabilidad e Indemnización</h4>
                                        <br/>
                                        <p>
                                            ComprameYa se limita a poner a disposición del usuario un espacio virtual para que éste pueda contactarse con el/los oferente/s de los bienes muebles que aparecen en la sitio Web. ComprameYa no es propietario de dichos bienes, muebles, ni garantiza el perfeccionamiento de las operaciones para obtener la propiedad sobre dichos bienes muebles, así como tampoco garantiza la existencia, calidad, ni cantidad de los inmuebles ofrecidos a través del espacio Web ComprameYa.
                                            El Usuario es completamente responsable por las negociaciones y operaciones que realice con los oferentes que aparezcan en el presente Portal.
                                            ComprameYa no será responsable por los daños que pueda sufrir el Usuario por contratar con los ofertantes que aparecen en la presente página Web. Asimismo, los Usuarios eximen de toda responsabilidad a ComprameYa en caso de una posible acción legal o reclamo en contra de otro Usuario y/o tercero. De presentarse dicho caso, el Usuario será el encargado de indemnizar al otro Usuario o tercero que presente acción legal en su contra y, por lo tanto, ComprameYa no tendrá que pagar indemnización alguna.
                                            ComprameYa declara que www.facebook.com no tiene control sobre las transacciones que realicen los usuarios, en tal sentido, no asume responsabilidad alguna en relación de cualquier pérdida, gasto, demanda o daño, directo o consecuente, derivados de la información y uso del sitio web.
                                        </p>
                                    </div>
                                    <div class="bs-callout bs-callout-primary">

                                        <h4>Legislación y Jurisdicción aplicable</h4>
                                        <br/>
                                        <p>
                                            Los términos y condiciones regulados en ComprameYa se rigen en todos y cada uno de sus extremos por las leyes de la República de El Salvador.
                                            ComprameYa y el Usuario, con renuncia a cualquier otro fuero, se someten al de los Juzgados y Tribunales de la Republica De El Salvador.
                                        </p>
                                    </div>
                                </div>
                            </div>
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
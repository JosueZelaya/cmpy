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
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:if test="${param.error != null}">
            <div class="alert alert-danger"> 
                ¡Usuario y Clave incorrectas!
            </div>
        </c:if>
        
        <c:if test="${param.logout != null}">
            <div class="alert alert-success"> 
                ¡Se ha cerrado la sessión!
            </div>
        </c:if>
        
        <form:form id="loginForm" method="post" action="${rootURL}login">
            <label for="username" class="col-sm-2 control-label">Usuario*</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Usuario" />
            <label for="password" class="col-sm-2 control-label">Clave*</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Clave" />
            <input type="submit" class="btn btn-primary" value="Login">
        </form:form>
        
    </body>
</html>

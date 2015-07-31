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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Login - ComprameYa!</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
    </head>
    <body>
        
        <%@include file="common/menuPrincipal.jsp" %>
              
        <div class="container-fluid">
            <div class="row">                          
                <div class="col-md-12 min_cont marcar">
                    <div class="panel panel-default min_cont">                    
                        <div class="panel-heading">
                            <p>                            
                                Para poder realizar compras y ventas primero debe autenticarse.                                                            
                            </p>
                        </div>                      
                        <div class="panel-body">
                            <%@include file="common/loginForm.jsp" %>
                        </div>
                    </div>
                </div>            
            </div>          
            <%@include file="common/footer.jsp" %>              
        </div> 
        
        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"/></script>                
    </body>
</html>

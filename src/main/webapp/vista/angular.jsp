<%-- 
    Document   : angular
    Created on : 08-22-2015, 11:58:52 PM
    Author     : arch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <div ng-app="">
            <p>Name : <input type="text" ng-model="name" class="form-control"></p>
            <h1>Hello {{name}}</h1>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/frameworks/angular.min.js"/></script>   
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-angular/ui-bootstrap-tpls-0.13.3.min"/></script>   
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js"/></script>   
    </body>
</html>

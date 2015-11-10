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

        <div ng-controller="ModalDemoCtrl">
            <script type="text/ng-template" id="myModalContent.html">
                <div class="modal-header">
                <h3 class="modal-title">I'm a modal!</h3>
                </div>
                <div class="modal-body">
                <ul>
                <li ng-repeat="item in items">
                <a href="#" ng-click="$event.preventDefault(); selected.item = item">{{ item }}</a>
                </li>
                </ul>
                Selected: <b>{{ selected.item }}</b>
                </div>
                <div class="modal-footer">
                <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
                <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
                </div>
            </script>

            <button type="button" class="btn btn-default" ng-click="open()">Open me!</button>
            <button type="button" class="btn btn-default" ng-click="open('lg')">Large modal</button>
            <button type="button" class="btn btn-default" ng-click="open('sm')">Small modal</button>
            <button type="button" class="btn btn-default" ng-click="toggleAnimation()">Toggle Animation ({{ animationsEnabled}})</button>
            <div ng-show="selected">Selection from a modal: {{ selected}}</div>
        </div>

        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers/modal.js"></script>

    </body>
</html>

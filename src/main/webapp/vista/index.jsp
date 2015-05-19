<%-- 
    Document   : index
    Created on : 05-19-2015, 08:52:18 PM
    Author     : arch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">         
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ComprameYa!</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
<div class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle navbar-brand" data-toggle="collapse" data-target="#enlacesBarra">
                <span id="spanBP" class="glyphicon glyphicon-plus"></span>
            </button>
            <a href="index.php" class="navbar-brand">ComprameYa!</a>
        </div>
        <div class="collapse navbar-collapse" id="enlacesBarra">
            <ul class="nav navbar-nav navbar-right">                
                <li><a class="active" href="#" id="goToIndex">Inicio</a></li>
                <li><a href="#" >Acerca de</a></li>
                <li><a href="#" >Contactanos</a></li>
                <li><a href="#" >Iniciar Sesion</a></li>
            </ul>
        </div>
    </div>
</div>   

<div class="row">  
    <div class="col-lg-2" >
        Categorías
    </div>
    
    <div class="col-lg-8" id="main-content">
        Todos los anuncios
    </div>
    
    <div class="col-lg-2" id="main-content">
        Empresas anunciantes
    </div>
    
</div>
 
<!-- Cargamos los scripts --> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"/></script>
</body>
</html>
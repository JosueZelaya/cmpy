<%-- 
    Document   : boot
    Created on : 24-may-2015, 8:01:11
    Author     : genaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ComprameYa!</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .min_cont
            {
               min-height: 500px;         
                
            }
            
            .min_otros
            {
                
                min-height: 100px;     
            }
            
            .marcar
            {
                background-color: gray;
                border: #000 solid 2px;
                
            }
            
            
        </style>
        
        
    </head>
    <body>
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-4 min_otros marcar">ComprameYa!</div>
            <div class="col-md-8 min_otros marcar">Menu</div>
          </div>
          <div class="row">
            <div class="col-md-2 min_cont marcar">Menu</div>
            <div class="col-md-8 min_cont marcar">Contenido</div>
            <div class="col-md-2 min_cont marcar">Anuncios</div>
          </div>
          <div class="row">
            <div class="col-md-12 min_otros marcar">Pie</div>
          </div>
        </div>
        
        
        
        <!-- Cargamos los scripts --> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"/></script>
    </body>
</html>

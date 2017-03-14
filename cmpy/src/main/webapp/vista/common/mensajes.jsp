<%-- 
    Document   : mensajes
    Created on : 12-nov-2015, 20:30:51
    Author     : genaro
--%>

<div class="row" ng-controller="menController">
    <div class="col-md-4">
        <div class="col-md-12" ng-repeat="usuario in usuarios">
            <div class="col-md-4"><img src="#" alt="img" class="img-circle"></div>
            <div class="col-md-8" ng-click="getMesansaje(usuario.id)">{{usuario.nombre}}<span class="badge">{{usuario.mensajes}}</span></div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="list-group" ng-repeat="mensaje in mensajes">
            <a href="#" class="list-group-item {{mensaje.color}}">
              <h4 class="list-group-item-heading">{{mensaje.encabezado}}</h4>
              <p class="list-group-item-text">{{mensaje.texto}}</p>
            </a>            
        </div>
        <div class="col-md-12"></div>
    </div>
</div>

mensajes.controller('mensajesController',['$scope','$stateParams','$rootScope','mensajesService','$timeout' , function($scope,$stateParams,$rootScope,mensajesService,$timeout) {

    
    $scope.contactos;
    $scope.mensajescontacto;
    $scope.usuarioactivo;
    $scope.nombreactivo;
    $scope.texto;
    $scope.alerta;
    $scope.asunto = "~";
    $scope.usuarioParam;
    $scope.asuntoParam="~";
    
    
    $scope.getUsuarios = function(){
        mensajesService.getUsuarios()
        .success(function (response) 
        {   
            $scope.contactos =  response;
        });        
    };
    
    
    $scope.getMensajeUsuario = function(usuarioId){
        
        $scope.usuarioactivo = usuarioId;
        
        if($scope.usuarioactivo==$scope.usuarioParam)
        {
            $scope.asunto = $scope.asuntoParam;
        }
        else
        {
          $scope.asunto="~"; 
        }
        
        mensajesService.getMensajeUsuario(usuarioId)
        .success(function(response){            
            $scope.mensajescontacto = response;
        });
          $timeout(function() {
            var id=document.querySelector("#bandeja");
            var elements = angular.element(id);
            var element = elements[0];                
            element.scrollTop = element.scrollHeight-element.clientHeight;            
         },100);
    }; 
    
    $scope.setConversacion = function(nombre){
        
        $scope.nombreactivo=nombre;
    };
    
    $scope.setMensaje = function(titulo,texto,destinatario){
        var destinatariosArray = [];
        destinatariosArray.push(destinatario);
        
        mensajesService.setMensaje(titulo,texto,destinatariosArray)
        .success(function (response) 
        {   
            $scope.texto = "";
            var id = document.querySelector("#textomensaje");
            var elements = angular.element(id);
            var element = elements[0];    
            element.focus();
            $scope.getMensajeUsuario($scope.usuarioactivo);
            $scope.getUsuarios();
            
            id=document.querySelector("#bandeja");
            elements = angular.element(id);
            element = elements[0];                
            element.scrollTop = element.scrollHeight-element.clientHeight;
            return response;
        });        
    };   
    
    $scope.iniciar = function(){
         $scope.getUsuarios();   
         $scope.usuarioactivo = $stateParams.usuarioId;
         $scope.usuarioParam = $stateParams.usuarioId;
         $scope.asunto = $stateParams.asunto;
         $scope.asuntoParam = $stateParams.asunto;
         $scope.nombreactivo = $stateParams.usuarioNombre;
         $scope.getMensajeUsuario($scope.usuarioactivo);
    };
    
}]);
mensajes.controller('mensajesController',['$scope','$stateParams','$rootScope','mensajesService' , function($scope,$stateParams,$rootScope,mensajesService) {

    
    $scope.contactos;
    $scope.mensajescontacto;
    $scope.usuarioactivo;
    $scope.nombreactivo;
    $scope.texto;
    $scope.alerta;
    $scope.asunto = "~";
    
    
    $scope.getUsuarios = function(){
        mensajesService.getUsuarios()
        .success(function (response) 
        {   
            $scope.contactos =  response;
        });        
    };
    
    
    $scope.getMensajeUsuario = function(usuarioId){
        
        $scope.usuarioactivo = usuarioId;
        
        mensajesService.getMensajeUsuario(usuarioId)
        .success(function(response){            
            $scope.mensajescontacto = response;
        });
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
            return response;
        });        
    };   
    
    $scope.iniciar = function(){
         $scope.getUsuarios();   
         $scope.usuarioactivo = $stateParams.usuarioId;
         $scope.asunto = $stateParams.asunto;
         $scope.nombreactivo = $stateParams.usuarioNombre;
         $scope.getMensajeUsuario($scope.usuarioactivo);
    };
    
}]);
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


modulo_anuncios.controller('productoController', function ($rootScope, $scope, $stateParams, anunciosService,mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
    var getPublicacion = function(id){
        return anunciosService.getPublicacionById(id)
                .success(function (publicacion){
                   return publicacion; 
                });
    };
    
    var cargarDetallePublicacion = function(id){

        $rootScope.publicacion = undefined;
        
        getPublicacion(id).success(function(publicacion){
            $rootScope.publicacion = publicacion;
        });           
        
    };
    
    $scope.agregarComentario = function () {
        
        var publicacion_id = $scope.publicacion.id;
        var comentario = $scope.comentario;
        
        anunciosService.agregarComentario(publicacion_id,comentario)
                .success(function(respuesta){});
        
    };
    
    var init = function () {
        
        var publicacionId = $stateParams.publicacionId;
        
        if(publicacionId!==undefined){
            cargarDetallePublicacion(publicacionId);   
        }
        
    };
    
    init();

});

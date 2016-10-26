/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


modulo_anuncios.controller('productoController', function ($scope, $stateParams, anunciosService, comentariosService) {
    
    var getPublicacion = function(id){
        return anunciosService.getPublicacionById(id)
                .success(function (publicacion){
                   return publicacion; 
                });
    };
    
    var getComentarios = function(publicacionId){
        return comentariosService.getComentarios(publicacionId, 0)
                .success(function (comentarios){
                   return comentarios; 
                });
    };
    
    var cargarDetallePublicacion = function(publicacionId){

        $scope.publicacion = undefined;
        
        getPublicacion(publicacionId).success(function(publicacion){
            $scope.publicacion = publicacion;
        });           
        
        getComentarios(publicacionId).success(function(comentarios){
            $scope.comentarioList = comentarios;
        });
        
    };
    
    $scope.agregarComentario = function () {
        
        var publicacion_id = $scope.publicacion.id;
        var comentario = $scope.comentario;
        
        comentariosService.agregarComentario(publicacion_id,comentario)
                .success(function(respuesta){
                    getComentarios(publicacion_id).success(function(comentarios){
                        $scope.comentarioList = comentarios;
                    });
                });
        
    };
    
    var init = function () {
        
        var publicacionId = $stateParams.publicacionId;
        
        if(publicacionId!==undefined){
            cargarDetallePublicacion(publicacionId);   
        }
        
    };
    
    init();

});

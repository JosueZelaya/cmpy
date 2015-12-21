modulo_anuncios.controller('anunciosController',['$scope','anunciosService','TIPO_PUBLICACION',function($scope,anunciosService,TIPO_PUBLICACION){
    
    anunciosService.getAnuncios(TIPO_PUBLICACION.GRATIS,'0')
        .success(function(publicaciones) {
            $scope.publicaciones = publicaciones;
        });
    
        anunciosService.getAnuncios(TIPO_PUBLICACION.PAGADA,'0')
        .success(function(publicaciones) {
            $scope.anuncios = publicaciones;
        });

    
        
}]);

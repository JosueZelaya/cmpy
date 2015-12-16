modulo_anuncios.controller('anunciosController',['$scope','anunciosService',function($scope,anunciosService){
    
    anunciosService.getAnuncios('2','0')
        .success(function(publicaciones) {
            $scope.publicaciones = publicaciones;
        });
    
        anunciosService.getAnuncios('1','0')
        .success(function(publicaciones) {
            $scope.anuncios = publicaciones;
        });

    
        
}]);

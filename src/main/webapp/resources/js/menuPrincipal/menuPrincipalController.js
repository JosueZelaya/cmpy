menuPrincipal.controller('menuPrincipalController',['$scope','$rootScope','anunciosService','TIPO_PUBLICACION' , function($scope,$rootScope,anunciosService,TIPO_PUBLICACION) {
    

    $scope.match = "";

    var getPublicacionesByMatch = function(tipo,pagina,match) {
        return anunciosService.getAnunciosByMatch(tipo,pagina,match)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };
    
    $scope.cargarPublicacionesGratisByMatch = function(page,match){
        
        getPublicacionesByMatch(TIPO_PUBLICACION.GRATIS, page,match)
                .success(function(publicaciones){
            $rootScope.publicaciones = publicaciones;
        });
    };



}]);


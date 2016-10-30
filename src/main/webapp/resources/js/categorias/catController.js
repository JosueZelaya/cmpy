categorias.controller('catController',['$scope','catService','anunciosService' , function($scope,catService,anunciosService) {
    catService.getCategorias()
    .success(function (response) 
    {   

        $scope.categorias = response;
        
        angular.forEach($scope.categorias, function (item) {
            item.collapse = true;
            angular.forEach(item.hijos, function (sub_item) {
                sub_item.collapse = true;
                angular.forEach(sub_item, function (sub_sub_item) {
                    sub_sub_item.collapse = true;
                });
            });
        });
        
    });
    
    var getPublicacionesByCat = function(tipo,pagina,cat) {
        return anunciosService.getAnunciosByCat(tipo,pagina,cat)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };
    
    var cargarPublicacionesGratisByCat = function(page,cat){
        getPublicaciones(TIPO_PUBLICACION.GRATIS, page,cat)
                .success(function(publicaciones){
            $rootScope.publicaciones = publicaciones;
        });
    };
    
    
}]);

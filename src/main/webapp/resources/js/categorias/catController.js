categorias.controller('catController',['$scope','$rootScope','TIPO_PUBLICACION','catService','anunciosService' , function($scope,$rootScope,TIPO_PUBLICACION,catService,anunciosService) {
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
    
    $scope.cargarPublicacionesGratisByCat = function(page,cat){
        
        debugger;
        
        getPublicacionesByCat(TIPO_PUBLICACION.GRATIS, page,cat)
                .success(function(publicaciones){
            $rootScope.publicaciones = publicaciones;
        });
    };
    
    
}]);

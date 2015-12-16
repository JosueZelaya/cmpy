modulo_productos.controller('productosController',['$scope','productosService',function($scope,productosService){
                
    productosService.getAnuncios('2','0')
        .success(function(publicaciones) {
            $scope.publicaciones = publicaciones;
        });
        
}]);

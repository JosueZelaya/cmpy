//productos.controller('productosController',['productosService',function($scope,productosService){
//        $scope.publicaciones = productosService.getAnuncios('2','1');
//}]);

productos.controller('productosController',function($scope,$http){
    var req = {
                url: "/publicacion/getAnunciosSinPaginar"
//                data: {tipo: '2', page: '1'}
            };
            $http.get("/publicacion/getAnunciosSinPaginar")
                    .success(function (response)
                    {
                        $scope.publicaciones = response.rows;
                    });

});

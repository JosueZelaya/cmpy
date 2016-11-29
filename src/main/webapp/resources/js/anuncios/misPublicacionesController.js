modulo_anuncios.controller('misPublicacionesController', function ($rootScope, $scope, $stateParams, anunciosService, mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {

    var getMisPublicaciones = function (tipo, pagina) {
            return anunciosService.getMisAnuncios(tipo, pagina)
                    .success(function (publicaciones) {
                        return publicaciones;
                    });
        };

    var eliminarPublicacion = function (publicacionId) {
        return anunciosService.eliminarPublicacion(publicacionId)
                .success(function (respuesta) {
                    return respuesta;
                });
    };
    
    $scope.eliminarPublicacion = function (publicacionId,index) {        
        eliminarPublicacion(publicacionId)
                .success(function (respuesta) {
                    $scope.publicaciones.splice(index,1);
                }); 
    };

    $scope.paginaSiguiente = function () {
        $scope.page++;
        verMisPublicaciones($scope.page);
    };

    $scope.getNumbers = function (num) {
        return new Array(num);
    };
    
    var verMisPublicaciones = function (page) {
            $scope.navCollapsed = !$scope.navCollapsed;
            getMisPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                    .success(function (publicaciones) {
                        $scope.publicaciones = publicaciones;
                    });
        };
    

    var init = function () {
        $scope.publicaciones = [];
        $scope.page = 0;
        verMisPublicaciones($scope.page);

    };

    init();

});

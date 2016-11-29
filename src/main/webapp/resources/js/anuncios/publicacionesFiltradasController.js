modulo_anuncios.controller('publicacionesFiltradasController', function ($rootScope, $scope, $stateParams, anunciosService, mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {

    var getPublicacionesByCat = function(tipo,pagina,cat,nivel) {
        return anunciosService.getAnunciosByCat(tipo,pagina,cat,nivel)
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
        cargarPublicacionesGratisByCat($scope.page);
    };

    $scope.getNumbers = function (num) {
        return new Array(num);
    };
    
    var cargarPublicacionesGratisByCat = function(page,cat,nivel){
        
        getPublicacionesByCat(TIPO_PUBLICACION.GRATIS, page,cat,nivel)
                .success(function(publicaciones){
            $scope.publicaciones = publicaciones;
        });
    };

    var init = function () {
        var cat = $stateParams.cat;
        var nivel = $stateParams.nivel;
        $scope.publicaciones = [];
        $scope.page = 0;
        cargarPublicacionesGratisByCat($scope.page, cat, nivel);

    };

    init();

});

modulo_anuncios.controller('busquedaController', function ($rootScope, $scope, $stateParams, anunciosService, mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {

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
        cargarPublicacionesGratisByMatch($scope.page);
    };

    $scope.getNumbers = function (num) {
        return new Array(num);
    };

    var getPublicacionesByMatch = function (tipo, pagina, match) {
        return anunciosService.getAnunciosByMatch(tipo, pagina, match)
                .success(function (publicaciones) {
                    return publicaciones;
                });
    };

    var cargarPublicacionesGratisByMatch = function (page) {
        getPublicacionesByMatch(TIPO_PUBLICACION.GRATIS, page, $scope.terminoBusqueda)
                .success(function (publicaciones) {
                    $scope.publicaciones = publicaciones;
                });
    };

    var init = function () {
        $scope.terminoBusqueda = $stateParams.terminoBusqueda;
        $scope.publicaciones = [];
        $scope.page = 0;
        
        cargarPublicacionesGratisByMatch($scope.page);
        
    };

    init();

});
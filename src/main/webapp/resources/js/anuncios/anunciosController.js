modulo_anuncios.controller('anunciosController', function ($rootScope, $scope, $stateParams, anunciosService, mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {

    var getPublicacionesByCat = function(tipo,pagina,cat,nivel) {
        return anunciosService.getAnunciosByCat(tipo,pagina,cat,nivel)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };

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

    var getPublicaciones = function (tipo, pagina) {
        return anunciosService.getAnuncios(tipo, pagina)
                .success(function (publicaciones) {
                    return publicaciones;
                });
    };

    var cargarPublicacionesGratis = function (page) {
        getPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                .success(function (publicaciones) {
                    $scope.publicaciones = $scope.publicaciones.concat(publicaciones);
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
        cargarPublicacionesGratis($scope.page);
    };

    $scope.getNumbers = function (num) {
        return new Array(num);
    };
    
    $rootScope.guardarPublicacion = function(publicacion){
        
        anunciosService.agregarPublicacion(publicacion)
                .success(function(publicacion){        
            
            $scope.publicaciones.unshift(publicacion);
        });
        
    };
    
    $rootScope.verMisPublicaciones = function (page) {
            $scope.navCollapsed = !$scope.navCollapsed;
            getMisPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                    .success(function (publicaciones) {
                        $scope.publicaciones = publicaciones;
                    });
        };
    
    $rootScope.cargarPublicacionesGratisByCat = function(page,cat,nivel){
        
        getPublicacionesByCat(TIPO_PUBLICACION.GRATIS, page,cat,nivel)
                .success(function(publicaciones){
            $scope.publicaciones = publicaciones;
        });
    };
    
    $rootScope.cargarPublicacionesGratis = function(){
        $scope.publicaciones = [];
        cargarPublicacionesGratis(0);
    };

    var init = function () {
        $scope.publicaciones = [];
        $scope.page = 0;
        cargarPublicacionesGratis($scope.page);

    };

    init();

});

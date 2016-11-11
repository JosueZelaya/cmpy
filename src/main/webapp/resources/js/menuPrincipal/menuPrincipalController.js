menuPrincipal.controller('menuPrincipalController', ['$scope', '$rootScope', 'anunciosService', 'notificacionService', 'TIPO_PUBLICACION', function ($scope, $rootScope, anunciosService, notificacionService, TIPO_PUBLICACION) {


        $scope.match = "";
        
        var getPublicaciones = function (tipo, pagina) {
            return anunciosService.getAnuncios(tipo, pagina)
                    .success(function (publicaciones) {
                        return publicaciones;
                    });
        };

        var cargarPublicacionesGratis = function (page) {
            getPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                    .success(function (publicaciones) {
                        $rootScope.publicaciones = publicaciones;
                    });
        };
        
        var getMisPublicaciones = function (tipo,pagina) {
            return anunciosService.getMisAnuncios(tipo,pagina)
                    .success(function (publicaciones) {
                        return publicaciones;
                    });
        };

        var getPublicacionesByMatch = function (tipo, pagina, match) {
            return anunciosService.getAnunciosByMatch(tipo, pagina, match)
                    .success(function (publicaciones) {
                        return publicaciones;
                    });
        };

        var getTotalNotificaciones = function () {
            return notificacionService.getTotalNotificaciones()
                    .success(function (total) {
                        return total;
                    });
        };

        var getNotificaciones = function () {
            return notificacionService.getNotificaciones()
                    .success(function (notificaciones) {
                        return notificaciones;
                    });
        };
        
        $scope.quitarVisibilidad = function (idNotificacion) {
            return notificacionService.quitarVisibilidad(idNotificacion)
                    .success(function (notificaciones) {
                        $scope.notificaciones = notificaciones;
                        $scope.totalNotificaciones = notificaciones.length;
                    });
        };    

        $scope.cargarPublicacionesGratisByMatch = function (page, match) {

            getPublicacionesByMatch(TIPO_PUBLICACION.GRATIS, page, match)
                    .success(function (publicaciones) {
                        $rootScope.publicaciones = publicaciones;
                    });
        };
        
        $scope.verMisPublicaciones = function (page) {
            $scope.navCollapsed = !$scope.navCollapsed;
            getMisPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                    .success(function (publicaciones) {
                        $rootScope.publicaciones = publicaciones;
                    });
        };
        
        $scope.cargarPublicacionesGratis = function () {
            cargarPublicacionesGratis(0);
        };

        init = function () {
            $scope.navCollapsed = true;
            $scope.collapseNot = true;
            $scope.totalMensajes = 0;
            
            getTotalNotificaciones()
                    .success(function (total) {
                        try {
                            JSON.parse(total);
                            $rootScope.totalNotificaciones = total;
                            getNotificaciones()
                                .success(function (notificaciones) {
                                    $rootScope.notificaciones = notificaciones;                        
                                });
                        } catch (e) {
                            $rootScope.totalNotificaciones = 0;
                        }                        
                    });


        };

        init();


    }]);


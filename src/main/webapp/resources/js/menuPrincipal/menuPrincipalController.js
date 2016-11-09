menuPrincipal.controller('menuPrincipalController', ['$scope', '$rootScope', 'anunciosService', 'notificacionService', 'TIPO_PUBLICACION', function ($scope, $rootScope, anunciosService, notificacionService, TIPO_PUBLICACION) {


        $scope.match = "";
        
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

            getMisPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                    .success(function (publicaciones) {
                        $rootScope.publicaciones = publicaciones;
                    });
        };

        init = function () {
            $scope.navCollapsed = true;
            $scope.totalMensajes = 0;
            
            getTotalNotificaciones()
                    .success(function (total) {
                        try {
                            JSON.parse(total);
                            $scope.totalNotificaciones = total;
                            getNotificaciones()
                                .success(function (notificaciones) {
                                    $scope.notificaciones = notificaciones;                        
                                });
                        } catch (e) {
                            $scope.totalNotificaciones = 0;
                        }                        
                    });


        };

        init();


    }]);


menuPrincipal.controller('menuPrincipalController', ['$scope', '$rootScope', 'anunciosService', 'notificacionService', 'TIPO_PUBLICACION','mensajesService','PushNotificationService','$log', function ($scope, $rootScope, anunciosService, notificacionService, TIPO_PUBLICACION,mensajesService, PushNotificationService, $log) {

        $scope.match = "";
        $rootScope.notificaciones = {}
        $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
        $scope.pageMensajesNoLeidos = 0;
        $scope.NoLeidosTotal = 0;
        $scope.mensajes = [];
        
//        $rootScope.$on('$routeChangeStart', function (event, next, current) {
//            if (!current) {
//              PushNotificationService.disconnect();
//            }
//          });

        PushNotificationService.receive().then(null, null, function (notificaciones) {
            $rootScope.notificaciones.push(notificaciones);
            $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
        });

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

        var getMisPublicaciones = function (tipo, pagina) {
            return anunciosService.getMisAnuncios(tipo, pagina)
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

        $scope.quitarVisibilidad = function (idNotificacion, index) {
//            return notificacionService.quitarVisibilidad(idNotificacion)
//                    .success(function (notificaciones) {
//                        $scope.notificaciones = notificaciones;
//                        $scope.totalNotificaciones = notificaciones.length;
//                    });

            notificacionService.ocultar(idNotificacion);
            $rootScope.notificaciones.splice(index, 1);
            $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
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
        
        $scope.cargarMensajesNoLeidos = function(page){
            mensajesService.getMensajesNoLeidos(page)
            .success(function (response) {
                        $scope.mensajes = response;
                 $scope.getMensajeUsuarioNoleidoTotal();
             });
        };
        
        $scope.getMensajesNoLeidosTotal = function(){
            mensajesService.getMensajesNoLeidosTotal()
            .success(function (response) {
                        $scope.NoLeidosTotal = response;
             });           
            
            
        };
        
        $scope.getMensajeUsuarioNoleidoTotal =function(){
            angular.forEach($scope.mensajes, function (item) {
                
                mensajesService.getMensajeUsuarioNoleidoTotal(item.usuarioEmisor.id)
                .success(function (response) {
                            item.total = response;
                 });  
            });
            
        };
        
        
        $scope.setMensajeUsuarioLeido = function(usuarioId){
             mensajesService.setMensajeUsuarioLeido(usuarioId)
            .success(function (response) {
                    init();
                     return response;
             });           
        };
        

        init = function () {
            $scope.navCollapsed = true;
            $scope.collapseNot = true;
            $scope.totalMensajes = 0;

            if ($rootScope.authenticated) {
                getNotificaciones()
                        .success(function (notificaciones) {
                            $rootScope.notificaciones = notificaciones;
                            $rootScope.totalNotificaciones = notificaciones.length;
                        });

               $scope.cargarMensajesNoLeidos($scope.pageMensajesNoLeidos);
               $scope.getMensajesNoLeidosTotal();
            }


        };

        init();


    }]);


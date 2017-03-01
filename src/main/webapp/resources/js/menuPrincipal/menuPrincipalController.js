menuPrincipal.controller('menuPrincipalController',
        ['$scope',
            '$rootScope',
            'notificacionService',
            'mensajesService',
            'PushNotificationService',
            '$log',
            'toastr',
            '$state',
            'utilsService', function ($scope, $rootScope, notificacionService, mensajesService, PushNotificationService, $log, toastr, $state, utilsService) {

                $scope.match = "";
                $rootScope.notificaciones = {};
                $rootScope.totalNotificaciones = 0;
                $scope.pageMensajesNoLeidos = 0;
                $scope.NoLeidosTotal = 0;
                $scope.mensajes = [];

                $rootScope.activarNotificacionesPush = function () {
                    
                    PushNotificationService.receive().then(null, null, function (notificacionUsuario) {

                        if (notificacionUsuario.notificacion.tipo === 'COMENTARIO') {

                            toastr.info(notificacionUsuario.notificacion.mensaje, {
                                allowHtml: true,
                                onHidden: function (clicked) {
                                    if (clicked) {
                                        quitarVisibilidad(notificacionUsuario.id, undefined);
                                        $state.go("home.vistaProducto",
                                                JSON.parse("{\"publicacionId\" : \" " + notificacionUsuario.notificacion.link + " \" }"),
                                                {reload: true});
                                    }
                                }
                            });

                            $rootScope.totalNotificaciones = $rootScope.notificaciones.unshift(notificacionUsuario);
                            updatePageTitle();
                        } else {

                            toastr.success(notificacionUsuario.notificacion.mensaje, {
                                allowHtml: true,
                                onHidden: function (clicked) {
                                    if (clicked) {
                                        setMensajeUsuarioLeido(notificacionUsuario.emisor.id);
                                        $state.go("home.vistaMensaje",
                                                JSON.parse(notificacionUsuario.notificacion.link),
                                                {reload: true});
                                    }
                                }
                            });
                            $scope.NoLeidosTotal++;
                            updatePageTitle();
                        }

                    });
                };

                var quitarVisibilidad = function (idNotificacion, index) {
                    notificacionService.ocultar(idNotificacion);
                    $rootScope.notificaciones.splice(index, 1);
                    $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
                    updatePageTitle();
                };

                var getNotificaciones = function () {
                    return notificacionService.getNotificaciones()
                            .success(function (notificaciones) {
                                return notificaciones;
                            });
                };

                $rootScope.getNotificaciones = function () {
                    getNotificaciones()
                            .success(function (notificaciones) {
                                $rootScope.notificaciones = notificaciones;
                                $rootScope.totalNotificaciones = notificaciones.length;
                            });
                };

                $rootScope.quitarVisibilidad = function (idNotificacion, index) {
                    notificacionService.ocultar(idNotificacion);
                    $rootScope.notificaciones.splice(index, 1);
                    $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
                    updatePageTitle();
                };

                $scope.cargarMensajesNoLeidos = function (page) {
                    mensajesService.getMensajesNoLeidos(page)
                            .success(function (response) {
                                $scope.mensajes = response;
                                $scope.getMensajeUsuarioNoleidoTotal();
                            });
                };

                $scope.getMensajesNoLeidosTotal = function () {
                    mensajesService.getMensajesNoLeidosTotal()
                            .success(function (response) {
                                $scope.NoLeidosTotal = response;
                            });
                };

                $scope.getMensajeUsuarioNoleidoTotal = function () {
                    angular.forEach($scope.mensajes, function (item) {

                        mensajesService.getMensajeUsuarioNoleidoTotal(item.usuarioEmisor.id)
                                .success(function (response) {
                                    item.total = response;
                                });
                    });

                };


                $scope.setMensajeUsuarioLeido = function (usuarioId) {
                    mensajesService.setMensajeUsuarioLeido(usuarioId)
                            .success(function (response) {
                                $scope.cargarMensajesNoLeidos($scope.pageMensajesNoLeidos);
                                $scope.getMensajesNoLeidosTotal();
                                updatePageTitle();
                                return response;
                            });
                };

                $scope.buscar = function (criterio) {
                    $state.go("home.busqueda",
                            {terminoBusqueda: criterio}, {reload: true});
                };

                var updatePageTitle = function () {
                    var total = $rootScope.totalNotificaciones + $scope.NoLeidosTotal;
                    if (total > 0) {
                        document.title = "(" + total + ") ComprameYa!";
                    } else {
                        document.title = "ComprameYa!";
                    }
                };

                var setMensajeUsuarioLeido = function (usuarioId) {
                    mensajesService.setMensajeUsuarioLeido(usuarioId)
                            .success(function (response) {
                                $scope.cargarMensajesNoLeidos($scope.pageMensajesNoLeidos);
                                $scope.getMensajesNoLeidosTotal();
                                updatePageTitle();
                                return response;
                            });
                };

                init = function () {
                    $scope.navCollapsed = true;
                    $scope.infoCollapsed = true;
                    $scope.collapseNot = true;
                    $scope.totalMensajes = 0;
                    $scope.visitas = 0;

                    getNotificaciones()
                            .success(function (notificaciones) {
                                $rootScope.notificaciones = notificaciones;
                                $rootScope.totalNotificaciones = notificaciones.length;
                            });
                    
                    utilsService.getTotalVisitas()
                            .success(function (total) {
                                $scope.visitas = total;
                            });

                    $rootScope.activarNotificacionesPush();

                    $scope.cargarMensajesNoLeidos($scope.pageMensajesNoLeidos);
                    $scope.getMensajesNoLeidosTotal();

                    updatePageTitle();

                };

                init();


            }]);
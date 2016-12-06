modulo_notificacion.controller('notificacionController',
        ['$scope',
            '$rootScope',
            'notificacionService',
            function ($scope, $rootScope, notificacionService) {

                $scope.message = "";
                $scope.max = 140;

                var getTodasNotificaciones = function () {
                    return notificacionService.getTodasNotificaciones()
                            .success(function (notificaciones) {
                                return notificaciones;
                            });
                };

                $scope.init = function () {
                    if ($rootScope.authenticated) {

                        getTodasNotificaciones()
                                .success(function (notificaciones) {
                                    $scope.notificaciones = notificaciones;
                                });

                    }
                };

            }
        ]);


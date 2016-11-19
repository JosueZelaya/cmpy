modulo_notificacion.controller('notificacionController', [
    '$scope',
    '$rootScope',
    'notificacionService',
    'localStorageService',
    function ($scope, $rootScope, notificacionService) {

        $scope.message = "";
        $scope.max = 140;

        var getTodasNotificaciones = function () {
            return notificacionService.getTodasNotificaciones()
                    .success(function (notificaciones) {
                        return notificaciones;
                    });
        };

        $scope.quitarVisibilidad = function (idNotificacion) {
            return notificacionService.quitarVisibilidad(idNotificacion)
                    .success(function () {
                        $rootScope.totalNotificaciones--;
                    });
        };

        $scope.init = function () {
            if ($rootScope.authenticated) {

                getTodasNotificaciones()
                        .success(function (notificaciones) {
                            $rootScope.notificaciones = notificaciones;
                        });

            }
        };

    }
]);


modulo_notificacion.controller('notificacionController', [
    '$scope',
    '$rootScope',
    'notificacionService',
    function ($scope, $rootScope, notificacionService) {

        var getTodasNotificaciones = function () {
            return notificacionService.getTodasNotificaciones()
                    .success(function (notificaciones) {
                        return notificaciones;
                    });
        };
        
        $scope.quitarVisibilidad = function (idNotificacion) {
            return notificacionService.quitarVisibilidad(idNotificacion)
                    .success(function (notificaciones) {
                        $rootScope.notificaciones = notificaciones;
                        $rootScope.totalNotificaciones = notificaciones.length;
                    });
        };

        $scope.init = function () {
            getTodasNotificaciones()
                    .success(function (notificaciones) {
                        $rootScope.notificaciones = notificaciones;
                    });
        };

    }
]);


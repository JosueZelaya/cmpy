modulo_notificacion.controller('notificacionController', [
    '$scope',
    '$rootScope',
    'notificacionService',
    'PushNotificationService',
    function ($scope, $rootScope, notificacionService, PushNotificationService) {
        
         $scope.messages = [];
        $scope.message = "";
        $scope.max = 140;

        $scope.addMessage = function() {
          PushNotificationService.send($scope.message);
          $scope.message = "";
        };

        PushNotificationService.receive().then(null, null, function(message) {
          $scope.messages.push(message);
          $rootScope.totalNotificaciones++;
        });

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


modulo_sorteo.controller('sorteoController',
    ['$scope',
    '$rootScope',
    'PushNotificationService'
    , function ($scope, $rootScope, PushNotificationService) {
        
        activarNotificacionesSorteo = function () {
            PushNotificationService.receivePublicMessage().then(null, null, function (notificacion) {
                $scope.notificacion = notificacion;
            });
        };
        
        init = function () {                    
            activarNotificacionesSorteo();
        };

        init();
}]);
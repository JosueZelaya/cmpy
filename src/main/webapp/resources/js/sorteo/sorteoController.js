modulo_sorteo.controller('sorteoController',
    ['$scope',
    '$rootScope',
    'PushNotificationService',
    '$http'
    , function ($scope, $rootScope, PushNotificationService, $http) {
        
        $scope.start = function() {
            var req = "/sorteo/start";
            return $http.get(req);
        };
        
        $scope.stop = function() {
            var req = "/sorteo/stop";
            return $http.get(req)
                    .success(function (response) {
                        $scope.notificacion = response;
                    });
        };
       
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
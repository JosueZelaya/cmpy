modulo_sorteo.controller('sorteoController',
    ['$scope',
    '$rootScope',
    'PushNotificationService',
    '$http',
    '$interval',
    '$timeout'
    , function ($scope, $rootScope, PushNotificationService, $http,$interval,$timeout) {
        
        $scope.salidaAnimacion = false;
        
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
                $timeout(function(){$scope.salidaAnimacion = true;$scope.$apply();}, 100);
                $scope.notificacion = notificacion;            
                $timeout(function(){$scope.salidaAnimacion = false;$scope.$apply();},100);
                
                if(false)
                {
                    confetti_global.start();
                    $rootScope.confeti=true;
                }
                
                
            });
        };

        
        init = function () {
            
            activarNotificacionesSorteo();
            startCuenta();
        };

        init();
}]);
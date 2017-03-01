modulo_sorteo.controller('sorteoController',
    ['$scope',
    '$rootScope',
    'PushNotificationService',
    '$http',
    '$interval',
    '$timeout'
    , function ($scope, $rootScope, PushNotificationService, $http,$interval,$timeout) {
        
        $scope.salidaAnimacion = false;
        $scope.botonstart = false;
        
        $scope.start = function() {
            var req = "/sorteo/start";
            return $http.get(req);
        };
        
        $scope.stop = function() {
            var req = "/sorteo/stop";
            return $http.get(req);
        };
       
        activarNotificacionesSorteo = function () {
            
            
            PushNotificationService.receivePublicMessage().then(null, null, function (notificacion) {
                $timeout(function(){$scope.salidaAnimacion = true;}, 100);
                $scope.notificacion = notificacion;            
                $timeout(function(){$scope.salidaAnimacion = false;},100);
                
                if($scope.notificacion.tipo === "GANADOR_SORTEO")
                {
                    $scope.botonstart = true;

                    $timeout(function(){
                        confetti_global.start();
                        $rootScope.confeti=true;
                        
                    },3000);
                    
                    $timeout(function(){
                        confetti_global.stop();
                        $rootScope.confeti=false;
                        $scope.botonstart = false;
                    },30000);
                }
            
                
            });
        };

        
        init = function () {
            
            activarNotificacionesSorteo();
            startCuenta();
        };

        init();
}]);
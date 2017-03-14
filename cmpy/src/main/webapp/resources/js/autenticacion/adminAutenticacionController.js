autenticacion.controller('autenticacionController',
        ['$rootScope',
            '$scope',
            '$http',
            '$location',
            '$window',
            'PushNotificationService',
            function ($rootScope, $scope, $http, $location, $window, PushNotificationService) {

                $scope.credentials = {};
                $scope.error = false;
                $rootScope.authenticated = false;

                var authenticate = function (credentials, callback) {

                    var headers = credentials ? {authorization: "Basic "
                                + btoa(credentials.username + ":" + credentials.password)
                    } : {};

                    $http.get('/user', {headers: headers}).success(function (data) {
                        if (data.login !== '' && data.login !== 'anonymousUser' && data.login !== undefined) {
                            $rootScope.authenticated = true;
                            $rootScope.username = data.nombre;
                            $rootScope.imageUrl = data.rutaImagen;
                            $rootScope.usuario = data;

                            if (data.socialSignInProvider) {
                                $rootScope.localAccount = false;
                            } else {
                                $rootScope.localAccount = true;
                            }
                        } else {
                            $rootScope.authenticated = false;
                        }
                        callback && callback();
                    }).error(function () {
                        $rootScope.authenticated = false;
                        callback && callback();
                    });

                };

                $scope.login = function () {
                    authenticate($scope.credentials, function () {
                        if ($rootScope.authenticated) {
                            PushNotificationService.reconnect();
                            $location.path("/admin");
                            $scope.error = false;
                            $scope.cancel(); //cerrar el dialogo
                            $rootScope.activarNotificacionesPush();
                            $rootScope.getNotificaciones();
                        } else {
                            $location.path("/login");
                            $scope.error = true;
                        }
                    });
                };

                $rootScope.logout = function () {
                    PushNotificationService.disconnect();
                    $http.post('logout', {}).finally(function () {
                        $rootScope.authenticated = false;
                        $location.path("/admin");
                    });
                };
                
                $scope.register = function(){
                    $window.location.href = '/user/register';
                };
                
                $scope.facebookAuth = function(){
                    $window.location.href = '/auth/facebook';
                };

                authenticate();
            }]);
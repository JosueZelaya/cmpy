autenticacion.controller('autenticacionController',
        function ($rootScope, $scope, $http, $location) {
            
            $scope.credentials = {};
            $scope.error = false;

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
                        $location.path("/");
                        $scope.error = false;
                        $scope.cancel(); //cerrar el dialogo
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            };


            $rootScope.logout = function () {
                $rootScope.desconectar();
                $http.post('logout', {}).finally(function () {
                    $rootScope.authenticated = false;
                    $location.path("/");
                });
            };
            
            authenticate();
        }
);
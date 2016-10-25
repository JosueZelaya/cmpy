autenticacion.controller('autenticacionController',
        function ($rootScope, $scope, $http, $location) {

            var authenticate = function (credentials, callback) {

                var headers = credentials ? {authorization: "Basic "
                            + btoa(credentials.username + ":" + credentials.password)
                } : {};

                $http.get('/user', {headers: headers}).success(function (data) {
                    if (data.login !== '' && data.login !== 'anonymousUser' && data.login !== undefined) {
                        $rootScope.authenticated = true;
                        $rootScope.username = data.nombre;
                        $rootScope.imageUrl = data.rutaImagen;
                        
                        if(data.socialSignInProvider){
                            $rootScope.localAccount = false;
                        }else{
                            $rootScope.localAccount = true;
                        }
                        
                        $rootScope.signInProvider = data.rutaImagen;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }).error(function () {
                    $rootScope.authenticated = false;
                    callback && callback();
                });

            }

            authenticate();
            $scope.credentials = {};
            $scope.login = function () {
                authenticate($scope.credentials, function () {
                    if ($rootScope.authenticated) {
                        $location.path("/");
                        $scope.error = false;
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            };
        }
);
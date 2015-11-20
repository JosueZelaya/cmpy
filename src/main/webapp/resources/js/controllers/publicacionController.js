cmy_ng.controller('navigation',
        function ($rootScope, $scope, $http, $location) {
            $http.get("/user")
                    .success(function (response)
                    {

                        $scope.usuario = response;

                    });
        }
);
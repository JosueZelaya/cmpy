autenticacion.controller('resetPassController', function ($scope, autenticacionService) {
    $scope.resetPass = function () {
        $scope.wrongEmail = false;

        autenticacionService.resetPass($scope.emailReset)
                .then(function (response) {
                    if (response === "ok") {
                        $scope.wrongEmail = false;
                        $scope.cancel(); //cerrar el dialogo                   
                    } else {
                        $scope.wrongEmail = true;
                        $scope.wrongEmailMsj = response;
                    }

                });

    };

    var init = function () {
        $scope.wrongEmail = false;
        $scope.wrongEmailMsj = "";
    };

    init();

});
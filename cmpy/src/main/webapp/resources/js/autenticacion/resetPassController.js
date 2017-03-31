autenticacion.controller('resetPassController', function ($scope, autenticacionService, $state, toastr) {
    $scope.resetPass = function () {
        $scope.wrongEmail = false;

        autenticacionService.resetPass($scope.emailReset)
                .then(function (response) {
                    if (response === "ok") {
                        $scope.wrongEmail = false;
                        $scope.cancel(); //cerrar el dialogo
                        toastr.success("Se ha enviado un mensaje a su correo para ayudarle a restablecer su contraseña", {});
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
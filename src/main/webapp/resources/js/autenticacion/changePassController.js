autenticacion.controller('changePassController', function ($scope, autenticacionService, $state, toastr, $rootScope) {
    $scope.changePass = function () {
        $scope.passNotEqual = false;
        $scope.wrongPass = false;
        if($scope.passNuevo === ""){
            $scope.passNotEqual = true;
            $scope.msjPasswordNotEqual = "Asigne un password";
        }else if($scope.passNuevo === $scope.passConfirmacion) {
            autenticacionService.changePass($scope.passActual, $scope.passNuevo, $scope.passConfirmacion)
                .then(function (response) {
                    if(response === "ok"){
                        $scope.wrongPass = false;
                        $scope.cancel(); //cerrar el dialogo
                        toastr.success("Se ha cambiado la contraseña", {});
                    }else{
                       $scope.wrongPass = true;
                       $scope.wrongPassMsj = response;
                    }
                   
                });
        } else {
            $scope.passNotEqual = true;
            $scope.msjPasswordNotEqual = "No coinciden";
        }
    };
    
    $scope.resetPass = function () {
        $scope.passNotEqual = false;
        $scope.wrongPass = false;
        if ($scope.passNuevo === $scope.passConfirmacion) {
            autenticacionService.updatePass($scope.passNuevo)
                .then(function (response) {
                    if(response === "ok"){
                        $rootScope.authenticated = true;
                        $state.go("home");                
                        toastr.success("Se ha actualizado su contraseña correctamente", {});
                    }else{
                       $scope.wrongPass = true;
                       $scope.wrongPassMsj = response;
                    }                   
                });
        } else {
            $scope.passNotEqual = true;
            $scope.msjPasswordNotEqual = "No coinciden";
        }
    };

    var init = function () {
        $scope.passNuevo = "";
        $scope.passConfirmacion = "";
        $scope.passActual = "";
        $scope.msjPasswordNotEqual = "";
        $scope.wrongPass = false;
        $scope.wrongPassMsj = "";
        $scope.passNotEqual = false;
    };

    init();

});
autenticacion.controller('updateCellController', function ($scope, autenticacionService, toastr) {
    $scope.updateCell = function () {
        $scope.wrongCell = false;

        autenticacionService.updateCell($scope.updatedCell)
                .then(function (response) {
                    if (response === "ok") {
                        $scope.wrongCell = false;
                        $scope.cancel(); //cerrar el dialogo
                        toastr.success("Su teléfono ha sido actualizado", {});
                    } else {
                        $scope.wrongCell = true;
                        $scope.wrongCellMsj = response;
                    }

                });

    };

    var init = function () {
        $scope.wrongCell = false;
        $scope.wrongCellMsj = "";
    };

    init();

});


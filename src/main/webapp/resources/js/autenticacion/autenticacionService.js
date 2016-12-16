autenticacion.service('autenticacionService', function ($http, $log) {
    this.changePass = function (passActual, passNuevo, passConfirmacion) {
        var formData = new FormData();
        formData.append("passActual", passActual);
        formData.append("passNuevo", passNuevo);
        formData.append("passConfirmacion", passConfirmacion);

        return $http.post('/user/updatePass', formData, {
            transformRequest: angular.identity,
            responseType: 'json',
            headers: {'Content-Type': undefined}
        })
                .then(function (response) {
                   return "ok";
                }, function (response) {
                    return "Contraseña incorrecta";
                });
    };
});
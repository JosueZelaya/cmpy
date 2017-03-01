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

    this.updatePass = function (passNuevo) {
        var formData = new FormData();
        formData.append("password", passNuevo);

        return $http.post('/user/savePassword', formData, {
            transformRequest: angular.identity,
            responseType: 'json',
            headers: {'Content-Type': undefined}
        })
                .then(function (response) {
                    return "ok";
                }, function (response) {
                    return "No se puedo actualizar el password";
                });
    };

    this.resetPass = function (email) {
        var formData = new FormData();
        formData.append("email", email);

        return $http.post('/user/resetPassword', formData, {
            transformRequest: angular.identity,
            responseType: 'json',
            headers: {'Content-Type': undefined}
        })
                .then(function (response) {
                    return "ok";
                }, function (response) {
                    return "El email no existe";
                });
    };
    
    this.updateCell = function (newCell) {
        var formData = new FormData();
        formData.append("newCell", newCell);

        return $http.post('/user/updateCell', formData, {
            transformRequest: angular.identity,
            responseType: 'json',
            headers: {'Content-Type': undefined}
        })
                .then(function (response) {
                    return "ok";
                }, function (response) {
                    return "No se pudo actualizar el teléfono";
                });
    };
    
});
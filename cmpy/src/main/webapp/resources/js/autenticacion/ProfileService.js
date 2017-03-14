autenticacion.service('profileService', function ($http, $log) {
    
    this.updateProfile = function (nombre,apellido,telefono,sexo,dia,mes,anio) {
        var formData = new FormData();
        formData.append("nombre", nombre);
        formData.append("apellido", apellido);
        formData.append("telefono", telefono);
        formData.append("sexo", sexo);
        formData.append("dia", dia);
        formData.append("mes", mes);
        formData.append("anio", anio);

        return $http.post('/user/updateProfile', formData, {
            transformRequest: angular.identity,
            responseType: 'json',
            headers: {'Content-Type': undefined}
        })
                .then(function (response) {
                    return "ok";
                }, function (response) {
                    return "error en la actualizacion";
                });
    };
    
    this.getProfile = function () {
        var req = "/user/getProfile";
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    };    
    

});

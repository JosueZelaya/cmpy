mensajes.service('mensajesService',
        ['$http',
            function ($http) {

                this.getMensajeUsuario = function (usuarioId, page) {
                    return $http.get("/mensaje/get/" + usuarioId + "/" + page)
                            .success(function (response)
                            {
                                return response;
                            });
                };


                this.setMensaje = function (titulo, texto, destinatarios) {

                    var formData = new FormData();
                    formData.append("titulo", titulo);
                    formData.append("texto", texto);

                    for (var index = 0; index < destinatarios.length; ++index) {
                        formData.append("destinatarios", destinatarios[index]);
                    }

                    return $http.post('/mensaje/set', formData, {
                        transformRequest: angular.identity,
                        responseType: 'json',
                        headers: {'Content-Type': undefined}
                    })
                            .success(function (response) {
                                return "Mensaje enviado...";
                            })
                            .error(function () {
                                return "Ocurrio un error";
                            });

                };

                this.getUsuarios = function () {
                    return $http.get("/mensaje/usuarios/get")
                            .success(function (response)
                            {
                                return response;
                            });
                };

                this.getMensajesNoLeidos = function (page) {
                    return $http.get("/mensaje/get/noleidos/" + page)
                            .success(function (response)
                            {
                                return response;
                            });

                };

                this.getMensajesNoLeidosTotal = function () {
                    return $http.get("/mensaje/get/noleidos/total")
                            .success(function (response)
                            {
                                return response;
                            });

                };

                this.getMensajeUsuarioNoleidoTotal = function (usuarioId) {
                    return $http.get("/mensaje/get/usuario/noleidos/total/" + usuarioId)
                            .success(function (response)
                            {
                                return response;
                            });

                };

                this.setMensajeUsuarioLeido = function (usuarioId) {
                    return $http.get("/mensaje/set/leido/" + usuarioId)
                            .success(function (response)
                            {
                                return response;
                            });

                };


            }]);


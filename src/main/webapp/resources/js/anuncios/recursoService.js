modulo_anuncios.service('recursoService',
        ['$http',
            '$log',
            function ($http, $log) {

                this.getRecursos = function (idPublicacion, page) {
                    var req = "/recurso/getRecursos/" + idPublicacion + "/" + page;
                    return $http.get(req)
                            .success(function (response)
                            {
                                return response;
                            });
                };

            }]);
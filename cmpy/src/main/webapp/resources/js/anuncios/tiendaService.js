modulo_anuncios.service('tiendaService',
        ['$http',
            '$log',
            function ($http, $log) {
                this.getTiendasAleatorias = function () {
                    var req = "/tiendas/aleatorias/";
                    return $http.get(req)
                            .success(function (response)
                            {
                                return response;
                            });
                };
            }]);
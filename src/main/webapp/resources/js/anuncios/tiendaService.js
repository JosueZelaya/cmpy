modulo_anuncios.service('tiendaService', function ($http, $log) {
   this.getTiendasAleatorias = function () {
        var req = "/tiendas/aleatorias/";
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    }; 
});
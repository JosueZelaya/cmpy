productos.factory('productosService', ['$http', function ($http) {

        function getAnuncios(tipo,page) {
            var productos = {};
            var req = {
                url: "/publicacion/getAnuncios",
                data: {tipo: tipo, page: page}
            };
            $http.get(req)
                    .success(function (response)
                    {
                        produtos = response.rows;
                    });
            return productos;
        }
    }]);
categorias.service('catService',
        ['$http',
            function ($http) {

                this.getCategorias = function () {
                    return $http.get("/categoria/todos")
                            .success(function (response)
                            {
                                return response;
                            });
                };


            }]);



modulo_anuncios.controller('busquedaController',
        ['$scope',
            '$stateParams',
            'anunciosService',
            'TIPO_PUBLICACION',
            function ($scope, $stateParams, anunciosService, TIPO_PUBLICACION) {

                var eliminarPublicacion = function (publicacionId) {
                    return anunciosService.eliminarPublicacion(publicacionId)
                            .success(function (respuesta) {
                                return respuesta;
                            });
                };

                var setVendido = function (publicacionId) {
                    return anunciosService.setVendido(publicacionId)
                            .success(function (respuesta) {
                                return respuesta;
                            });
                };

                $scope.eliminarPublicacion = function (publicacionId, index) {
                    eliminarPublicacion(publicacionId)
                            .success(function (respuesta) {
                                $scope.publicaciones.splice(index, 1);
                            });
                };

                $scope.setVendido = function (publicacionId, index) {
                    setVendido(publicacionId)
                            .success(function (respuesta) {
                                $scope.publicaciones.splice(index, 1);
                            });
                };

                $scope.paginaSiguiente = function () {
                    $scope.page++;
                    cargarPublicacionesGratisByMatch($scope.page);
                };

                $scope.getNumbers = function (num) {
                    return new Array(num);
                };

                var getPublicacionesByMatch = function (pagina, match) {
                    return anunciosService.getAnunciosByMatch(pagina, match)
                            .success(function (publicaciones) {
                                return publicaciones;
                            });
                };

                var cargarPublicacionesGratisByMatch = function (page) {
                    getPublicacionesByMatch(page, $scope.terminoBusqueda)
                            .success(function (publicaciones) {
                                $scope.publicaciones = publicaciones;
                            });
                };

                var init = function () {
                    $scope.terminoBusqueda = $stateParams.terminoBusqueda;
                    $scope.publicaciones = [];
                    $scope.page = 0;

                    cargarPublicacionesGratisByMatch($scope.page);

                };

                init();

            }]);
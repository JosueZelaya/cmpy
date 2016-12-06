modulo_anuncios.controller('publicacionesFiltradasController',
        ['$scope',
            '$stateParams',
            'anunciosService',
            'TIPO_PUBLICACION',
            function ($scope, $stateParams, anunciosService, TIPO_PUBLICACION) {

                var getPublicacionesByCat = function (tipo, pagina, cat, nivel) {
                    return anunciosService.getAnunciosByCat(tipo, pagina, cat, nivel)
                            .success(function (publicaciones) {
                                return publicaciones;
                            });
                };

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
                    cargarPublicacionesGratisByCat($scope.page);
                };

                $scope.getNumbers = function (num) {
                    return new Array(num);
                };

                var cargarPublicacionesGratisByCat = function (page) {
                    getPublicacionesByCat(TIPO_PUBLICACION.GRATIS, page, $scope.cat, $scope.nivel)
                            .success(function (publicaciones) {
                                $scope.publicaciones = publicaciones;
                            });
                };

                var init = function () {
                    $scope.cat = $stateParams.cat;
                    $scope.nivel = $stateParams.nivel;
                    $scope.publicaciones = [];
                    $scope.page = 0;
                    cargarPublicacionesGratisByCat($scope.page);

                };

                init();

            }]);

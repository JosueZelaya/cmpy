modulo_anuncios.controller('anunciosController',
        ['$rootScope',
            '$scope',
            'anunciosService',
            'TIPO_PUBLICACION',
            '$window',
            'toastr',
            function ($rootScope, $scope, anunciosService, TIPO_PUBLICACION, $window, toastr) {
                
                $rootScope.rendercat = false;

                var getPublicacionesByCat = function (tipo, pagina, cat, nivel) {
                    return anunciosService.getAnunciosByCat(tipo, pagina, cat, nivel)
                            .success(function (publicaciones) {
                                return publicaciones;
                                setTimeout(function(){ FB.XFBML.parse(); }, 100);
                            });
                };

                var getMisPublicaciones = function (tipo, pagina) {
                    return anunciosService.getMisAnuncios(tipo, pagina)
                            .success(function (publicaciones) {
                                return publicaciones;
                                setTimeout(function(){ FB.XFBML.parse(); }, 100);
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

                var getAnunciosMezclados = function (tipo, pagina) {
                    return anunciosService.getAnunciosMezclados(tipo, pagina)
                            .success(function (publicaciones) {
                                return publicaciones;
                                setTimeout(function(){ FB.XFBML.parse(); }, 100);
                            });
                };

                var cargarPublicacionesGratis = function (page) {
                    getAnunciosMezclados(page)
                            .success(function (publicaciones) {
                                $scope.publicaciones = $scope.publicaciones.concat(publicaciones);
                                setTimeout(function(){ FB.XFBML.parse(); }, 100);
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
                    cargarPublicacionesGratis($scope.page);
                };

                $scope.getNumbers = function (num) {
                    return new Array(num);
                };

                $scope.goExternal = function (url) {
                    $window.open(url);
                };

                $rootScope.agregarPublicacion = function (publicacion) {
                    $scope.publicaciones.unshift(publicacion);
                    toastr.success("Se agregado tu publicacion", {});
                };

                $rootScope.verMisPublicaciones = function (page) {
                    $scope.navCollapsed = !$scope.navCollapsed;
                    getMisPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                            .success(function (publicaciones) {
                                $scope.publicaciones = publicaciones;
                            });
                };

                $rootScope.cargarPublicacionesGratisByCat = function (page, cat, nivel) {

                    getPublicacionesByCat(TIPO_PUBLICACION.GRATIS, page, cat, nivel)
                            .success(function (publicaciones) {
                                $scope.publicaciones = publicaciones;
                            });
                };

                $rootScope.cargarPublicacionesGratis = function () {
                    $scope.publicaciones = [];
                    cargarPublicacionesGratis(0);
                };

                var init = function () {
                    $scope.baseImgUrl = "https://res.cloudinary.com/dm8oxzlpb/image/upload/";
                    $scope.thumbSize = "h_150/";
                    $scope.publicaciones = [];
                    $scope.page = 0;
                    cargarPublicacionesGratis($scope.page);
                    $rootScope.rendercat=true;
                };

                init();

            }]);

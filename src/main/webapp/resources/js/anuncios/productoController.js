/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


modulo_anuncios.controller('productoController',
        ['$scope',
            '$stateParams',
            'anunciosService',
            'comentariosService',
            'recursoService',
            function ($scope, $stateParams, anunciosService, comentariosService, recursoService) {

                var getPublicacion = function (id) {
                    return anunciosService.getPublicacionById(id)
                            .success(function (publicacion) {
                                return publicacion;
                            });
                };

                var getComentarios = function (publicacionId) {
                    return comentariosService.getComentarios(publicacionId, 0)
                            .success(function (comentarios) {
                                return comentarios;
                            });
                };

                var getRecursos = function (publicacionId) {
                    return recursoService.getRecursos(publicacionId, 0)
                            .success(function (recursos) {
                                return recursos;
                            });
                };

                var cargarDetallePublicacion = function (publicacionId) {

                    $scope.publicacion = undefined;

                    getPublicacion(publicacionId).success(function (publicacion) {
                        $scope.publicacion = publicacion;
                    });

                    getComentarios(publicacionId).success(function (comentarios) {
                        $scope.comentarioList = comentarios;
                    });

                    getRecursos(publicacionId).success(function (recursos) {
                        $scope.recursoList = recursos;
                    });

                };

                $scope.agregarComentario = function () {

                    var publicacion_id = $scope.publicacion.id;
                    var comentario = $scope.comentario;
                    $scope.comentario = "";

                    if (comentario === "" || comentario === undefined) {
                        return;
                    }

                    comentariosService.agregarComentario(publicacion_id, comentario)
                            .success(function (comentario) {
                                $scope.comentarioList.unshift(comentario);
                            });

                };

                $scope.getNumbers = function (num) {
                    return new Array(num);
                };

                var init = function () {

                    var publicacionId = $stateParams.publicacionId;

                    if (publicacionId !== undefined) {
                        cargarDetallePublicacion(publicacionId);
                    }

                };

                init();

            }]);

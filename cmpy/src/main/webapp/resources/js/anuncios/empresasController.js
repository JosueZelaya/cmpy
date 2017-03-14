/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

modulo_anuncios.controller('empresasController',
        ['$scope',
            'tiendaService',
            function ($scope, tiendaService) {

                var getTiendasAleatorias = function () {
                    return tiendaService.getTiendasAleatorias()
                            .success(function (tiendas) {
                                return tiendas;
                            });
                };

                $scope.getNumbers = function (num) {
                    return new Array(num);
                };

                var init = function () {

                    getTiendasAleatorias().success(function (tiendas) {
                        $scope.tiendas = tiendas;
                    });

                };

                init();

            }]);

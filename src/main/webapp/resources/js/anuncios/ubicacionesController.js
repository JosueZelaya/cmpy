/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

modulo_anuncios.controller('ubicacionesController', function ($rootScope, $scope, $stateParams, anunciosService, mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {

    var init = function () {

        var publicacionId = $stateParams.publicacionId;

        if (publicacionId !== undefined) {
            mapService.getUbicaciones(publicacionId)
                    .success(function (response)
                    {
                        angular.forEach(response, function (item) {
                            var marker = {};
                            marker.id = item.id;
                            marker.title = 'ubi' + item.id.toString();
                            marker.latitude = item.latitud;
                            marker.longitude = item.longitud;
                            $rootScope.markers.push(marker);
                        });

                    });
        }

    };

    init();

});

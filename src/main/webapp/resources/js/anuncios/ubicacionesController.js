/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

modulo_anuncios.controller('ubicacionesController', function ($rootScope, $scope,$http, $stateParams, anunciosService, mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {

    var init = function () {

        var publicacionId = $stateParams.publicacionId;
        

        if (publicacionId !== undefined) {
            $scope.alladdress = [];
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
                            
                            var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + item.latitud + "," + item.longitud + "&sensor=true&key=AIzaSyCUv4YsbHpTGv3j5PkAl2x4guWokqIuEEM";
                            $http.get(url,{ headers: {'X-Requested-With': undefined}})
                            .then(function(result) {
                                var address = result.data.results[0].formatted_address;
                                $scope.alladdress.push(address);
                            });
                            
                            
                        });

                    });
                    
                 
                    
        }

    };

    init();

});

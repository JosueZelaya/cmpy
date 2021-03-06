maps.controller('mapController',
        ['$rootScope',
            '$scope',
            '$http',
            'mapService',
            '$timeout',
            '$window',
            'matchmedia',
            '$stateParams',
            function ($rootScope, $scope, $http, mapService, $timeout, $window, matchmedia, $stateParams) {


                $scope.address = "";

                $scope.render = true;

                $rootScope.markers = [];

                $scope.marker = {
                    options: {
                        draggable: true
                    },
                    events: {
                        rightclick: function (mapModel, MouseEvent, originalEventArgs)
                        {
                            var id = originalEventArgs.id;
                            var max = $rootScope.markers.length;
                            for (i = 0; i < max; i++) {
                                if ($rootScope.markers[i] !== undefined) {
                                    if (id === $rootScope.markers[i].id) {
                                        $rootScope.markers.splice(i, 1);
                                        $rootScope.$apply();
                                    }
                                }
                            }

                        }
                    }
                };

                $rootScope.setUbicaciones = function ()//(publicacion_id)
                {
                    var listaUbicacion = [];

                    angular.forEach($rootScope.markers, function (item) {
                        var ubicacion = {};
                        ubicacion.id = item.id;
                        ubicacion.latitud = item.latitude;
                        ubicacion.longitud = item.longitude;
                        listaUbicacion.push(ubicacion);
                    });

                    $rootScope.ubicaciones = listaUbicacion;

                    //mapService.setUbicaciones(publicacion_id,listaUbicacion);

                    //$http.post("/ubicacion/publicacion/set/" + publicacion_id.toString(), listaUbicacion);
                };

                $scope.getUbicaciones = function (publicacion_id)
                {
                    $rootScope.markers = [];

                    //$http.get("/ubicacion/publicacion/get/" + publicacion_id.toString())
                    mapService.getUbicaciones(publicacion_id)
                            .success(function (response)
                            {
                                angular.forEach(response, function (item) {
                                    var marker = {};
                                    marker.id = item.id;
                                    marker.title = 'ubi' + item.id.toString();
                                    marker.latitude = item.latitud;
                                    marker.longitude = item.longitud;
                                    $rootScope.markers.push(marker);
                                    $rootScope.$apply();
                                });

                            });
                };

                $scope.events = {
                    tilesloaded: function (map, eventName, originalEventArgs) {
                    },
                    click: function (mapModel, MouseEvent, originalEventArgs)
                    {
                        var e = originalEventArgs[0];
                        var lat = e.latLng.lat();
                        var lng = e.latLng.lng();
                        var id = 0;
                        if ($rootScope.markers[$rootScope.markers.length - 1] !== undefined) {
                            var id = $rootScope.markers[$rootScope.markers.length - 1].id;
                        }
                        id++;

                        var marker = {};
                        marker.id = id;
                        marker.title = "ubi" + id.toString();
                        marker.latitude = lat;//e.latLng.lat;
                        marker.longitude = lng;//e.latLng.lng;
                        $rootScope.markers.push(marker);
                        $rootScope.$apply();
                    }

                };

//busqueda//

                $scope.searchbox = {
                    template: 'searchbox.tpl.html',
                    events: {
                        places_changed: function (searchBox) {

                            console.log(searchBox.getPlaces()[0].geometry);

                            console.log(searchBox.getPlaces()[0].geometry.location.lat());

                            var tmp_lat = searchBox.getPlaces()[0].geometry.location.lat();
                            var tmp_lng = searchBox.getPlaces()[0].geometry.location.lng();

                            $scope.center = {
                                latitude: tmp_lat,
                                longitude: tmp_lng
                            };

                            $scope.zoom = 17;

                            console.log(searchBox.getPlaces());



                        }
                    }
                };
                
                $scope.searchbox2 = {
                    template: 'searchbox2.tpl.html',
                    position:'top-center',
                    events: {
                        places_changed: function (searchBox) {

                            console.log(searchBox.getPlaces()[0].geometry);

                            console.log(searchBox.getPlaces()[0].geometry.location.lat());

                            var tmp_lat = searchBox.getPlaces()[0].geometry.location.lat();
                            var tmp_lng = searchBox.getPlaces()[0].geometry.location.lng();

                            $scope.center = {
                                latitude: tmp_lat,
                                longitude: tmp_lng
                            };

                            $scope.zoom = 17;

                            console.log(searchBox.getPlaces());



                        }
                    }
                };                


                $scope.getUbicacionGPS = function () {


                    if ($window.navigator.geolocation)
                    {

                        $window.navigator.geolocation.getCurrentPosition(function (objPosition)
                        {

                            $scope.center = {
                                latitude: objPosition.coords.latitude,
                                longitude: objPosition.coords.longitude
                            };
                            $scope.zoom = 17;


                            var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + objPosition.coords.latitude + "," + objPosition.coords.longitude + "&sensor=true&key=AIzaSyCUv4YsbHpTGv3j5PkAl2x4guWokqIuEEM";
                            $http.get(url, {headers: {'X-Requested-With': undefined}})
                                    .then(function (result) {
                                        var address = result.data.results[0].formatted_address;
                                        $scope.address = address;
                                    });

                        }, function (objPositionError)
                        {
                            switch (objPositionError.code)
                            {
                                case objPositionError.PERMISSION_DENIED:
                                    alert("No se ha permitido el acceso a la posición del usuario.");
                                    break;
                                case objPositionError.POSITION_UNAVAILABLE:
                                    alert("No se ha podido acceder a la información de su posición.");
                                    break;
                                case objPositionError.TIMEOUT:
                                    alert("El servicio ha tardado demasiado tiempo en responder.");
                                    break;
                                default:
                                    alert("Error desconocido.");
                            }
                        }
                        , {
                            maximumAge: 75000,
                            timeout: 30000
                        });

                    } else
                    {
                        alert("Su navegador no soporta la API de geolocalización.");
                    }

                    $timeout(function () {
                        var evt = $window.document.createEvent('UIEvents');
                        evt.initUIEvent('resize', true, false, $window, 3000);
                        $window.dispatchEvent(evt);

                    });

                };

                var unsub = {};
                $scope.isPhoneOrTablet = false;

                unsub['phone'] = matchmedia.onPhone(function (mediaQueryList) {
                    $scope.isPhone = mediaQueryList.matches;
                    if ($scope.isPhone || $scope.isTablet)
                    {
                        $scope.isPhoneOrTablet = true;
                        $scope.zoom = 8;
                        $scope.center = {
                            latitude: 13.7153719325982,
                            longitude: -88.95217895507812
                        };
                    } else
                    {
                        $scope.isPhoneOrTablet = false;
                        $scope.zoom = 9;
                        $scope.center = {
                            latitude: 13.7153719325982,
                            longitude: -88.95217895507812
                        };
                    }
                });
                unsub['tablet'] = matchmedia.onTablet(function (mediaQueryList) {
                    $scope.isTablet = mediaQueryList.matches;
                    if ($scope.isPhone || $scope.isTablet)
                    {
                        $scope.isPhoneOrTablet = true;
                        $scope.zoom = 8;
                        $scope.center = {
                            latitude: 13.7153719325982,
                            longitude: -88.95217895507812
                        };
                    } else
                    {
                        $scope.isPhoneOrTablet = false;
                        $scope.zoom = 9;
                        $scope.center = {
                            latitude: 13.7153719325982,
                            longitude: -88.95217895507812
                        };
                    }
                });

                $scope.$on('$destroy', function () {
                    // say goodbye to your listeners here

                    unsub['phone']();
                    unsub['tablet']();

                });

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
                
                $timeout(function () {
                    var evt = $window.document.createEvent('UIEvents');
                    evt.initUIEvent('resize', true, false, $window, 3000);
                    $window.dispatchEvent(evt);
                    
                    if($scope.isPhoneOrTablet == false){
                        
                        $scope.zoom = 9;
                    }
                    else{
                        $scope.zoom = 8;
                        
                    }

                    $scope.center = {
                        latitude: 13.71537193259822,
                        longitude: -88.952178955078122
                    };

                });

            }]);






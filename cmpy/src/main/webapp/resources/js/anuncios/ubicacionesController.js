/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

modulo_anuncios.controller('ubicacionesController',
        ['$rootScope',
            '$scope',
            '$http',
            '$stateParams',
            'mapService',
            '$timeout',
            '$window',
            'matchmedia',
            function ($rootScope, $scope, $http, $stateParams, mapService,$timeout,$window,matchmedia) {
                
                

                var init = function () {

                    var publicacionId = $stateParams.publicacionId;
                    $rootScope.markers =[];
                    

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
                                        $http.get(url, {headers: {'X-Requested-With': undefined}})
                                                .then(function (result) {
                                                    var address = result.data.results[0].formatted_address;
                                                    $scope.alladdress.push(address);
                                                });


                                    });

                                });



                    }

                };

                init();
                
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

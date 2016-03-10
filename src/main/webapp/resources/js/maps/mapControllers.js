maps.controller('mapController', function($scope,$http,mapService) {

    $scope.center = {
        latitude: 13.7724376,
        longitude: -88.7833089
    };
    
    $scope.zoom = 9;
    $scope.markers = [];
    
    $scope.marker = {
        options: {
        draggable: true
        },
        events:{
            rightclick :function(mapModel,MouseEvent,originalEventArgs)
            {
                var id = originalEventArgs.id;
                var max = $scope.markers.length;
                for(i = 0; i < max; i++){
                    if($scope.markers[i] !== undefined){
                        if(id === $scope.markers[i].id){
                            $scope.markers.splice(i,1);
                            $scope.$apply();
                        }
                    }
                }
                
            }
      }
    };
    
    $scope.setUbicaciones = function(publicacion_id)
    {
        var listaUbicacion = [];
        
        angular.forEach($scope.markers, function(item){
                var ubicacion = {};
                ubicacion.id = item.id;
                ubicacion.latitud = item.latitude;
                ubicacion.longitud = item.longitude;
                listaUbicacion.push(ubicacion);
        });
        
        mapService.setUbicaciones(publicacion_id,listaUbicacion);
        
        //$http.post("/ubicacion/publicacion/set/" + publicacion_id.toString(), listaUbicacion);
    }; 
    
    $scope.getUbicaciones = function(publicacion_id)
    {
        //$http.get("/ubicacion/publicacion/get/" + publicacion_id.toString())
        mapService.getUbicaciones(publicacion_id)
        .success(function (response)
        {
            angular.forEach(response, function(item){
                var marker = {};
                marker.id = item.id;
                marker.title = 'ubi'+item.id.toString();
                marker.latitude = item.latitud;
                marker.longitude = item.longitud;
                $scope.markers.push(marker);
            });
            
        });
    };
    
    $scope.events = {
        tilesloaded: function (map, eventName, originalEventArgs) {
        },
        click: function(mapModel,MouseEvent,originalEventArgs)
          {
            var e = originalEventArgs[0];
            var lat = e.latLng.lat();
            var lng = e.latLng.lng();
            var id = 0;
            if($scope.markers[$scope.markers.length -1] !== undefined){
                var id = $scope.markers[$scope.markers.length -1].id;
            }
            id++;
            
            var marker = {};
            marker.id = id;
            marker.title = "ubi"+id.toString();
            marker.latitude = lat;//e.latLng.lat;
            marker.longitude = lng;//e.latLng.lng;
            $scope.markers.push(marker);
            $scope.$apply();
          }

    };
    
  
    
//    $http.get("/ubicacion/publicacion/get/8")
//    .success(function (response)
//    {
//        angular.forEach(response, function(item){
//            var marker = {};
//            marker.id = item.id;
//            marker.title = 'ubi'+item.id.toString();
//            marker.latitude = item.latitud;
//            marker.longitude = item.longitud;
//            $scope.markers.push(marker);
//        });
//    });
    

  });






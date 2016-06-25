/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
maps.service('mapService', function ($http) {
    
    this.setUbicaciones = function (publicacion_id, listaUbicacion) {
        return $http.post("/ubicacion/publicacion/set/" + publicacion_id.toString(), listaUbicacion)            
           .success(function (response)
            {
                return response;
            });
    };
    
    this.getUbicaciones = function(publicacion_id){
        return  $http.get("/ubicacion/publicacion/get/" + publicacion_id.toString())           
           .success(function (response)
            {
                return response;
            });       
        
    };
     this.getUbicacion = function(publicacion_id){
        
        return $http.get("/ubicacion/publicacion/get/" + publicacion_id.toString())           
           .success(function (response)
            {
                var markers = new Array();
                
                for(var i = 0;i< response.length;i++)
                {
                    var marker = {};
                    marker.id = response[i].id;
                    marker.title = 'ubi'+response[i].id.toString();
                    marker.latitude = response[i].latitud;
                    marker.longitude = response[i].longitud;
                    markers.push(marker);
                    
                }
                
                 /*angular.forEach(response, function(item){
                 var marker = {};
                 marker.id = item.id;
                 marker.title = 'ubi'+item.id.toString();
                 marker.latitude = item.latitud;
                 marker.longitude = item.longitud;
                 markers.push(marker);
                });*/
                
                return markers;
                
            });  
        
        
    };
    
   
   
    
    
    

});




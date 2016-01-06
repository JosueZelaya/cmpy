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
    

});




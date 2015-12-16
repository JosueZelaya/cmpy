modulo_anuncios.service('anunciosService', ['$http', function ($http) {
    
    this.getAnuncios = function (tipo,page) {
        var req = "/publicacion/getAnuncios/"+tipo+"/"+page;
        return $http.get(req)            
           .success(function (response)
            {
                return response;
            });
    }
    

}]);
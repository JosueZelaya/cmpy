modulo_anuncios.service('comentariosService', function ($http, $log) {
    
    this.getComentarios = function (idPublicacion, page) {
        var req = "/comentario/getComentarios/" + idPublicacion + "/" + page;
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    };
    
    this.agregarComentario = function (publicacion_id, comentario){
        var formData = new FormData();
        formData.append("publicacion_id", publicacion_id);
        formData.append("comentario", comentario);
        
        return $http.post('/comentario/agregarComentario', formData, {
            transformRequest: angular.identity,
            responseType:'json',
            headers: {'Content-Type': undefined}            
        }).success(function(){
//            debugger;
            return "Comentario Agregado!";
        });
    };

});
modulo_anuncios.service('anunciosService', function ($http, $log) {

    this.getTotalAnuncios = function(tipo){
        var req = "/publicacion/getTotalAnuncios/"+tipo;
        return $http.get(req)
                .success(function(response){
                   return response; 
                });
    }

    this.getTotalPaginas = function(tipo){
        var req = "/publicacion/getTotalPaginas/"+tipo;
        return $http.get(req)
                .success(function(response){
                    return response;
                });
    }

    this.getPublicacionById = function (id){
        var req = "/publicacion/getPublicacionById/"+id;
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    }

    this.getAnuncios = function (tipo, page) {
        var req = "/publicacion/getAnuncios/" + tipo + "/" + page;
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    };


    this.agregarPublicacion = function (publicacion) {

        var formData = new FormData();
        formData.append("titulo", publicacion.titulo);
        formData.append("precio", publicacion.precio);
        formData.append("descripcion", publicacion.descripcion);
        //formData.append("ubicaciones", publicacion.ubicaciones);
        //debugger;
        
        var res = angular.toJson(publicacion.ubicaciones);
        formData.append("ubicaciones", res.toString());

//formData.append("ubicaciones", "Hola");
        
        
        //for (var index = 0; index < publicacion.ubicaciones.length; ++index) {
          //  formData.append("ubicaciones", publicacion.ubicaciones[index]);
        //}
        
      

        //Se agregan todas las imagenes... Cada una debe llamarse: multipleFiles        
        //Para que Spring entienda que están relacionadas y que se trata de una colección o lista
        for (var index = 0; index < publicacion.imagenes.length; ++index) {
            formData.append("multipleFiles", publicacion.imagenes[index]);
        }

        

        return $http.post('/publicacion/agregarPublicacion', formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
            return "Publicacion Agregada!";
        })
        .error(function(){
        });     

    };

});

modulo_anuncios.service('Publicacion', function () {

    /**
     * Constructor, with class name
     */
    function Publicacion(titulo, precio, descripcion) {
        // Public properties, assigned to the instance ('this')
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
//        this.imagenes = imagenes;
    }

    /**
     * Public method, assigned to prototype
     */
    Publicacion.prototype.setImagenes = function (imagenes) {
        this.imagenes = imagenes;
    };
    
    Publicacion.prototype.setUbicaciones = function (ubicaciones) {
        this.ubicaciones = ubicaciones;
    };

    return Publicacion;

});

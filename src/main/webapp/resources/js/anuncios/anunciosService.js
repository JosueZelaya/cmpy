modulo_anuncios.service('anunciosService',function ($http,$log) {
    
    this.getAnuncios = function (tipo,page) {
        var req = "/publicacion/getAnuncios/"+tipo+"/"+page;
        return $http.get(req)            
           .success(function (response)
            {
                return response;
            });
    };
    
    
    this.agregarPublicacion = function(publicacion,callback){
        
        var formData = new FormData();
        formData.append("titulo",publicacion.titulo);
        formData.append("precio",publicacion.precio);
        formData.append("descripcion",publicacion.descripcion);
        
        //Se agregan todas las imagenes... Cada una debe llamarse: multipleFiles        
        //Para que Spring entienda que están relacionadas y que se trata de una colección o lista
        for (var index = 0; index < publicacion.imagenes.length; ++index) {            
            formData.append("multipleFiles",publicacion.imagenes[index]);
        }

        var request = new XMLHttpRequest();
        request.open('POST',"/publicacion/agregarPublicacion");
        request.send(formData);
        
        if (request.readyState === 4 && request.status === 200) {
            $log.info("Publicacion agregada!");            
        }
        callback();
    };

});

modulo_anuncios.service('Publicacion', function(){
    
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
  
    return Publicacion;
  
});

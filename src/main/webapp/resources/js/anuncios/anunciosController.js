modulo_anuncios.controller('anunciosController', function ($rootScope, $scope, $stateParams, anunciosService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
    var getPublicacion = function(id){
        return anunciosService.getPublicacionById(id)
                .success(function (publicacion){
                   return publicacion; 
                });
    }
    
    var getPublicaciones = function(tipo,pagina) {
        return anunciosService.getAnuncios(tipo,pagina)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };
    
    var cargarPublicacionesGratis = function(){
        getPublicaciones(TIPO_PUBLICACION.GRATIS, '0')
                .success(function(publicaciones){
            $rootScope.publicaciones = publicaciones;
        });
    };
    
    var cargarPublicacionesPagadas = function(){
        getPublicaciones(TIPO_PUBLICACION.PAGADA, '0')
                .success(function(publicaciones){
            $rootScope.anuncios = publicaciones;
//            
//            $location.hash('publicacionesProductos');
//            $anchorScroll();
        });
    };
    
    var cargarDetallePublicacion = function(id){
        getPublicacion(id).success(function(publicacion){
            $rootScope.publicacion = publicacion;
        });
    };
    
    var crearPublicacion = function(){
        //Se recogen los datos de la publicacion        
        var imagenes = new Array();
        for (var index = 0; index < $scope.existingFlowObject.files.length; ++index) {
            imagenes[index] = $scope.existingFlowObject.files[index].file;
        }

        var publicacion = new Publicacion($scope.titulo, $scope.precio, $scope.descripcion);
        publicacion.setImagenes(imagenes);
        
        return publicacion;
    };
    
    var guardarPublicacion = function(publicacion){
        
        anunciosService.agregarPublicacion(publicacion)
                .success(function(respuesta){
                    
            $scope.cancel(); //cerrar el dialogo
            cargarPublicacionesGratis(); //recarga las publicaciones
        });
        
    };

    $scope.agregarPublicacion = function () {

        var publicacion = crearPublicacion();
        
        guardarPublicacion(publicacion);
        
    };
    
    var init = function () {
        
        // inicializando el componente de carga de imagenes
        $scope.existingFlowObject = flowFactory.create({});
        
        cargarPublicacionesGratis();
        
        cargarPublicacionesPagadas();
        
        var publicacionId = $stateParams.publicacionId;
        if(publicacionId!==null){
            cargarDetallePublicacion(publicacionId);   
        }
    };
    
    init();

});

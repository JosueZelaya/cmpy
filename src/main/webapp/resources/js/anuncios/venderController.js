modulo_anuncios.controller('venderController', function ($rootScope, $scope, $stateParams, anunciosService,mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
    var getPublicaciones = function(tipo,pagina) {
        return anunciosService.getAnuncios(tipo,pagina)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };
    
    var cargarPublicacionesGratis = function(page){
        getPublicaciones(TIPO_PUBLICACION.GRATIS, page)
                .success(function(publicaciones){
            $rootScope.publicaciones = publicaciones;
        });
    };
    
    var cargarPaginacion = function(){
        
        anunciosService.getTotalAnuncios(TIPO_PUBLICACION.GRATIS)
                .success(function (total){
                    $rootScope.totalAnuncios = total;
                    $rootScope.page = 1;
                });
        
    };
    
    var crearPublicacion = function(){
        //Se recogen los datos de la publicacion        
        var imagenes = new Array();
        for (var index = 0; index < $scope.existingFlowObject.files.length; ++index) {
            imagenes[index] = $scope.existingFlowObject.files[index].file;
        }
        
        var publicacion = new Publicacion('',$scope.titulo, $scope.precio, $scope.descripcion);
        publicacion.setImagenes(imagenes);
        publicacion.setUbicaciones($rootScope.ubicaciones);
        
        return publicacion;
    };
    
    var guardarPublicacion = function(publicacion){
        
        anunciosService.agregarPublicacion(publicacion)
                .success(function(respuesta){
                    
            $scope.cancel(); //cerrar el dialogo
            cargarPublicacionesGratis(0); //recarga las publicaciones
            cargarPaginacion();
        });
        
    };

    $scope.agregarPublicacion = function () {

        var publicacion = crearPublicacion();
        
        guardarPublicacion(publicacion);
        
    };
    
    var init = function () {
        
        // inicializando el componente de carga de imagenes
        $scope.existingFlowObject = flowFactory.create({});  
        
    };
    
    init();

});

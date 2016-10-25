modulo_anuncios.controller('anunciosController', function ($rootScope, $scope, $stateParams, anunciosService,mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
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
                    $scope.totalAnuncios = total;
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
        
        //console.log("ubicaciones",$rootScope.ubicaciones);

        
        return publicacion;
    };
    
    var guardarPublicacion = function(publicacion){
        
        anunciosService.agregarPublicacion(publicacion)
                .success(function(respuesta){
                    
            $scope.cancel(); //cerrar el dialogo
            cargarPublicacionesGratis(0); //recarga las publicaciones
        });
        
    };

    $scope.agregarPublicacion = function () {

        var publicacion = crearPublicacion();
        
        guardarPublicacion(publicacion);
        
    };
    
    $scope.paginaSiguiente = function(){
        $scope.page++;
        cargarPublicacionesGratis($scope.page);
    };
    
    $scope.paginaAnterior = function(){
        $scope.page--;
        cargarPublicacionesGratis($scope.page);
    };
    
    $scope.cargarPagina = function(){
        cargarPublicacionesGratis($scope.page-1);
    };
    
    $scope.getNumbers = function(num) {
        return new Array(num);   
    };
    
    var init = function () {
        
        var publicacionId = $stateParams.publicacionId;
        
        if(publicacionId!==undefined){
            return;
        }
        
        // inicializando el componente de carga de imagenes
        $scope.existingFlowObject = flowFactory.create({});        
        $scope.page = 0;
//        $rootScope.totalPaginas = [];
        $scope.totalAnuncios = 0;
        
        cargarPublicacionesGratis($scope.page);
        
        cargarPaginacion();
        
        
    };
    
    init();

});

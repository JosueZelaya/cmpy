modulo_anuncios.controller('venderController', function ($rootScope, $scope, $stateParams, anunciosService,mapService,catService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
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
    
    //catagorias
    
    $scope.categorias_nivel1 = [];
    $scope.categorias_nivel2 = [];
    $scope.categorias_nivel3 = [];
    
    $scope.select_nivel1;
    $scope.select_nivel2;
    $scope.select_nivel3;  
    
    catService.getCategorias()
    .success(function (response) 
    {   
        $scope.categorias = response;
        $scope.getSelectNivel1();
    });  
    
    
    $scope.getSelectNivel1= function(){
        
         angular.forEach($scope.categorias, function (item) {
            $scope.categorias_nivel1.push(item);
        });       
    };
    
    $scope.getSelectNivel2 = function(){
        
        $scope.categorias_nivel2 = [];
        
        angular.forEach($scope.categorias, function (item) {
            debugger;
            if(item.id == $scope.select_nivel1)
            {
                angular.forEach(item.hijos, function (sub_item) {
                    $scope.categorias_nivel2.push(sub_item);
                });
            }
        });        
    };
    
    $scope.getSelectNivel3 = function(){
        
        $scope.categorias_nivel3 = [];
        
        angular.forEach($scope.categorias, function (item) {
            debugger;
            if(item.id == $scope.select_nivel1)
            {
                angular.forEach(item.hijos, function (sub_item) {
                     if(sub_item.id == $scope.select_nivel2)
                    {
                        angular.forEach(sub_item.hijos, function (sub_sub_item) {
                            $scope.categorias_nivel3.push(sub_sub_item);
                        });                       
                    }
                });
            }
        });        
    };
    
    $scope.setGlobalcat = function(){
        
        debugger;
        
        if(!isNaN($scope.select_nivel3))
        {
            $scope.catagoriaId = parseInt($scope.select_nivel3);  
        }
        
       
    };   

});

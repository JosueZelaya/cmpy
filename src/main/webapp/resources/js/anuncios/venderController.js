modulo_anuncios.controller('venderController', function ($rootScope, $scope, $stateParams, anunciosService,mapService,catService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
    var crearPublicacion = function(){
        //Se recogen los datos de la publicacion        
        var imagenes = new Array();
        for (var index = 0; index < $scope.existingFlowObject.files.length; ++index) {
            imagenes[index] = $scope.existingFlowObject.files[index].file;
        }
        
        var publicacion = new Publicacion('',$scope.titulo, $scope.precio, $scope.descripcion,$scope.catagoriaId, 'GRATIS');
        publicacion.setImagenes(imagenes);
        publicacion.setUbicaciones($rootScope.ubicaciones);
        
        return publicacion;
    };

    $scope.agregarPublicacion = function () {
        
        $scope.setGlobalcat();

        var publicacion = crearPublicacion();
        
        guardarPublicacion(publicacion);
        
        $scope.cancel(); //cerrar el dialogo
    };
    
    var guardarPublicacion = function(publicacion){
        
        anunciosService.agregarPublicacion(publicacion)
                .success(function(publicacion){        
            
            $rootScope.agregarPublicacion(publicacion);
        });
        
    };
    
    var init = function () {
        
        // inicializando el componente de carga de imagenes
        $scope.existingFlowObject = flowFactory.create({});  
        
    };
    
    init();
    
    //catagorias
    
    //$scope.categoriaSelected = "";
    $scope.categoriasAu = [];
    
    catService.getCategorias()
    .success(function (response) 
    {   
        $scope.categorias = response;
        formarCatAu();
        
    });  
    
    var formarCatAu = function(){
        $scope.categoriasAu = [];
        
        angular.forEach($scope.categorias, function (item) {
            $scope.categoriasAu.push({
                name:item.nombre,
                simple:item.nombre,
                id:item.id
            });
            angular.forEach(item.categoriaList, function (sub_item) {
                $scope.categoriasAu.push({
                    name:item.nombre + " / " + sub_item.nombre,
                    simple:sub_item.nombre,
                    id:sub_item.id
                });
                angular.forEach(sub_item.categoriaList, function (sub_sub_item) {
                    $scope.categoriasAu.push({
                        name:item.nombre + " / " + sub_item.nombre + " / " + sub_sub_item.nombre,
                        simple:sub_sub_item.nombre,
                        id:sub_sub_item.id
                    });
                });
            });
        });        
        
    };
    
    $scope.setGlobalcat = function(){
        
        if($scope.categoriaSelected!==undefined)
        {
            $scope.catagoriaId = parseInt($scope.categoriaSelected.id);  
        }
        
       
    };   

});

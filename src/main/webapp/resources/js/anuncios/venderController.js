modulo_anuncios.controller('venderController', function ($rootScope, $scope, $stateParams, anunciosService,mapService,catService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
    var crearPublicacion = function(){
        //Se recogen los datos de la publicacion        
        var imagenes = new Array();
        for (var index = 0; index < $scope.existingFlowObject.files.length; ++index) {
            imagenes[index] = $scope.existingFlowObject.files[index].file;
        }
        
        var publicacion = new Publicacion('',$scope.titulo, $scope.precio, $scope.descripcion,$scope.catagoriaId);
        publicacion.setImagenes(imagenes);
        publicacion.setUbicaciones($rootScope.ubicaciones);
        
        return publicacion;
    };

    $scope.agregarPublicacion = function () {

        var publicacion = crearPublicacion();
        
        $rootScope.guardarPublicacion(publicacion);
        $scope.cancel(); //cerrar el dialogo
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
            
            if(item.id == $scope.select_nivel1)
            {
                angular.forEach(item.categoriaList, function (sub_item) {
                    $scope.categorias_nivel2.push(sub_item);
                });
            }
        });        
    };
    
    $scope.getSelectNivel3 = function(){
        
        $scope.categorias_nivel3 = [];
        
        angular.forEach($scope.categorias, function (item) {
            
            if(item.id == $scope.select_nivel1)
            {
                angular.forEach(item.categoriaList, function (sub_item) {
                     if(sub_item.id == $scope.select_nivel2)
                    {
                        angular.forEach(sub_item.categoriaList, function (sub_sub_item) {
                            $scope.categorias_nivel3.push(sub_sub_item);
                        });                       
                    }
                });
            }
        });        
    };
    
    $scope.setGlobalcat = function(){
        
        if(!isNaN($scope.select_nivel3))
        {
            $scope.catagoriaId = parseInt($scope.select_nivel3);  
        }
        
       
    };   

});

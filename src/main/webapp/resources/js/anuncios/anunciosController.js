modulo_anuncios.controller('anunciosController',['$scope','anunciosService','TIPO_PUBLICACION','flowFactory',function($scope,anunciosService,TIPO_PUBLICACION,flowFactory){
    
    anunciosService.getAnuncios(TIPO_PUBLICACION.GRATIS,'0')
        .success(function(publicaciones) {
            $scope.publicaciones = publicaciones;
        });
    
        anunciosService.getAnuncios(TIPO_PUBLICACION.PAGADA,'0')
        .success(function(publicaciones) {
            $scope.anuncios = publicaciones;
        });

    $scope.existingFlowObject = flowFactory.create({
     target: 'http://example.com/upload'
    });

    agregarPublicacion = function(){
        console.log("AQUI ESTOY");        
        
        var formData = new FormData();
        
        var index;
        for (index = 0; index < $scope.existingFlowObject.files.length; ++index) {
            formData.append("multipleFiles",$scope.existingFlowObject.files[index].file);            
        }
        
        formData.append("titulo",$scope.titulo);
        formData.append("precio",$scope.precio);
        formData.append("descripcion",$scope.descripcion);
        
        var request = new XMLHttpRequest();
        request.open('POST',"/publicacion/agregarPublicacion");
        request.send(formData);
        
        console.log("FINISHED");
    };    
        
}]);

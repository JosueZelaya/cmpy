modulo_anuncios.controller('anunciosController', function ($scope, anunciosService, TIPO_PUBLICACION, flowFactory, Publicacion) {

    // inicializando el componente de carga de imagenes
    $scope.existingFlowObject = flowFactory.create({});

    anunciosService.getAnuncios(TIPO_PUBLICACION.GRATIS, '0')
            .success(function (publicaciones) {
                $scope.publicaciones = publicaciones;
            });

    anunciosService.getAnuncios(TIPO_PUBLICACION.PAGADA, '0')
            .success(function (publicaciones) {
                $scope.anuncios = publicaciones;
            });

    $scope.agregarPublicacion = function () {

        //Se recogen los datos de la publicacion        
        var imagenes = new Array();
        for (var index = 0; index < $scope.existingFlowObject.files.length; ++index) {
            imagenes[index] = $scope.existingFlowObject.files[index].file;
        }

        var publicacion = new Publicacion($scope.titulo, $scope.precio, $scope.descripcion);
        publicacion.setImagenes(imagenes);

        anunciosService.agregarPublicacion(publicacion, function () {
            $scope.cancel();
            
            var publicacionesNuevas;
            anunciosService.getAnuncios(TIPO_PUBLICACION.GRATIS, '0')
                        .success(function (publicaciones) {
                            $scope.publicaciones = publicaciones;                            
                        });
            
            
        });

    };

});

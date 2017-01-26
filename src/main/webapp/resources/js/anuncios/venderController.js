modulo_anuncios.controller('venderController',
        ['$rootScope',
            '$scope',
            'anunciosService',
            'catService',
            'flowFactory',
            'Publicacion',
            '$q',
            function ($rootScope, $scope, anunciosService, catService, flowFactory, Publicacion, $q) {
                
                $scope.obj = {};

//                $scope.$on('flow::fileAdded', function (event, $flow, flowFile) {
//                    console.log('This works');
//                    var nativeFile = flowFile; // instance of File, same as here: https://github.com/flowjs/ng-flow/blob/master/src/directives/img.js#L13
//                     //file.file = null;// do not display it
//                     var deferred = $q.defer();
//                     flowFile.promise = deferred.promise;
//
//                     loadImage(
//                         nativeFile,
//                         function(canvas) {
//                             canvas.toBlob(function(blob) {
//                                 flowFile.file = blob;
//                                 flowFile.size = blob.size;
//                                 flowFile.name = nativeFile.name;
//                                 console.debug("resized image to " + 300 + "xX");
//                                 deferred.resolve();
//                                 $scope.$digest();
//                             });
//                         }, {
//                             canvas: true,
//                             crop: true,
//                             maxWidth: 300
//                         }
//                     );
//
//                });

                var crearPublicacion = function () {
                    //Se recogen los datos de la publicacion 

                    var publicacion = new Publicacion('', $scope.titulo, $scope.precio, $scope.descripcion, $scope.catagoriaId, 'GRATIS');
                    publicacion.setImagenes($scope.imgs);
                    publicacion.setUbicaciones($rootScope.ubicaciones);

                    return publicacion;
                };

                $scope.agregarPublicacion = function () {

                    if (esValido()) {

                        $scope.setGlobalcat();

                        var publicacion = crearPublicacion();

                        guardarPublicacion(publicacion);

                        $scope.cancel(); //cerrar el dialogo
                    }
                };

                var guardarPublicacion = function (publicacion) {

                    anunciosService.agregarPublicacion(publicacion)
                            .success(function (publicacion) {

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

                var formarCatAu = function () {
                    $scope.categoriasAu = [];

                    angular.forEach($scope.categorias, function (item) {
                        $scope.categoriasAu.push({
                            name: item.nombre,
                            simple: item.nombre,
                            id: item.id
                        });
                        angular.forEach(item.categoriaList, function (sub_item) {
                            $scope.categoriasAu.push({
                                name: item.nombre + " / " + sub_item.nombre,
                                simple: sub_item.nombre,
                                id: sub_item.id
                            });
                            angular.forEach(sub_item.categoriaList, function (sub_sub_item) {
                                $scope.categoriasAu.push({
                                    name: item.nombre + " / " + sub_item.nombre + " / " + sub_sub_item.nombre,
                                    simple: sub_sub_item.nombre,
                                    id: sub_sub_item.id
                                });
                            });
                        });
                    });

                };

                $scope.setGlobalcat = function () {

                    if ($scope.categoriaSelected !== undefined)
                    {
                        $scope.catagoriaId = parseInt($scope.categoriaSelected.id);
                    }


                };

                $scope.esValidoTitulo = function () {
                    $scope.valtitulo = ($scope.titulo !== undefined) && ($scope.titulo !== '') ? "has-success" : "has-error";
                };

                $scope.esValidoCat = function () {
                    $scope.valcat = ($scope.categoriaSelected !== undefined) && ($scope.categoriaSelected.id !== undefined) ? "has-success" : "has-error";
                };

                $scope.esValidoPrecio = function () {
                    $scope.valprecio = ($scope.precio !== undefined) && ($scope.precio > 0) ? "has-success" : "has-error";
                };

                $scope.esValidoDes = function () {
                    $scope.valdes = ($scope.descripcion !== undefined) && ($scope.descripcion !== '') ? "has-success" : "has-error";
                };

                var esValido = function () {

                    $scope.valtitulo = ($scope.titulo !== undefined) && ($scope.titulo !== '') ? "has-success" : "has-error";
                    $scope.valcat = ($scope.categoriaSelected !== undefined) && ($scope.categoriaSelected.id !== undefined) ? "has-success" : "has-error";
                    $scope.valprecio = ($scope.precio !== undefined) && ($scope.precio > 0) ? "has-success" : "has-error";
                    $scope.valdes = ($scope.descripcion !== undefined) && ($scope.descripcion !== '') ? "has-success" : "has-error";
                    $scope.valimg = ($scope.imgs.length === 0) ? true : false;
                    $scope.valubi = ($rootScope.ubicaciones === undefined) ? true : false;

                    if ($rootScope.ubicaciones !== undefined)
                    {

                        $scope.valubi = $rootScope.ubicaciones.length === 0 ? true : false;
                    }


                    $scope.valglobal = !(($scope.titulo !== undefined) && ($scope.titulo !== '')
                            && ($scope.categoriaSelected !== undefined) && ($scope.categoriaSelected.id !== undefined)
                            && ($scope.precio !== undefined) && ($scope.precio > 0)
                            && ($scope.descripcion !== undefined) && ($scope.descripcion !== '')
                            && !$scope.valimg
                            && !$scope.valubi);

                    return ($scope.titulo !== undefined) && ($scope.titulo !== '')
                            && ($scope.categoriaSelected !== undefined) && ($scope.categoriaSelected.id !== undefined)
                            && ($scope.precio !== undefined) && ($scope.precio > 0)
                            && ($scope.descripcion !== undefined) && ($scope.descripcion !== '')
                            && !$scope.valimg
                            && !$scope.valubi;

                };

                $scope.valtitulo = "";
                $scope.valcat = "";
                $scope.valprecio = "";
                $scope.valdes = "";
                $scope.valimg = false;
                $scope.valubi = false;
                $scope.valglobal = false;
                $scope.baseImgUrl = "http://res.cloudinary.com/dm8oxzlpb/image/upload/";
                $scope.thumbSize = "h_60,w_90/";
                $scope.imgs = [];
                
                $scope.cloudinary = function() {                    
                    cloudinary.openUploadWidget({
                        cloud_name: 'dm8oxzlpb',
                        upload_preset: 'my0sfy04',
                        max_image_width: '1024',
                        max_image_height: '1024',
                        show_powered_by: false,
                        max_files: 7,       
                        stylesheet: 'white',
                        max_file_size: '500000',
//                        max_chunk_size: '9000',
                        text: {
                            "powered_by_cloudinary": "comprameya.com",
                            "sources.local.title": "Mis Imágenes",
                            "sources.local.drop_file": "Arrastre una imágen aquí",
                            "sources.local.drop_files": "Arrastre imágenes aquí",
                            "sources.local.drop_or": "O",
                            "sources.local.select_file": "Seleccione Imágen",
                            "sources.local.select_files": "Seleccione Imágenes",
                            "sources.url.title": "Link",
                            "sources.url.note": "URL de una imágen pública:",
                            "sources.url.upload": "Subir",
                            "sources.url.error": "Por favor, digite una URL válida",
                            "sources.camera.title": "Cámara",
                            "sources.camera.note": "Podrá tomar una foto si su navegador tiene acceso a la cámara:",
                            "sources.camera.capture": "Capturar",
                            "progress.uploading": "Subiendo...",
                            "progress.upload_cropped": "Subir",
                            "progress.processing": "Procesando...",
                            "progress.retry_upload": "Intente de nuevo",
                            "progress.use_succeeded": "OK",
                            "progress.failed_note": "Algunas imágenes no pudieron cargarse."
                        }
                    }, function (error, result) {
                        if(!error) {
                            $scope.$apply(function () {
                                result.forEach(function (entry) {
                                    $scope.imgs.push(entry.path);
                                });
                            });                            
                        }else {
//                            alert(error);
                        }
                    });
                };

            }]);

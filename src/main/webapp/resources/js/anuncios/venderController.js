modulo_anuncios.controller('venderController',
        ['$rootScope',
            '$scope',
            'anunciosService',
            'catService',
            'flowFactory',
            'Publicacion',
            function ($rootScope, $scope, anunciosService, catService, flowFactory, Publicacion) {

                $scope.obj = {};

                $scope.$on('flow::fileAdded', function (event, $flow, flowFile) {
                    console.log('This works');
//                    var that = flowFile;
//                    if (!{png: 1, gif: 1, jpg: 1, jpeg: 1}[that.getExtension()])
//                        return false;
//                    if (!(that.file.hasOwnProperty('alreadyResized') && that.file.alreadyResized)) {
//                        loadImage(that.file,
//                                function (canvas) {
//                                    canvas.toBlob(
//                                            function (blob) {
//                                                blob.alreadyResized = true;
//                                                for (var key in that.file)
//                                                    blob[key] = that.file[key];
//                                                that.flowObj.addFile(blob);
//                                            }
//                                    )
//                                },
//                                {
//                                    canvas: true,
//                                    crop: true,
//                                    maxWidth: 300,
//                                    maxHeight: 300
//                                }
//                        );
//                        return false;
//                    } else
//                    {
//                        return true;
//                    }

                });

                var crearPublicacion = function () {
                    //Se recogen los datos de la publicacion 
                    var imagenes = new Array();
                    for (var index = 0; index < $scope.existingFlowObject.files.length; ++index) {
                        imagenes[index] = $scope.existingFlowObject.files[index].file;
                    }

                    var publicacion = new Publicacion('', $scope.titulo, $scope.precio, $scope.descripcion, $scope.catagoriaId, 'GRATIS');
                    publicacion.setImagenes(imagenes);
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
                    $scope.valimg = ($scope.existingFlowObject.files.length === 0) ? true : false;
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

            }]);

module.exports = function (grunt) {
    //grunt wrapper function 
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        //grunt task configuration will go here     
        ngAnnotate: {
//            options: {
//                remove: true,
//                add: true
//            },
            options: {
                singleQuotes: true
            },
            app: {
//                files: {
//                    expand: true,
//                    sourceMap: true,
//                    src: './js/**/*.js',
//                    ext: '.annotated.js', // Dest filepaths will have this extension.
//                    extDot: 'last',
//                    dest: './js-min-safe'
//                }
                files: {
                    './js-min-safe/bower/blueimp-load-image/js/load-image.js': ['./bower_components/blueimp-load-image/js/load-image.js'],
                    './js-min-safe/bower/angular/angular.min.js': ['./bower_components/angular/angular.min.js'],
                    './js-min-safe/bower/angular-sanitize/angular-sanitize.min.js': ['./bower_components/angular-sanitize/angular-sanitize.min.js'],
                    './js-min-safe/bower/angular-loading-bar/build/loading-bar.min.js': ['./bower_components/angular-loading-bar/build/loading-bar.min.js'],
                    './js-min-safe/bower/angular-animate/angular-animate.min.js': ['./bower_components/angular-animate/angular-animate.min.js'],
                    './js-min-safe/bower/angular-bootstrap/ui-bootstrap-tpls.min.js': ['./bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js'],
                    './js-min-safe/bower/lodash/dist/lodash.min.js': ['./bower_components/lodash/dist/lodash.min.js'],
                    './js-min-safe/bower/underscore/underscore-min.js': ['./bower_components/underscore/underscore-min.js'],
                    './js-min-safe/bower/angular-simple-logger/dist/angular-simple-logger.min.js': ['./bower_components/angular-simple-logger/dist/angular-simple-logger.min.js'],
                    './js-min-safe/bower/angular-google-maps/dist/angular-google-maps.min.js': ['./bower_components/angular-google-maps/dist/angular-google-maps.min.js'],
                    './js-min-safe/bower/angular-ui-router/release/angular-ui-router.min.js': ['./bower_components/angular-ui-router/release/angular-ui-router.min.js'],
                    './js-min-safe/bower/ng-flow/dist/ng-flow-standalone.min.js': ['./bower_components/ng-flow/dist/ng-flow-standalone.min.js'],
                    './js-min-safe/bower/angular-swipe/dist/angular-swipe.min.js': ['./bower_components/angular-swipe/dist/angular-swipe.min.js'],
                    './js-min-safe/bower/matchmedia/matchMedia.js': ['./bower_components/matchmedia/matchMedia.js'],
                    './js-min-safe/bower/matchmedia-ng/matchmedia-ng.js': ['./bower_components/matchmedia-ng/matchmedia-ng.js'],
                    './js-min-safe/bower/angular-confirm-modal/angular-confirm.min.js': ['./bower_components/angular-confirm-modal/angular-confirm.min.js'],
                    './js-min-safe/bower/sockjs/sockjs.min.js': ['./bower_components/sockjs/sockjs.min.js'],
                    './js-min-safe/bower/stomp-websocket/lib/stomp.min.js': ['./bower_components/stomp-websocket/lib/stomp.min.js'],
                    './js-min-safe/bower/angular-toastr/dist/angular-toastr.tpls.min.js': ['./bower_components/angular-toastr/dist/angular-toastr.tpls.min.js'],
                    './js-min-safe/autenticacion/autenticacion.module.js': ['./js/autenticacion/autenticacion.module.js'],
                    './js-min-safe/autenticacion/autenticacionController.js': ['./js/autenticacion/autenticacionController.js'],
                    './js-min-safe/autenticacion/changePassController.js': ['./js/autenticacion/resetPassController.js'],
                    './js-min-safe/autenticacion/resetPassController.js': ['./js/autenticacion/changePassController.js'],
                    './js-min-safe/autenticacion/updateCellController.js': ['./js/autenticacion/updateCellController.js'],
                    './js-min-safe/autenticacion/autenticacionService.js': ['./js/autenticacion/autenticacionService.js'],
                    './js-min-safe/autenticacion/ProfileService.js': ['./js/autenticacion/ProfileService.js'],
                    './js-min-safe/autenticacion/ProfileController.js': ['./js/autenticacion/ProfileController.js'],                    
                    './js-min-safe/notificacion/notificacion.module.js': ['./js/notificacion/notificacion.module.js'],
                    './js-min-safe/notificacion/notificacionService.js': ['./js/notificacion/notificacionService.js'],
                    './js-min-safe/notificacion/PushNotificationService.js': ['./js/notificacion/PushNotificationService.js'],
                    './js-min-safe/notificacion/notificacionController.js': ['./js/notificacion/notificacionController.js'],
                    './js-min-safe/categorias/categorias.module.js': ['./js/categorias/categorias.module.js'],
                    './js-min-safe/categorias/catService.js': ['./js/categorias/catService.js'],
                    './js-min-safe/categorias/catController.js': ['./js/categorias/catController.js'],
                    './js-min-safe/menuPrincipal/menuPrincipal.module.js': ['./js/menuPrincipal/menuPrincipal.module.js'],
                    './js-min-safe/menuPrincipal/busquedaService.js': ['./js/menuPrincipal/busquedaService.js'],
                    './js-min-safe/menuPrincipal/menuPrincipalController.js': ['./js/menuPrincipal/menuPrincipalController.js'],
                    './js-min-safe/anuncios/anuncios.module.js': ['./js/anuncios/anuncios.module.js'],
                    './js-min-safe/anuncios/anunciosService.js': ['./js/anuncios/anunciosService.js'],
                    './js-min-safe/anuncios/comentariosService.js': ['./js/anuncios/comentariosService.js'],
                    './js-min-safe/anuncios/recursoService.js': ['./js/anuncios/recursoService.js'],
                    './js-min-safe/anuncios/tiendaService.js': ['./js/anuncios/tiendaService.js'],
                    './js-min-safe/anuncios/anunciosController.js': ['./js/anuncios/anunciosController.js'],
                    './js-min-safe/anuncios/busquedaController.js': ['./js/anuncios/busquedaController.js'],
                    './js-min-safe/anuncios/empresasController.js': ['./js/anuncios/empresasController.js'],
                    './js-min-safe/anuncios/misPublicacionesController.js': ['./js/anuncios/misPublicacionesController.js'],
                    './js-min-safe/anuncios/productoController.js': ['./js/anuncios/productoController.js'],
                    './js-min-safe/anuncios/publicacionesFiltradasController.js': ['./js/anuncios/publicacionesFiltradasController.js'],
                    './js-min-safe/anuncios/ubicacionesController.js': ['./js/anuncios/ubicacionesController.js'],
                    './js-min-safe/sorteo/sorteo.module.js': ['./js/sorteo/sorteo.module.js'],
                    './js-min-safe/sorteo/sorteoController.js': ['./js/sorteo/sorteoController.js'],
                    ///'./js-min-safe/sorteo/confeti.js': ['./js/sorteo/confeti.js'],
                    './js-min-safe/anuncios/venderController.js': ['./js/anuncios/venderController.js'],
                    './js-min-safe/maps/maps.module.js': ['./js/maps/maps.module.js'],
                    './js-min-safe/maps/mapService.js': ['./js/maps/mapService.js'],
                    './js-min-safe/maps/mapControllers.js': ['./js/maps/mapControllers.js'],
                    './js-min-safe/utils/utils.module.js': ['./js/utils/utils.module.js'],
                    './js-min-safe/utils/loadingDirective.js': ['./js/utils/loadingDirective.js'],
                    './js-min-safe/utils/modalController.js': ['./js/utils/modalController.js'],
                    './js-min-safe/utils/utilsService.js': ['./js/utils/utilsService.js'],
                    './js-min-safe/mensajes/mensajes.module.js': ['./js/mensajes/mensajes.module.js'],
                    './js-min-safe/mensajes/mensajesService.js': ['./js/mensajes/mensajesService.js'],
                    './js-min-safe/mensajes/mensajesController.js': ['./js/mensajes/mensajesController.js'],
                    './js-min-safe/cmpy/cmpy.module.js': ['./js/cmpy/cmpy.module.js']
                }
            }
        },
        concat: {
            options: {
                separator: ';\n'
            },
            js: {//target
                src: ['./js-min-safe/app.js',
                    './js-min-safe/bower/angular/*.js',
                    './js-min-safe/bower/**/*.js',
                    './js-min-safe/**/*.module.js',
                    './js-min-safe/**/*.js'],
                dest: './min/app.js'
            }
        },
        uglify: {
            js: {//target
                src: ['./min/app.js'],
                dest: './min/app.min.js'
            }
        },
        cssmin: {
            options: {
                shorthandCompacting: false,
                roundingPrecision: -1,
                keepSpecialComments: 0
            },
            dist: {
                files: {
                    './min/css/style.min.css':
                            [
                                './bower_components/bootstrap-css/css/bootstrap.min.css',
                                './bower_components/angular-carousel-3d/dist/carousel-3d.min.css',
                                './bower_components/angular-loading-bar/build/loading-bar.min.css',
                                './bower_components/angular-toastr/dist/angular-toastr.min.css',
                                './css/index/*.css'                                
                            ]
                }
            }
        }
    });

    //load grunt tasks
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-ng-annotate');
    grunt.loadNpmTasks('grunt-contrib-cssmin');

    //register grunt default task
    grunt.registerTask('default', ['ngAnnotate', 'concat', 'uglify', 'cssmin']);


};
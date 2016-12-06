/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cmy_ng = angular.module('cmpy', 
[
    'angular-loading-bar',
    'ui.router', 
    'ui.bootstrap',
    'ngAnimate',
    'toastr',
    'flow',
    'uiGmapgoogle-maps',
    'angular-confirm',
    'cmpy.autenticacion',
    'cmpy.notificacion',
    'cmpy.menuPrincipal',    
    'cmpy.categorias',
    'cmpy.maps',
    'cmpy.anuncios',
    'cmpy.mensajes',
    'cmpy.utils'
]);

cmy_ng.config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, toastrConfig) {

//    $locationProvider.html5Mode({
//        enabled: true,
//        requireBase: false,
//        rewriteLinks: false
//    });

    angular.extend(toastrConfig, {
        autoDismiss: true,
        containerId: 'toast-container',
        maxOpened: 3,    
        newestOnTop: true,
        positionClass: 'toast-bottom-right',
        preventDuplicates: false,
        preventOpenDuplicates: false,
        target: 'body',
        closeButton: true,
        progressBar: true,
        tapToDismiss: true
      });
    
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    
    $urlRouterProvider.otherwise('/');

    $stateProvider

            // Facebook redirect url
            .state('fb', {
                url: "/",
                views: {
                    '': { templateUrl: '/resources/js/cmpy/home.html' },                    
//                    "menuPrincipal@fb": {templateUrl: '/resources/js/menuPrincipal/menuPrincipal.html', controller: 'menuPrincipalController'},
                    "menuCategorias@fb": {templateUrl: '/resources/js/categorias/menuCategorias.html', controller: 'catController'},
                    "panelPublicacionesProductos@fb": {templateUrl: '/resources/js/anuncios/panelProductos.html', controller: 'anunciosController'},
                    "panelPublicacionesEmpresas@fb": {templateUrl: '/resources/js/anuncios/panelEmpresas.html', controller: 'empresasController'},                    
                    "venderModal@fb": {templateUrl: '/resources/js/anuncios/venderModal.html'},
                    "ubicacionesModal@fb": {templateUrl: '/resources/js/anuncios/ubicacionesModal.html'},
                    "maps": {templateUrl: '/resources/js/maps/mapaUbicacion.html', controller: 'mapController'}
                }
            })

            // HOME STATES AND NESTED VIEWS ========================================
            .state('home', {
                url: "",
                views: {
                    '': { templateUrl: '/resources/js/cmpy/home.html' },
                    
//                    "menuPrincipal@home": {templateUrl: '/resources/js/menuPrincipal/menuPrincipal.html', controller: 'menuPrincipalController'},
                    "menuCategorias@home": {templateUrl: '/resources/js/categorias/menuCategorias.html', controller: 'catController'},
                    "panelPublicacionesProductos@home": {templateUrl: '/resources/js/anuncios/panelProductos.html', controller: 'anunciosController'},
                    "panelPublicacionesEmpresas@home": {templateUrl: '/resources/js/anuncios/panelEmpresas.html', controller: 'empresasController'},                    
                    "venderModal@home": {templateUrl: '/resources/js/anuncios/venderModal.html'},
                    "ubicacionesModal@home": {templateUrl: '/resources/js/anuncios/ubicacionesModal.html'},
                    "maps": {templateUrl: '/resources/js/maps/mapaUbicacion.html', controller: 'mapController'}
                }
            })

            .state('home.misPublicaciones', {
                url: "/misPublicaciones",  
                views: {
                    '':{templateUrl: '/resources/js/anuncios/panelProductos.html', controller: 'misPublicacionesController'}
                }
            })
    
            .state('home.porCategoria', {
                url: "/categoria/{cat}/{nivel}",
                views: {
                    '':{templateUrl: '/resources/js/anuncios/panelProductos.html', controller: 'publicacionesFiltradasController'}
                }
            })
            
            .state('home.busqueda', {
                url: "/busqueda/{terminoBusqueda}",
                views: {
                    '':{templateUrl: '/resources/js/anuncios/panelProductos.html', controller: 'busquedaController'}
                }
            })
    
            .state('home.vistaProducto', {
                url: "/vistaProducto/{publicacionId}",  
                views: {
                    '':{templateUrl: '/resources/js/anuncios/vistaProducto.html', controller: "productoController"}
                }
            })
            
            .state('home.misPublicaciones.vistaProducto', {
                url: "/vistaProducto/{publicacionId}",  
                views: {
                    '':{templateUrl: '/resources/js/anuncios/vistaProducto.html', controller: "productoController"}
                }
            })
            
            
            .state('home.vistaMensaje', {
                url: "/vistaMensaje", 
                params: {
                    usuarioId: 0,
                    usuarioNombre: '',
                    asunto: '~'
                },
                views: {
                    '':{templateUrl: '/resources/js/mensajes/bandeja.html', controller : "mensajesController"}
                }
            })
            
            .state('home.vistaNotificaciones', {
                url: "/vistaNotificaciones",
                views: {
                    '':{templateUrl: '/resources/js/notificacion/bandeja.html', controller: "notificacionController"}
                }
            })

            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', { 
                // we'll get to this in a bit       
            });                        

});

cmy_ng.run(['$rootScope', function($rootScope){
        
  $rootScope.publicaciones = new Array();      
        
  // Previous code ...
  $rootScope.ready = false;
  
  
  
  $rootScope.$on("cfpLoadingBar:loading", function(){
      $rootScope.ready = false;
  });

  $rootScope.$on("cfpLoadingBar:loaded", function(){
      $rootScope.ready = true;
  });



}]);

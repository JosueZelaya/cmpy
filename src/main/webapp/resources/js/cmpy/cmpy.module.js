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

cmy_ng.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {

//    $locationProvider.html5Mode({
//        enabled: true,
//        requireBase: false,
//        rewriteLinks: false
//    });
    
    $urlRouterProvider.otherwise('/');

    $stateProvider

            // Facebook redirect url
            .state('fb', {
                url: "/",
                views: {
                    '': { templateUrl: '/resources/js/cmpy/home.html' },
                    
                    "menuPrincipal@fb": {templateUrl: '/resources/js/menuPrincipal/menuPrincipal.html'},
                    "menuCategorias@fb": {templateUrl: '/resources/js/categorias/menuCategorias.html'},
                    "panelPublicacionesProductos@fb": {templateUrl: '/resources/js/anuncios/panelProductos.html'},
                    "panelPublicacionesEmpresas@fb": {templateUrl: '/resources/js/anuncios/panelEmpresas.html'},                    
                    "venderModal@fb": {templateUrl: '/resources/js/anuncios/venderModal.html'},
                    "ubicacionesModal@fb": {templateUrl: '/resources/js/anuncios/ubicacionesModal.html'},
                    "maps": {templateUrl: '/resources/js/maps/mapaUbicacion.html'}
                }
            })

            // HOME STATES AND NESTED VIEWS ========================================
            .state('home', {
                url: "",
                views: {
                    '': { templateUrl: '/resources/js/cmpy/home.html' },
                    
                    "menuPrincipal@home": {templateUrl: '/resources/js/menuPrincipal/menuPrincipal.html'},
                    "menuCategorias@home": {templateUrl: '/resources/js/categorias/menuCategorias.html'},
                    "panelPublicacionesProductos@home": {templateUrl: '/resources/js/anuncios/panelProductos.html'},
                    "panelPublicacionesEmpresas@home": {templateUrl: '/resources/js/anuncios/panelEmpresas.html'},                    
                    "venderModal@home": {templateUrl: '/resources/js/anuncios/venderModal.html'},
                    "ubicacionesModal@home": {templateUrl: '/resources/js/anuncios/ubicacionesModal.html'},
                    "maps": {templateUrl: '/resources/js/maps/mapaUbicacion.html'}
                }
            })
            
            .state('home.vistaProducto', {
                url: "/vistaProducto/{publicacionId}",  
                views: {
                    '':{templateUrl: '/resources/js/anuncios/vistaProducto.html'}
                }
            })
            
            .state('fb.vistaProducto', {
                url: "/vistaProducto/{publicacionId}",  
                views: {
                    '':{templateUrl: '/resources/js/anuncios/vistaProducto.html'}
                }
            })
            .state('home.vistaMensaje', {
                url: "/vistaMensaje/{usuarioId}",  
                views: {
                    '':{templateUrl: '/resources/js/mensajes/bandeja.html'}
                }
            })
            
            .state('fb.vistaMensaje', {
                url: "/vistaMensaje/{usuarioId}",  
                views: {
                    '':{templateUrl: '/resources/js/mensajes/bandeja.html'}
                }
            })

            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', { 
                // we'll get to this in a bit       
            });

});

cmy_ng.run(['$rootScope', function($rootScope){
  // Previous code ...
  $rootScope.ready = false;
  
  
  
  $rootScope.$on("cfpLoadingBar:loading", function(){
      $rootScope.ready = false;
  });

  $rootScope.$on("cfpLoadingBar:loaded", function(){
      $rootScope.ready = true;
  });



}]);

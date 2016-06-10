/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cmy_ng = angular.module('cmpy', 
[
    'ui.router', 
    'ui.bootstrap',
    'ngAnimate',
    'flow',
    'uiGmapgoogle-maps',
    'cmpy.autenticacion',
    'cmpy.menuPrincipal',    
    'cmpy.categorias',
    'cmpy.maps',
    'cmpy.anuncios',
    'cmpy.utils'
]);

cmy_ng.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider

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
                    "maps": {templateUrl: '/resources/js/maps/mapaUbicacion.html'}
                }
            })
            
            .state('home.vistaProducto', {
 //               url: "/vistaProducto/{publicacionId}",  
 //               templateUrl: '/resources/js/anuncios/vistaProducto.html'//,
                url: "/vistaProducto/{publicacionId}",  
                views: {
                    '':{templateUrl: '/resources/js/anuncios/vistaProducto.html'},
                    "maps@home.vistaProducto": {templateUrl: '/resources/js/maps/mapaUbicacion.html'}
                }
                //views:{"maps": {templateUrl: '/resources/js/maps/mapaUbicacion.html'}}
                
//                controller: function($scope) {
//                    $scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
//                }
            })

            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });

});


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
            .state('index', {
                url: "",
                views: {
                    "menuPrincipal": {templateUrl: '/resources/js/menuPrincipal/menuPrincipal.html'},
                    "menuCategorias": {templateUrl: '/resources/js/categorias/menuCategorias.html'},
                    "panelPublicacionesProductos": {templateUrl: '/resources/js/anuncios/panelProductos.html'},
                    "panelPublicacionesEmpresas": {templateUrl: '/resources/js/anuncios/panelEmpresas.html'},                    
                    "venderModal": {templateUrl: '/resources/js/anuncios/venderModal.html'}
                }
            })
            
            .state('vistaProducto', {
                url: "/vistaProducto",     
                templateUrl: '/resources/js/anuncios/vistaProducto.html',
                views: {
                    "menuPrincipal": {templateUrl: '/resources/js/menuPrincipal/menuPrincipal.html'},
                    "menuCategorias": {templateUrl: '/resources/js/categorias/menuCategorias.html'},     
                    "panelPublicacionesProductos": {templateUrl: '/resources/js/anuncios/detalleProducto.html'},
                    "panelPublicacionesEmpresas": {templateUrl: '/resources/js/anuncios/panelEmpresas.html'}                    
                }
            })

            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });

});


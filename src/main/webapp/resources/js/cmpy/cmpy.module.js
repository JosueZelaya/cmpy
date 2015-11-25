/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cmy_ng = angular.module('cmpy', 
[
    'ui.router', 
    'ui.bootstrap', 
    'cmpy.autenticacion',
    'cmpy.menuPrincipal',    
    'cmpy.categorias',
    'cmpy.maps',
    'cmpy.productos',    
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
                    "panelPublicacionesProductos": {templateUrl: '/resources/js/productos/panelProductos.html'}
//                    "productos": {templateUrl: '/resources/js/productos/productos.html'}
                }
            })

            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });

});


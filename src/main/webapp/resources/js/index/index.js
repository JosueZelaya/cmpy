/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cmy_ng = angular.module('appComprameYa', ['ui.router', 'ui.bootstrap']);

cmy_ng.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider

            // HOME STATES AND NESTED VIEWS ========================================
            .state('index', {
                url: "",
                views: {
                    "menuPrincipal": {templateUrl: '/resources/js/views/menuPrincipal.html', controller: 'menuPrincipalController'},
                    "menuCategorias": {templateUrl: '/resources/js/views/menuCategorias.html'},
                    "panelPublicacionesProductos": {templateUrl: '/resources/js/views/panelPublicacionesProductos.html'},
                    "productos": {templateUrl: '/resources/js/views/productos.html',controller: 'publicacionController'}
                }
            })

            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });

});


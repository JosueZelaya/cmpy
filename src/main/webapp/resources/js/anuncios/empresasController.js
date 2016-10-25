/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

modulo_anuncios.controller('empresasController', function ($rootScope, $scope, $stateParams, anunciosService,mapService, TIPO_PUBLICACION, flowFactory, Publicacion) {
    
    var getPublicaciones = function(tipo,pagina) {
        return anunciosService.getAnuncios(tipo,pagina)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };
    
    var cargarPublicacionesPagadas = function(){
        getPublicaciones(TIPO_PUBLICACION.PAGADA, '0')
                .success(function(publicaciones){
            $rootScope.anuncios = publicaciones;
        });
    };
    
    var init = function () {       
        
        cargarPublicacionesPagadas();
        
    };
    
    init();

});

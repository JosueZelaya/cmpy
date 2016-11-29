/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

modulo_anuncios.controller('empresasController', function ($scope, tiendaService) {
    
    var getTiendasAleatorias = function() {
        return tiendaService.getTiendasAleatorias()
                    .success(function (tiendas) {                        
                            return tiendas;                            
                        });
    };
    
    var init = function () {       
        
        getTiendasAleatorias();
        
    };
    
    init();

});

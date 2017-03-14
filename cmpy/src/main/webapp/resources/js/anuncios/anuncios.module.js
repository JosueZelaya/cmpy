var modulo_anuncios = angular.module('cmpy.anuncios', ['flow']);

//modulo_anuncios.config(function ($httpProvider){
//   $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
//});

modulo_anuncios.constant('TIPO_PUBLICACION',{
    PAGADA : "1",
    GRATIS : "2"    
});


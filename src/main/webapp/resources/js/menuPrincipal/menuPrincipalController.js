menuPrincipal.controller('menuPrincipalController',['$scope','$rootScope','anunciosService','notificacionService','TIPO_PUBLICACION' , function($scope,$rootScope,anunciosService,notificacionService,TIPO_PUBLICACION) {
    

    $scope.match = "";

    var getPublicacionesByMatch = function(tipo,pagina,match) {
        return anunciosService.getAnunciosByMatch(tipo,pagina,match)
                    .success(function (publicaciones) {                        
                            return publicaciones;                            
                        });
    };
    
    var getTotalNotificaciones = function(){
        return notificacionService.getTotalNotificaciones()
                .success(function(total){
                    return total;
                });
    };
    
    var getNotificaciones = function(){
        return notificacionService.getNotificaciones()
                .success(function(notificaciones){
                    return notificaciones;
                });
    };
    
    $scope.cargarPublicacionesGratisByMatch = function(page,match){
        
        getPublicacionesByMatch(TIPO_PUBLICACION.GRATIS, page,match)
                .success(function(publicaciones){
            $rootScope.publicaciones = publicaciones;
        });
    };
    
    var init = function () {
        
//        if($rootScope.authenticated){
            getTotalNotificaciones()
                    .success(function(total){
                        $scope.totalNotificaciones = total;
                    });
                    
            getNotificaciones()
                    .success(function(notificaciones){
                        $scope.notificaciones = notificaciones;
                    });
//        }
            
        
    };

    init();


}]);


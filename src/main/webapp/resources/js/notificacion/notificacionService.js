modulo_notificacion.service('notificacionService', function ($http, $log) {

   this.getTotalNotificaciones = function(){
        var req = "/notificacion/getTotalNotificaciones/";
        return $http.get(req)
                .success(function(response){
                   return response; 
                });
    };
    
    this.getNotificaciones = function () {
        var req = "/notificacion/getNotificaciones/";
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    };
    
    this.getTodasNotificaciones = function () {
        var req = "/notificacion/getTodasNotificaciones/";
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    };
    
    this.quitarVisibilidad = function (notificacionId) {
        var req = "/notificacion/quitarVisibilidad/"+notificacionId;
        return $http.get(req)
                .success(function (response)
                {
                    return response;
                });
    };
    
    this.solicitarNotificacionesPush = function () {
        var req = "/notificacion/solicitarNotificacionesPush/";
        $http.get(req);
    };
    
    this.ocultar = function (notificacionId) {
        var req = "/notificacion/ocultar/"+notificacionId;
        $http.get(req);
    };

});
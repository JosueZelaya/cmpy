menuPrincipal.controller('menuPrincipalController', ['$scope', '$rootScope', 'anunciosService', 'notificacionService', 'TIPO_PUBLICACION','mensajesService','PushNotificationService','$log', function ($scope, $rootScope, anunciosService, notificacionService, TIPO_PUBLICACION,mensajesService, PushNotificationService, $log) {

        $scope.match = "";
        $rootScope.notificaciones = {}
        $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
        $scope.pageMensajesNoLeidos = 0;
        $scope.NoLeidosTotal = 0;
        $scope.mensajes = [];
        
//        $rootScope.$on('$routeChangeStart', function (event, next, current) {
//            if (!current) {
//              PushNotificationService.disconnect();
//            }
//          });

        PushNotificationService.receive().then(null, null, function (notificaciones) {
            $rootScope.totalNotificaciones = 
                    $rootScope.notificaciones.unshift(notificaciones);
        });

        var getNotificaciones = function () {
            return notificacionService.getNotificaciones()
                    .success(function (notificaciones) {
                        return notificaciones;
                    });
        };

        $scope.quitarVisibilidad = function (idNotificacion, index) {

            notificacionService.ocultar(idNotificacion);
            $rootScope.notificaciones.splice(index, 1);
            $rootScope.totalNotificaciones = $rootScope.notificaciones.length;
        };
        
        $scope.cargarMensajesNoLeidos = function(page){
            mensajesService.getMensajesNoLeidos(page)
            .success(function (response) {
                        $scope.mensajes = response;
                 $scope.getMensajeUsuarioNoleidoTotal();
             });
        };
        
        $scope.getMensajesNoLeidosTotal = function(){
            mensajesService.getMensajesNoLeidosTotal()
            .success(function (response) {
                        $scope.NoLeidosTotal = response;
             });           
            
            
        };
        
        $scope.getMensajeUsuarioNoleidoTotal =function(){
            angular.forEach($scope.mensajes, function (item) {
                
                mensajesService.getMensajeUsuarioNoleidoTotal(item.usuarioEmisor.id)
                .success(function (response) {
                            item.total = response;
                 });  
            });
            
        };
        
        
        $scope.setMensajeUsuarioLeido = function(usuarioId){
             mensajesService.setMensajeUsuarioLeido(usuarioId)
            .success(function (response) {
                    init();
                     return response;
             });           
        };
        

        init = function () {
            $scope.navCollapsed = true;
            $scope.collapseNot = true;
            $scope.totalMensajes = 0;

            if ($rootScope.authenticated) {
                getNotificaciones()
                        .success(function (notificaciones) {
                            $rootScope.notificaciones = notificaciones;
                            $rootScope.totalNotificaciones = notificaciones.length;
                        });

               $scope.cargarMensajesNoLeidos($scope.pageMensajesNoLeidos);
               $scope.getMensajesNoLeidosTotal();
            }


        };

        init();


    }]);


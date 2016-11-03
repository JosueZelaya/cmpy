menuPrincipal.service('busquedaService',['$http', function ($http) {
    
    this.getCategorias = function () {
        return $http.get("/categoria/todos")            
           .success(function (response)
            {
                return response;
            });
    };
    

}]);

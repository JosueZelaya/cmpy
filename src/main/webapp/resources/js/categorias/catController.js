categorias.controller('catController', function($scope,$rootScope,catService) {
    catService.getCategorias()
    .success(function (response) 
    {   
        $rootScope.collapse = false;
        $rootScope.btnshow= false;
        $scope.categorias = response;
        
        angular.forEach($scope.categorias, function (item) {
            item.collapse = true;
            angular.forEach(item.hijos, function (sub_item) {
                sub_item.collapse = true;
                angular.forEach(sub_item, function (sub_sub_item) {
                    sub_sub_item.collapse = true;
                });
            });
        });
        
    });
});

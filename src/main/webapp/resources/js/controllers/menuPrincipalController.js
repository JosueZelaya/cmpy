cmy_ng.controller('menuPrincipalController', function($scope, $http) {
    $http.get("/user")
    .success(function (response) 
    {   
        
        $scope.usuario = response;
        
//        angular.forEach($scope.categorias, function (item) {
//            item.collapse = true;
//            angular.forEach(item.hijos, function (sub_item) {
//                sub_item.collapse = true;
//                angular.forEach(sub_item, function (sub_sub_item) {
//                    sub_sub_item.collapse = true;
//                });
//            });
//        });
        
    });
});
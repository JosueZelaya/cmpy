
  

cmy_ng.controller('catTodoController', function($scope, $http) {
    $http.get("/categoria/todos")
    .success(function (response) 
    {   
        
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
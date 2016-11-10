utils.directive('loadingMsg', ['$http', function($http) {
    return {
      template: '<span ng-show="pending">Cargando...</span>',
      scope: {},
      controller: function($scope, $http) {
        $scope.$watch(function () {
            return $http.pendingRequests.length;
        }, function(newVal) {
            $scope.pending = newVal;
        });
      }
    };
}]);



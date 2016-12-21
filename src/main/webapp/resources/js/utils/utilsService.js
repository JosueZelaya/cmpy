utils.service('utilsService', function ($http, $log) {
    this.getTotalVisitas = function () {
            var req = "/visitas";
            return $http.get(req)
                    .success(function (response) {
                        return response;
                    });
        };
});
utils.controller('modalController',
        ['$rootScope',
            '$scope',
            '$uibModal',
            '$log',
            function ($rootScope, $scope, $uibModal, $log) {

                $scope.animationsEnabled = true;

                $rootScope.open = function (modalTemplate, size) {

                    var modalInstance = $uibModal.open({
                        animation: $scope.animationsEnabled,
                        templateUrl: modalTemplate,
                        controller: 'ModalInstanceCtrl',
                        size: size,
                        resolve: {
                            mensaje: function () {
                                return "voy por parametro";
                            }
                        }
                    });

                    modalInstance.result.then(function (resultado) {
                        $log.info(resultado);
                    }, function () {
                        $log.info('Modal dismissed at: ' + new Date());
                    });

                };

                $scope.toggleAnimation = function () {
                    $scope.animationsEnabled = !$scope.animationsEnabled;
                };

            }]);

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

utils.controller('ModalInstanceCtrl',
        ['$scope',
            '$uibModalInstance',
            function ($scope, $uibModalInstance) {

                $scope.ok = function () {
                    $uibModalInstance.close("Cerrando ventana modal");
                };

                $scope.cancel = function () {
                    $uibModalInstance.dismiss('cancel');
                };
            }]);

utils.controller('panelCollapseController',['$scope' ,'matchmedia', function($scope,matchmedia) {
        
    $scope.btnestado = true;
    $scope.btnstyle = "glyphicon glyphicon-chevron-down";
    $scope.isPhoneOrTablet = false;
    var unsub = {};


    $scope.setBtnStyle = function(){
        if($scope.isPhoneOrTablet){
            if($scope.btnestado)
            {
                $scope.btnstyle = "glyphicon glyphicon-chevron-up";
                $scope.btnestado = false;
            }
            else
            {
                $scope.btnstyle = "glyphicon glyphicon-chevron-down";
                $scope.btnestado = true;               
                
            }
        }
        
    };
    
    
    unsub['phone'] = matchmedia.onPhone(function(mediaQueryList){
                              $scope.isPhone = mediaQueryList.matches;
                              if($scope.isPhone){
                                   $scope.btnstyle = "glyphicon glyphicon-chevron-down";
                                   $scope.btnestado = true; 
                              }
                              if($scope.isPhone || $scope.isTablet)
                              {
                                  $scope.isPhoneOrTablet = true;
                              }
                              else
                              {
                                  $scope.isPhoneOrTablet = false;
                              }
                          });
    unsub['tablet'] = matchmedia.onTablet( function(mediaQueryList){
                              $scope.isTablet = mediaQueryList.matches;
                              if($scope.isTablet){
                                   $scope.btnstyle = "glyphicon glyphicon-chevron-down";
                                   $scope.btnestado = true; 
                              }
                              if($scope.isPhone || $scope.isTablet)
                              {
                                  $scope.isPhoneOrTablet = true;
                              }
                              else
                              {
                                  $scope.isPhoneOrTablet = false;
                              }
                          });
                          
    $scope.$on('$destroy', function () {
        // say goodbye to your listeners here

        unsub['phone']();
        unsub['tablet']();

    });    
     
}]);
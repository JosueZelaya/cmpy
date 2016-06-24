maps = angular.module('cmpy.maps',['uiGmapgoogle-maps'])

  .config(['uiGmapGoogleMapApiProvider', function (GoogleMapApi) {
    GoogleMapApi.configure({
  //  key: 'your api key',
  //    v: '3.20',
      libraries: 'places'
    });
  }]);
  
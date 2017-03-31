maps = angular.module('cmpy.maps',['uiGmapgoogle-maps'])

  .config(['uiGmapGoogleMapApiProvider', function (GoogleMapApi) {
    GoogleMapApi.configure({
      key: 'AIzaSyCUv4YsbHpTGv3j5PkAl2x4guWokqIuEEM',
      v: '3.25',
      libraries: 'places'
    });
  }]);
  
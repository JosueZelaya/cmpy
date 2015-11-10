<%-- 
    Document   : mapaUbicacion
    Created on : 01-oct-2015, 22:00:36
    Author     : genaro
--%>


<div ng-controller="mapController">

    <ui-gmap-google-map events="events" center="center" zoom="zoom" draggable="true" options="options" bounds="bounds">
        <ui-gmap-markers models="markers" events="marker.events" coords="'self'" icon="'icon'" options="marker.options">
        </ui-gmap-markers>
    </ui-gmap-google-map>

</div>



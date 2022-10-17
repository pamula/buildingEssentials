$(document).ready(function(){
    initialize();  
});



function initialize() {
  const fenway = { lat: 42.345573, lng: -71.098326 };
  const map = new google.maps.Map(document.getElementById("map"), {
    center: fenway,
    zoom: 14,
  });
  const panorama = new google.maps.StreetViewPanorama(
    document.getElementById("pano"),
    {
      position: fenway,
      pov: {
        heading: 34,
        pitch: 10,
      },
    }
  );

  map.setStreetView(panorama);
}

window.initialize = initialize;

//
//
//var curLat = null; //user location
//var curLon = null;
//
//function getLocation() {
//  if (navigator.geolocation) {
//    navigator.geolocation.getCurrentPosition(showPosition);
//  } else {
//        window.alert("no location");
//    }
//}
//function showPosition(position) {
//    curLat = position.coords.latitude;
//    curLon = position.coords.longitude;
//}
//function initMap(){
//  getLocation() //finds out user location to fomat the map
//  if (curLat == null || curLon == null){
//    curLat = 42.3601;   //if the user location cannot be found, set default ones
//    curLon = -71.0589;   // of boston
//    console.log("random locations");
//  }
//  var options = {
//    zoom:10,
//    center:{lat:curLat, lng:curLon}
//  }
//    var map = new google.maps.Map(document.getElementById("map"),options);
//}

<html>
    <head>
    </head>
    <body >
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title">Add your Address</h2>
                </div>
                <div class="panel-body">
                    <input  placeholder="Enter your address" id="autocomplete" onFocus="geolocate()" type="text" class="form-control">
                   </div>
            </div>
        </div>

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnGTbyaxe2OyVmvs6A430GSxQftAARsH0&libraries=places&callback=initAutocomplete&type=address" async defer></script>
        <script>
          var placeSearch, autocomplete;
          function initAutocomplete() {
              // Create the autocomplete object, restricting the search to geographical
              // location types.
              autocomplete = new google.maps.places.Autocomplete(
                      /** @type {!HTMLInputElement} */(document.getElementById('autocomplete')),
                      {types: ['establishment']});

              // When the user selects an address from the dropdown, populate the address
              // fields in the form.
              autocomplete.addListener('place_changed', fillInAddress);
               autocomplete.setOptions({strictBounds: false});
          }


          function geolocate() {
              if (navigator.geolocation) {
                  navigator.geolocation.getCurrentPosition(function (position) {
                      var geolocation = {
                          lat: position.coords.latitude,
                          lng: position.coords.longitude
                      };
                      var circle = new google.maps.Circle({
                          center: geolocation,
                          radius: position.coords.accuracy
                      });
                      autocomplete.setBounds(circle.getBounds());
                  });
              }
          }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <script src="auto-complete.js"></script>


    </body>
</html>
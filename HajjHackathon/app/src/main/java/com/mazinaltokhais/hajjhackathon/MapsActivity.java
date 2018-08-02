package com.mazinaltokhais.hajjhackathon;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.provider.UserDictionary.Words.APP_ID;

//import com.google.android.gms.location.places.Places;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    Intent intentThatCalled;
    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    String voice2text; //added
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLastKnownLocation;
    private static final String TAG ="Test" ;
    private GoogleMap mMap;

    private CameraPosition mCameraPosition;

    // The entry point to Google Play services, used by the Places API and Fused Location Provider.
    private GoogleApiClient mGoogleApiClient2;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(24.713600, 46.675300);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    // private Location mLastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mLastKnownLocation = new Location("service Provider");
       // googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(Loc
        mGoogleApiClient2 = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
               // .addApi(Places.GEO_DATA_API)
                //.addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient2.connect();

//        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
//                LOCATION_REFRESH_DISTANCE, mLocationListener);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        getDeviceLocation();
    }

    private void getDeviceLocation() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        if (mLocationPermissionGranted) {
//            mLastKnownLocation.set(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient2));
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                mLastKnownLocation.set(location);
                                GetLocation();
                            }
                        }
                    });

        }
    }

    public void GetLocation()
    {
//        if (LocationServices.FusedLocationApi.
//            getLastLocation(mGoogleApiClient2)!= null)
//        mLastKnownLocation=LocationServices.FusedLocationApi.
//                getLastLocation(mGoogleApiClient2);
//

        // Set the map's camera position to the current location of the device.
        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation!= null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            Log.d(TAG, "Current location is null. Using defaults.");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

        // List<Address> addresses = null;
        if (mLastKnownLocation!= null) {
            LatLng pos = new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude());

            addMarker(pos,false);
        }

        addDumpMarkers();


    }
    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        //updateLocationUI();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.main_map);
//        mapFragment.getMapAsync(this);

      //  public void onConnected(Bundle bundle) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient2);

                double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
                String units = "imperial";
                String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                        lat, lon, units, APP_ID);
                //new GetWeatherTask(textView).execute(url);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    private void addMarker(LatLng mLatLon, boolean dump){
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.recycle_bin);

        if (dump) {
            mMap.addMarker(new MarkerOptions()
                    .position(mLatLon)
                    .icon(icon));
        }
        else{
            mMap.addMarker(new MarkerOptions()
                    .position(mLatLon)
                    );
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient2 != null) {
            mGoogleApiClient2.connect();
        }
    }

    @Override
    protected void onStop() {
        mGoogleApiClient2.disconnect();
        super.onStop();
    }
//    protected void getLocation() {
//        if (isLocationEnabled(MainActivity.this)) {
//            locationManager = (LocationManager)  this.getSystemService(Context.LOCATION_SERVICE);
//            criteria = new Criteria();
//            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
//
//            //You can still do this if you like, you might get lucky:
//            Location location = locationManager.getLastKnownLocation(bestProvider);
//            if (location != null) {
//                Log.e("TAG", "GPS is on");
//                latitude = location.getLatitude();
//                longitude = location.getLongitude();
//                Toast.makeText(MainActivity.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
//                searchNearestPlace(voice2text);
//            }
//            else{
//                //This is what you need:
//                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
//            }
//        }
//        else
//        {
//            //prompt user to enable location....
//            //.................
//        }
//    }
public void addDumpMarkers()
{
    LatLng mLatLon= new LatLng(21.354884,39.9666063);
    LatLng mLatLon2= new LatLng(21.34069, 39.997352);
    LatLng mLatLon3= new LatLng(21.423456, 39.826040);
    LatLng mLatLon4= new LatLng(21.354884,39.9666063);
    LatLng mLatLon5= new LatLng(21.418426, 39.870935);
    addMarker(mLatLon,true);
    addMarker(mLatLon2,true);
    addMarker(mLatLon3,true);
    addMarker(mLatLon4,true);
    addMarker(mLatLon5,true);


}

}

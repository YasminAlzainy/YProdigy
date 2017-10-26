package com.example.karim.prodigyv1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.List;

import jp.shts.android.storiesprogressview.StoriesProgressView;
import timber.log.Timber;

public class map_home_layout extends AppCompatActivity {

    private MapView mapView;//creat isntacne from mapbox
    RelativeLayout relativeLayout_move_plane;//creat instance from relative layout
    ImageView imageView_circle_move;//object for the controller
    ImageView imageView_online_menu;//instance the refer to the online
    float x;//indicate for the x of controller
    float y;//indicate for the y of controller
    LocationManager locationManager; //to get the current GPS location
    StoriesProgressView storiesProgressView;
    ImageView imageView;
    int counter=0;
    int []resurese=new int[]{
            R.drawable.img,
            R.drawable.got1,
            R.drawable.got2
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pass the my mapbox key to retrive the map
        Mapbox.getInstance(this, "pk.eyJ1Ijoia2FyaW1lbHNoYXdlaXNoIiwiYSI6ImNqOHhlYnEycjF6dHoyeG1ybGJ4eHVrbnIifQ.vfigDA0NQO4Xx8CGX1akMQ");
        setContentView(R.layout.activity_map_home_layout);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

                @Override

                public void onLocationChanged(Location location) {
                    //get loatiuide
                    double latiuide = location.getLatitude();
                    //get longlatuide
                    double longttuide = location.getLongitude();
                    final LatLng latLng =new LatLng(latiuide,longttuide);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    List<Address>addressList= null;
                    try {
                        addressList = geocoder.getFromLocation(latiuide,longttuide,1);
                        String str=addressList.get(0).getCountryName();
                        str+=","+addressList.get(0).getLocality();
                        mapView.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(MapboxMap mapboxMap) {
                                mapboxMap.addMarker(new MarkerViewOptions()
                                        .position(latLng)

                                );
                                CameraPosition cameraPosition=new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(17)
                                        .bearing(180)
                                        .tilt(30)
                                        .build();
                                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),7000);
                        mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(@NonNull Marker marker) {
                                AlertDialog.Builder mBuilder=new AlertDialog.Builder(map_home_layout.this);
                                final View mView=getLayoutInflater().inflate(R.layout.dialog,null);
                                storiesProgressView=(StoriesProgressView) mView.findViewById(R.id.Stories2);
                                storiesProgressView.setStoriesCount(3);
                                storiesProgressView.setStoryDuration(50000L);
                                storiesProgressView.setStoriesListener(new StoriesProgressView.StoriesListener() {
                                    @Override
                                    public void onNext() {
                                        imageView.setImageResource(resurese[++counter]);
                                    }

                                    @Override
                                    public void onComplete() {
                                        Toast.makeText(map_home_layout.this, "Compelete", Toast.LENGTH_SHORT).show();
                                        storiesProgressView.destroy();
                                    }
                                });
                                storiesProgressView.startStories();
                                imageView=(ImageView)mView.findViewById(R.id.image2);

                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        storiesProgressView.skip();
                                    }
                                });
                                imageView.setImageResource(resurese[counter]);
                                mBuilder.setView(mView);
                                AlertDialog alertDialog=mBuilder.create();
                                alertDialog.show();
                                return false;
                            }
                        });
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        relativeLayout_move_plane=(RelativeLayout)findViewById(R.id.relativelayout_move_plane);
        imageView_circle_move=(ImageView)findViewById(R.id.move_circle);
        imageView_online_menu=(ImageView)findViewById(R.id.img_view_online_dialog);
        imageView_online_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder=new AlertDialog.Builder(map_home_layout.this);
                View mView=getLayoutInflater().inflate(R.layout.dialog_online,null);
                mbuilder.setView(mView);
                AlertDialog alertDialog=mbuilder.create();
                alertDialog.show();
            }
        });
        relativeLayout_move_plane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x=event.getX();
                y=event.getY();
                if(event.getAction()==MotionEvent.ACTION_MOVE)
                {
                        if(x<relativeLayout_move_plane.getRight()&&x>=relativeLayout_move_plane.getLeft()
                                &&
                                y<relativeLayout_move_plane.getBottom()&&y>=relativeLayout_move_plane.getTop()) {
                            imageView_circle_move.setX(x);
                            imageView_circle_move.setY(y);
                        }

                }
                return true;
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}

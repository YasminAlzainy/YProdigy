package com.example.karim.prodigyv1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

public class map_home_layout extends AppCompatActivity {

    private MapView mapView;
    RelativeLayout relativeLayout_move_plane;
    ImageView imageView_circle_move;
    ImageView imageView_online_menu;
    float x;
    float y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoia2FyaW1lbHNoYXdlaXNoIiwiYSI6ImNqOHhlYnEycjF6dHoyeG1ybGJ4eHVrbnIifQ.vfigDA0NQO4Xx8CGX1akMQ");
        setContentView(R.layout.activity_map_home_layout);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
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

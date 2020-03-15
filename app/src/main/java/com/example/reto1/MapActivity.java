package com.example.reto1;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Marker me;
    private TextView output;
    private FloatingActionButton fab;
    private ArrayList<Marker> markers;
    private Marker sel;
    private boolean selected;
    private double lat;
    private double lon;
    private double actualLat;
    private double actualLon;
    private Geocoder geo;
    private ArrayList<Double> dist;
    private int posM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        output = findViewById(R.id.output);
        fab = findViewById(R.id.fab);
        geo = new Geocoder(this);
        markers = new ArrayList<Marker>();
        dist = new ArrayList<Double>();

            fab.setOnClickListener(

                    (v) -> {

                        if(selected){

                            Intent i = new Intent(this, AddActivity.class);
                            i.putExtra("Latitud", lat);
                            i.putExtra("Longitud", lon);
                            startActivityForResult(i,30);

                        } else {

                            Toast.makeText(this, "Primero debe agregar un marcador al mapa", Toast.LENGTH_LONG).show();

                        }



                    }

            );
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500,2,this);

        LatLng pos = new LatLng(3, -76);




            me = mMap.addMarker( new MarkerOptions().position(pos).title("Me"));

        me.setIcon((BitmapDescriptorFactory.fromResource(R.drawable.human1)));



        mMap.setOnMapClickListener(point -> {

            if (point != null) {

                if(sel != null){

                    sel.remove();

                }

                sel = mMap.addMarker(new MarkerOptions().position(new LatLng(point.latitude, point.longitude)));
                selected = true;
                lat = point.latitude;
                lon = point.longitude;


            }

        });


    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        actualLat = location.getLatitude();
        actualLon = location.getLongitude();

        me.setPosition( pos );
        try {
            String adr = geo.getFromLocation(actualLat,actualLon,1).get(0).getAddressLine(0);
            me.setSnippet(adr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
        if(markers.size() != 0){

            cerca();

        }



    }

    /*
    public void cerca(){

        for(int i=0; i<markers.size(); i++){

            Circle circle = mMap.addCircle(new CircleOptions().center(new LatLng(markers.get(i).getLt(), markers.get(i).getLg())).radius(75).strokeColor(Color.argb(0,255,255,255)));
            float[] distance = new float[2];
            Location.distanceBetween(actualLat, actualLon,circle.getCenter().latitude,circle.getCenter().longitude,distance);
            posA = i;

            if(menor == 0){

                menor=distance[0];

            }

            if(distance[0]<circle.getRadius()){

                output.setText("Usted está en: " + markers.get(i).getName());
                menor=distance[0];


            } else {



               if(distance[0]<menor){

                    menor = distance[0];
                    posA =i;

                }

            }

        }

        output.setText("Usted está cerca de: " + markers.get(posA).getName());

    }
*/

    public void cerca(){

        if(markers.size() >=1) {

            for (int i = 0; i < markers.size(); i++) {
                double n = SphericalUtil.computeDistanceBetween(me.getPosition(), markers.get(i).getPosition());
                markers.get(i).setSnippet("Usted está a " + (int) n + " metros del lugar");
                dist.set(i, n);
            }

            double menor = 0;
            for (int i = 0; i < dist.size(); i++) {
                if (i == 0) {
                    menor = dist.get(i);
                    posM = i;
                }

                if (menor > dist.get(i)) {
                    menor = dist.get(i);
                    posM = i;
                }
            }
            //radio de 100 metros
            if (menor <= 100) {
                output.setText("Usted está en " + markers.get(posM).getTitle());
            } else {
                output.setText("Usted está cerca de " + markers.get(posM).getTitle());
            }
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 30 && resultCode == RESULT_OK) {

            if (data != null) {

                Serializable serializable = data.getExtras().getSerializable("Marker");
                com.example.reto1.Marker mark = (com.example.reto1.Marker) serializable;
                Marker m = mMap.addMarker(new MarkerOptions().position(sel.getPosition()).title(mark.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                double act = SphericalUtil.computeDistanceBetween(me.getPosition(), m.getPosition());
                m.setSnippet("Usted está a " + (int) act + " metros del lugar");
                sel.remove();
                sel = null;
                markers.add(m);
                dist.add(act);

                selected = false;

                /*
                for(int i=0; i<markers.size(); i++){


                    try {
                        adr = geo.getFromLocation(markers.get(i).getLt(),markers.get(i).getLg(),1).get(0).getAddressLine(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(markers.get(i).getLt(),markers.get(i).getLg())).title(markers.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).snippet(adr));

                }

            }

                 */

            }

            if (markers.size() != 0) {

                cerca();

            }

        }
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {  }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

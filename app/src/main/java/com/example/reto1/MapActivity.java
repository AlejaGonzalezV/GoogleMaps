package co.edu.appsmobile.locationexample;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Marker me;
    private TextView output;
    private Polygon icesi;
    private Polygon central;
    private Polygon auditorios;
    private Polygon biblioteca;
    private CheckBox centrall, auditorioss, biblio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        output = findViewById(R.id.output);
        centrall = findViewById(R.id.central);
        auditorioss = findViewById(R.id.auditorios);
        biblio = findViewById(R.id.Biblio);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500,2,this);

        LatLng pos = new LatLng(3, -76);



        me = mMap.addMarker( new MarkerOptions().position(pos).title("Me").snippet("Ma Location"));

        icesi = mMap.addPolygon(
                new PolygonOptions()
                        .add(new LatLng(3.339764,-76.531202))
                        .add(new LatLng(3.343036,-76.530810))
                        .add(new LatLng(3.343331,-76.527243))
                        .add(new LatLng(3.338682,-76.527114))
                        .fillColor( Color.argb(50,255,0,0))
                        .strokeColor(Color.argb(50,255,0,0))
        );

        central = mMap.addPolygon(
                new PolygonOptions()
                        .add(new LatLng(3.342265,-76.529705))
                        .add(new LatLng(3.341890,-76.529726))
                        .add(new LatLng(3.341938,-76.529528))
                        .add(new LatLng(3.342238,-76.529485))
                        .fillColor( Color.argb(50,0,255,0))
                        .strokeColor(Color.argb(50,0,255,0))

        );

        auditorios = mMap.addPolygon(
                new PolygonOptions()
                        .add(new LatLng(3.342667,-76.529480))
                        .add(new LatLng(3.342784,-76.529801))
                        .add(new LatLng(3.342538,-76.529957))
                        .add(new LatLng(3.342383,-76.529801))
                        .add(new LatLng(3.342420,-76.529533))
                        .fillColor( Color.argb(50,255,0,255))
                        .strokeColor(Color.argb(50,255,0,255))

        );

        biblioteca = mMap.addPolygon(
                new PolygonOptions()
                        .add(new LatLng(3.341665,-76.529807))
                        .add(new LatLng(3.341654,-76.530118))
                        .add(new LatLng(3.341970,-76.530107))
                        .add(new LatLng(3.341960,-76.529796))
                        .fillColor( Color.argb(50,0,0,255))
                        .strokeColor(Color.argb(50,0,0,255))

        );



    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        me.setPosition( pos );
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
        output.setText(("Accuracy: "+location.getAccuracy()));

        if(PolyUtil.containsLocation(pos, central.getPoints(), true)){
            centrall.setChecked(true);
            output.append("\nEstas en Icesi");
        }

        if(PolyUtil.containsLocation(pos, auditorios.getPoints(), true)){
            auditorioss.setChecked(true);
            output.append("\nEstas en Icesi");
        }

        if(PolyUtil.containsLocation(pos, biblioteca.getPoints(), true)){
            biblio.setChecked(true);
            output.append("\nEstas en Icesi");
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

package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.Marker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.MissingResourceException;

public class MarcadoresActivity extends AppCompatActivity {

    private ArrayList<Markers> marcadores;
    private ListView markersTable;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private Markers marker;
    private ListView markersList;
    private int pos;
    private String latlongM;
    private MarkerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcadores);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        markersList = findViewById(R.id.markersList);
        adapter = new MarkerAdapter();
        markersList.setAdapter(adapter);
        adapter.addMarker(new Markers("Universidad Icesi", 3.341571, -76.530198));
        Log.i("ESTO", adapter.getCount() + " ");



        markersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                marker = (Markers) markersList.getItemAtPosition(position);
                pos = position;


            }
        });

        fab2.setOnClickListener(

                (v) -> {

                   if(marker != null){

                       Intent i = new Intent();
                       i.putExtra("Marker", marker);
                       setResult(RESULT_OK,i);
                       finish();

                   }

                }
        );

        fab3.setOnClickListener(


                (v) -> {

                    if(marker!= null){

                        adapter.deleteMarker(marker);

                    }


                }

        );

    }




}

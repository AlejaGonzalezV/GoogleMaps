package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

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
        markersList = findViewById(R.id.markersList);
        adapter = new MarkerAdapter();
        markersList.setAdapter(adapter);
        adapter.addMarker(new Markers("Universidad Icesi", 3.341571, -76.530198));
        Log.i("ESTO", adapter.getCount() + " ");

        }


        public void llamar(Markers marker){

            Intent i = new Intent();
            i.putExtra("Marker", marker);
            setResult(RESULT_OK,i);
            finish();

        }
/*
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


*/

    }






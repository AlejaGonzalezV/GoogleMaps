package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private double lat;
    private double lon;
    private EditText nomText;
    private Button addBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nomText = findViewById(R.id.nomText);
        addBut = findViewById(R.id.addBut);
        lat = (Double) getIntent().getExtras().getSerializable("Latitud");
        lon = (Double) getIntent().getExtras().getSerializable("Longitud");

        addBut.setOnClickListener(

                (v) -> {

                    Marker mark = new Marker(nomText.getText().toString(),lat, lon);
                    Intent i = new Intent();
                    i.putExtra("Marker",mark);
                    setResult(RESULT_OK,i);
                    finish();


                }

        );



    }
}

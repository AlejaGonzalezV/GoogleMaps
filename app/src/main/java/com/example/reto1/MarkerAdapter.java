package com.example.reto1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MarkerAdapter extends BaseAdapter {

    private ArrayList<Markers> marcadores;

    public MarkerAdapter(){

        marcadores =  new ArrayList<Markers>();

    }

    @Override
    public int getCount() {
        return marcadores.size();
    }

    @Override
    public Object getItem(int position) {
        return marcadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void deleteMarker(Markers marker){

        marcadores.remove(marker);
        notifyDataSetChanged();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.marker_row, null, false);
        TextView nameField = row.findViewById(R.id.nameField);
        Button buttonUbi = row.findViewById(R.id.buttonUbi);
        Button buttonDel = row.findViewById(R.id.buttonDel);

        buttonDel.setOnClickListener(

                (v) -> {

                    Log.i("AAAAAAA", marcadores.size() + "");
                   deleteMarker(marcadores.get(position));
                    Log.i("AAAAAAA DESPUES", marcadores.size() + "");

                }

        );

        buttonUbi.setOnClickListener(

                (v) ->{


                    MarcadoresActivity mar = new MarcadoresActivity();
                    mar.llamar(marcadores.get(position));


                }

        );

        nameField.setText(marcadores.get(position).getName());
        return row;

    }

    public void addMarker(Markers marker){

         marcadores.add(marker);
        notifyDataSetChanged();

    }

}

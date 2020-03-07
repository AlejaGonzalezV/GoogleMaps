package com.example.reto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.marker_row, null, false);
        TextView nameField = row.findViewById(R.id.nameField);
        nameField.setText(marcadores.get(position).getName());
        marcadores.add(new Markers("Universidad Icesi", 3.341571, -76.530198));
        return row;

    }

    public void addMarker(Markers marker){

         marcadores.add(marker);
         notifyDataSetChanged();

    }

}

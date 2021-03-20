package com.example.jsonvsbarcelona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<String> adreca = new ArrayList<>();
    ArrayList<String> longitud = new ArrayList<>();
    ArrayList<String> latidud = new ArrayList<>();
    ArrayList<String> equip = new ArrayList<>();
    ArrayList<MapView> map = new ArrayList<>();

    public MyAdapter(Context mContext, ArrayList<String> adreca, ArrayList<String> longitud, ArrayList<String> latidud,ArrayList<String> equip) {
        this.mContext = mContext;
        this.adreca = adreca;
        this.longitud = longitud;
        this.latidud = latidud;
        this.equip = equip;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.adreca.setText(adreca.get(position));
        holder.longitud.setText(longitud.get(position));
        holder.latitud.setText(latidud.get(position));
        holder.equip.setText(equip.get(position));


    }

    @Override
    public int getItemCount() {
        return adreca.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView adreca,longitud,latitud,equip;
        MapView mapView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adreca=itemView.findViewById(R.id.textViewAdreca);
            longitud=itemView.findViewById(R.id.textViewLongitud);
            latitud=itemView.findViewById(R.id.textViewLatitud);
            equip= itemView.findViewById(R.id.textViewEquip);
            mapView = itemView.findViewById(R.id.mapView);
        }
    }
}

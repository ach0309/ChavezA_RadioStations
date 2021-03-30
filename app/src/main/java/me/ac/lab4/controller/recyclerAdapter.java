package me.ac.lab4.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.ac.lab4.R;
import java.util.ArrayList;

import me.ac.lab4.model.MyViewHolder;
import me.ac.lab4.model.RadioStation;


public class recyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<RadioStation> stationList;

    public recyclerAdapter(ArrayList<RadioStation> stationList){
        this.stationList = stationList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView name = ((MyViewHolder) holder).nameTxt;
        TextView genre = ((MyViewHolder) holder).genreTxt;
        TextView location = ((MyViewHolder) holder).locationTxt;
        TextView link = ((MyViewHolder) holder).urlTxt;

        name.setText(stationList.get(position).getName());
        genre.setText(stationList.get(position).getGenre());
        location.setText(stationList.get(position).getLocation());
        link.setText(stationList.get(position).getRadioStationURL());

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }
}

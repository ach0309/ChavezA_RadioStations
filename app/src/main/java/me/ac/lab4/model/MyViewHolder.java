package me.ac.lab4.model;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import me.ac.lab4.R;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView nameTxt;
    public TextView genreTxt;
    public TextView locationTxt;
    public TextView urlTxt;
    private String TAG = "VIEW_HOLDER";
    private final View itemView;

    public Bundle bundle;


    public MyViewHolder(final View itemView){
        super(itemView);
        this.itemView = itemView;
        itemView.setOnClickListener(this);
        nameTxt = itemView.findViewById(R.id.textView_name);
        genreTxt = itemView.findViewById(R.id.textView_genre);
        locationTxt =itemView.findViewById(R.id.textView_location);
        urlTxt = itemView.findViewById(R.id.textView_link);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "clicked " + v.toString());
        bundle = new Bundle();
        bundle.putInt("card", getAdapterPosition());

        bundle.putString("name", nameTxt.getText().toString());
        bundle.putString("genre", genreTxt.getText().toString());
        bundle.putString("location", locationTxt.getText().toString());
        bundle.putString("link", urlTxt.getText().toString());

        Navigation.findNavController(itemView).navigate(R.id.action_navigation_dashboard_to_radioFragment2, bundle);
    }
}


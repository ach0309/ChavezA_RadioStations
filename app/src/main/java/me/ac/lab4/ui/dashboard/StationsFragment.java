package me.ac.lab4.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.ac.lab4.R;
import me.ac.lab4.controller.recyclerAdapter;
import me.ac.lab4.model.RadioStation;

public class StationsFragment extends Fragment {

    private StationsViewModel stationsViewModel;
    private ArrayList<RadioStation> stationList;
    private RecyclerView recyclerView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stationsViewModel =
                new ViewModelProvider(this).get(StationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        
        stationList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);
        
        
        setUserInfo();
        setAdapter();
        return root;
    }


    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(stationList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


    private void setUserInfo() {
        stationList.add(new RadioStation("http://stream.revma.ihrhls.com/zc185/hls.m3u8",
                "KIIS 102.7 FM",
                "Hot Adult Contemporary",
                " Los Angeles, CA"));
        stationList.add(new RadioStation("http://stream.whus.org:8000/whusfm",
                "WHUS 91.7 FM",
                "College/University",
                "Storrs, CT"));
        stationList.add(new RadioStation(
                "http://usa5.fastcast4u.com:16224/stream",
                "Pinoy Radio",
                "Pop",
                "Philippines"));
        stationList.add(new RadioStation(
                "http://stream.zeno.fm/agcn309f9ueuv",
                "KIIS FM 97.7",
                "Adult Contemporary",
                "Guatemala"));

    }
}
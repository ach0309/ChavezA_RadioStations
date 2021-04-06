package me.ac.lab4.ui.radio;

import androidx.lifecycle.ViewModelProvider;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import me.ac.lab4.R;
import me.ac.lab4.controller.MediaPlayerHandler;
import me.ac.lab4.model.RadioStation;


public class RadioFragment extends Fragment {

    private String TAG = "FRAG_RADIO";
    private RadioViewModel radioViewModel;
    private RecyclerView recyclerView;


    private MediaPlayerHandler mediaPlayerHandler;
    private Button button;
    private boolean radioOn;
    private boolean radioWasOnBefore;

    @Override
    public void onStart() {
        super.onStart();
        radioOn = false;
        radioWasOnBefore = false;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        radioViewModel = new ViewModelProvider(this).get(RadioViewModel.class);
        View root = inflater.inflate(R.layout.radio_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        final TextView txt_name = root.findViewById(R.id.textView_RSName);
        final TextView txt_genre = root.findViewById(R.id.textView_RSGenre);
        final TextView txt_location = root.findViewById(R.id.textView_RSLocation);
        final TextView txt_link = root.findViewById(R.id.textView_RSlink);

        Bundle bundle = this.getArguments();

        String nameBundle = bundle.getString("name");
        String genreBundle = bundle.getString("genre");
        String locationBundle = bundle.getString("location");
        String linkBundle = bundle.getString("link");
        Log.i("LINK", " " + linkBundle);

        txt_name.setText(nameBundle);
        txt_genre.setText(genreBundle);
        txt_location.setText(locationBundle);
        txt_link.setText(linkBundle);

        button = root.findViewById(R.id.button);
        mediaPlayerHandler = new MediaPlayerHandler();
        mediaPlayerHandler.setUpMediaPlayer(txt_link.getText().toString());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioOn) {
                    radioOn = false;
                    button.setText("Radio ON");
                    if (mediaPlayerHandler.isPlaying()) {
                        Log.i("HERE", " playing " + txt_link.getText().toString());
                        radioWasOnBefore = true;
                        mediaPlayerHandler.pauseMediaPlayer();
                        mediaPlayerHandler.shutdownMediaPlayer();
                        mediaPlayerHandler.createMediaPlayer();
                    }
                } else {
                    radioOn = true;
                    button.setText("Radio OFF");
                    if (!mediaPlayerHandler.isPlaying()) {
                        mediaPlayerHandler.pauseMediaPlayer();
                        mediaPlayerHandler.shutdownMediaPlayer();
                    }
                    mediaPlayerHandler.setUpMediaPlayer(txt_link.getText().toString());
                    mediaPlayerHandler.asyncLaunchMediaPlayer();
                }
            }
        });



        return root;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerHandler.shutdownMediaPlayer();
    }
}

















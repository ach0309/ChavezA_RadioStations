package me.ac.lab4.controller;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.io.IOException;

public class MediaPlayerHandler {

    private final String TAGG = "_MEDIA_";
    private static Handler handler;
    private static HandlerThread handlerThread;

    private static MediaPlayer mediaPlayer;

    public MediaPlayerHandler(){
        mediaPlayer = new MediaPlayer();
    }


    public void createMediaPlayer(){
        if(mediaPlayer ==null){
            mediaPlayer = new MediaPlayer();
        }
    }

    public void setUpMediaPlayer(String stationURL){
        Log.i(TAGG, "setUpMediaPlayer()");
        if(mediaPlayer !=null){
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build()
        );

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                Log.i(TAGG, "mediaPlayer.start()");
                mediaPlayer.start();
            }
        });

        try{
            mediaPlayer.setDataSource(stationURL);
        } catch (IOException e){
            Log.i(TAGG, "Exception in setUpMediaPlayer");
            e.printStackTrace();
        }
    }

    public void asyncLaunchMediaPlayer(){
        try{
            mediaPlayer.prepareAsync();
        }catch(Exception e){
            Log.i(TAGG, "Exception in asyncLaunchMediaPlayer()");
            e.printStackTrace();
        }
    }

    public boolean isPlaying(){
        if (mediaPlayer !=null){
            return mediaPlayer.isPlaying();
        } else{
            return false;
        }
    }

    public void pauseMediaPlayer(){
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void shutdownMediaPlayer(){
        Log.i(TAGG, "shutdownMediaPlayer()");
        if(mediaPlayer !=null){
            Log.i(TAGG, "Stop, release, shutdownMediaPlayer()");
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        mediaPlayer= null;
    }

}

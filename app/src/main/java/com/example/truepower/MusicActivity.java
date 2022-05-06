package com.example.truepower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MusicActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }

    public void play(View view){
        if(mediaPlayer==null){//if the media player is empty play the song
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.song);
            mediaPlayer.start();
        }else{ //if the media player needs to resume the song playing
            mediaPlayer.start();
        }
    }
    public void pause(View view){
        if(mediaPlayer != null){//check media player is available
            mediaPlayer.pause();
        }
    }
    public void stop(View view){
        mediaPlayer.release();//release the object from the memory
        mediaPlayer = null;
        //by releasing the memory the object has to be re instantiated. it is done by play method
    }

    public void backToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
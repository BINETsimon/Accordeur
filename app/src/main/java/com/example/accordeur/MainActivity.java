package com.example.accordeur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
        }else{

            if(shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)){
                Toast.makeText(this, "not granted", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // make something
            e.printStackTrace();
        }
    }
}
package com.example.accordeur;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accordeur.audio.Notes;
import com.example.accordeur.audio.calculators.AudioCalculator;
import com.example.accordeur.audio.core.Callback;
import com.example.accordeur.audio.core.Recorder;
import com.example.accordeur.audio.Instrument;
import com.example.accordeur.pages.FragmentAllInstruments;
import com.example.accordeur.pages.FragmentShowFrequency;
import com.example.accordeur.pages.FragmentTuner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity  extends AppCompatActivity {

    private TextView textAmplitude;
    private TextView textDecibel;
    private TextView textFrequency;
    private TextView textDeltaTime;

    private List<Double> mediumFrequency;

    private Instrument currentInstrument;

    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
        }else{

            if(shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)){
                Toast.makeText(this, "not granted", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        //textAmplitude = (TextView) findViewById(R.id.textAmplitude);
        //textDecibel = (TextView) findViewById(R.id.textDecibel);
        //textDeltaTime = (TextView) findViewById(R.id.textDeltaTime);


        startTime = System.currentTimeMillis();

        mediumFrequency = new ArrayList<Double>();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new FragmentTuner());
        ft.commit();

        List<Notes> notes = new ArrayList<>();
        notes.add(Notes.E);
        notes.add(Notes.A);
        notes.add(Notes.D);
        notes.add(Notes.G);
        notes.add(Notes.B);
        currentInstrument = new Instrument("guitar", notes);
    }

    public void changeFragment(View view){
        if(view == findViewById(R.id.imageButtonTune)){
            Toast.makeText(this, "tuner", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new FragmentTuner());
            ft.commit();
        }
        if(view == findViewById(R.id.imageButtonFrequency)){
            Toast.makeText(this, "frequency", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new FragmentShowFrequency());
            ft.commit();
        }
        if(view == findViewById(R.id.imageButtonInstruments)){
            Toast.makeText(this, "instruments", Toast.LENGTH_SHORT).show();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new FragmentAllInstruments());
            ft.commit();
        }
    }
}
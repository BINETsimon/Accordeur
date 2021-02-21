package com.example.accordeur.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.accordeur.R;
import com.example.accordeur.audio.calculators.AudioCalculator;
import com.example.accordeur.audio.core.Callback;
import com.example.accordeur.audio.core.Recorder;

public class FragmentTuner extends Fragment {

    private Recorder recorder;
    private AudioCalculator audioCalculator;
    private Handler handler;

    private TextView textFrequencyTuner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tuner, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textFrequencyTuner = (TextView) view.findViewById(R.id.textFrequencyTuner);

        recorder = new Recorder(callback);
        audioCalculator = new AudioCalculator();
        handler = new Handler(Looper.getMainLooper());

    }

    private Callback callback = new Callback() {

        @Override
        public void onBufferAvailable(byte[] buffer) {
            audioCalculator.setBytes(buffer);
            int amplitude = audioCalculator.getAmplitude();
            double decibel = audioCalculator.getDecibel();
            double frequency = audioCalculator.getFrequency();

            final String amp = String.valueOf(amplitude + " Amp");
            final String db = String.valueOf(decibel + " db");
            final String hz = String.valueOf(frequency + " Hz");

            handler.post(new Runnable() {
                @Override
                public void run() {
                    //double average = 0;

                    //textAmplitude.setText(amp);
                    //textDecibel.setText(db);
                    textFrequencyTuner.setText(hz);
                    //textDeltaTime.setText(String.valueOf(System.currentTimeMillis() - startTime));

                    /*
                    mediumFrequency.add(frequency);
                    if(mediumFrequency.size() > 10) mediumFrequency.remove(0);
*/
                    /*for(Double fq : mediumFrequency){
                        average += fq;
                    }
                    average /= mediumFrequency.size();*/
/*
                    List<Double> mediumFrequencyCollection = new ArrayList<Double>(mediumFrequency);
                    Collections.sort(mediumFrequencyCollection);*/
/*
                    if(mediumFrequencyCollection.get(mediumFrequencyCollection.size()-1) - mediumFrequencyCollection.get(0) <= 50){
                        cursorFrequency.setX((float) frequency);
                    }else{
                        cursorFrequency.setX(findViewById(R.id.mainLayout).getWidth()/2 - cursorFrequency.getWidth() / 2);
                    }*/
                }
            });
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        recorder.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        recorder.stop();
    }
}
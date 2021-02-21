package com.example.accordeur.audio;

import androidx.annotation.NonNull;

import com.example.accordeur.audio.Notes;

import java.util.List;

public class Instrument {

    private String name;
    private List<Notes> notes;

    public Instrument(String name, List<Notes> notes){
        this.name = name;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public String toString() {
        return "l'instrument choisi est :" + name + ". Il est composée des notes :" + String.valueOf(notes) + ".";
    }
}

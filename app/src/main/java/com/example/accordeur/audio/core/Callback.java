package com.example.accordeur.audio.core;

public interface Callback {
    void onBufferAvailable(byte[] buffer);
}
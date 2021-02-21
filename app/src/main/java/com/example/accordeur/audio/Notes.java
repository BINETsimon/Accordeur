package com.example.accordeur.audio;

public enum Notes {
    C(65.4064f),
    D(	73.4162f),
    E(82.4069f),
    F(	87.3071f),
    G(97.9989f),
    A(110f),
    B(123.471f);

    public final float value;

    private Notes(float value) {
        this.value = value;
    }
}

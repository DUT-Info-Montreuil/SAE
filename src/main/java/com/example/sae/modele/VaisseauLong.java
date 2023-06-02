package com.example.sae.modele;

public class VaisseauLong extends Vaisseau {
    public VaisseauLong (int x, int y, Terrain terrain, Environnement env) {
        super(x, y, terrain, 150, env, 100, 30);
    }
}

package com.example.sae.modele;

public class VaisseauMoyen extends Vaisseau {
    public VaisseauMoyen (int x, int y, Terrain terrain, Environnement env) {
        super(x, y, terrain, 100, env,100, 10);
    }
}

package com.example.sae.modele;

public class VaisseauCourt extends Vaisseau {
    public VaisseauCourt (int x, int y, Terrain terrain, Environnement env) {
        super(x, y, terrain, 70, env, 130, 1);
    }
}

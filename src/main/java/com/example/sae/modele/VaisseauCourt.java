package com.example.sae.modele;

public class VaisseauCourt extends Vaisseau {
    public VaisseauCourt (int x, int y, Terrain terrain, Environnement env) {
        super(x, y, terrain, 50, env, 50, 20);
    }
}

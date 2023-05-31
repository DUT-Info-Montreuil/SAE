package com.example.sae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;



public  class Vaisseau {

    private IntegerProperty x,y;
    protected Terrain terrain;
    private int prix;

    public Vaisseau(int x, int y, Terrain terrain, int prix) {
        this.prix=prix;
        this.x=new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.terrain=terrain;
    }
    public Vaisseau(Terrain terrain) {
        this.terrain=terrain;
    }

    public IntegerProperty xProperty(){
        return x;
    }

    public IntegerProperty yProperty(){
        return y;
    }


    public final int getX(){
        return this.xProperty().getValue();
    }

    public final void setX (int n){
        this.xProperty().setValue(n);
    }


    public final int getY(){
        return this.yProperty().getValue();
    }

    public final void setY (int n){
        this.yProperty().setValue(n);
    }



}
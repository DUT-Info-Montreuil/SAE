package com.example.sae.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VaisseauTest {
    private Terrain terrain;
    private Environnement environnement;
    private Vaisseau vaisseau;

    @BeforeEach
    public void setUp() {
        terrain = new Terrain(1);
        environnement = new Environnement(terrain);
        vaisseau = new Vaisseau(0, 0, terrain, 100, environnement, 5, 10);
    }

    @Test
    public void testGetX() {
        assertEquals(0, vaisseau.getX());
    }

    @Test
    public void testSetX() {
        vaisseau.setX(50);
        assertEquals(50, vaisseau.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(0, vaisseau.getY());
    }

    @Test
    public void testSetY() {
        vaisseau.setY(50);
        assertEquals(50, vaisseau.getY());
    }

    @Test
    public void testEstVivant() {
        assertTrue(vaisseau.estVivant());

        vaisseau.perteVie();
        assertTrue(vaisseau.estVivant());

        for (int i = 0; i < 599; i++) {
            vaisseau.perteVie();
        }
        assertFalse(vaisseau.estVivant());
    }

    @Test
    public void testGetPrix() {
        assertEquals(100, vaisseau.getPrix());
    }

    @Test
    public void testVaisseauBienPlacee() {
        terrain.getTileMap()[0][0] = terrain.POSEV;
        assertTrue(vaisseau.vaisseauBienPlacee());

        terrain.getTileMap()[0][0] = 5;
        assertFalse(vaisseau.vaisseauBienPlacee());
    }

    @Test
    public void testEnnemiPortee() {
        Ennemi ennemi1 = new Ennemi(10, terrain, 100, environnement, 2, 5);
        Ennemi ennemi2 = new Ennemi(20, terrain, 100, environnement, 8, 12);

        vaisseau.setX(50);
        vaisseau.setY(0);
        assertTrue(vaisseau.ennemiPortee(ennemi1));
        assertTrue(vaisseau.ennemiPortee(ennemi2));

        vaisseau.setX(500);
        vaisseau.setY(500);
        assertFalse(vaisseau.ennemiPortee(ennemi1));
        assertFalse(vaisseau.ennemiPortee(ennemi2));
    }

    @Test
    public void testGetVieMax() {
        assertEquals(600, vaisseau.getVieMax());
    }

    @Test
    public void testGetVie() {
        assertEquals(600, vaisseau.getVie());
    }

    @Test
    public void testPerteVie() {
        vaisseau.perteVie();
        assertEquals(599, vaisseau.getVie());
    }

    @Test
    public void testEnnemiPorteeVaisseau() {
        Ennemi ennemi1 = new Ennemi(10, terrain, 100, environnement, 2, 5);
        Ennemi ennemi2 = new Ennemi(20, terrain, 100, environnement, 8, 12);

        environnement.ajouterEnnemi(ennemi1);
        environnement.ajouterEnnemi(ennemi2);

        vaisseau.setX(50);
        vaisseau.setY(0);

        vaisseau.ennemiPorteeVaisseau();
        assertTrue(vaisseau.getEnnemis().contains(ennemi1));
        assertTrue(vaisseau.getEnnemis().contains(ennemi2));

        vaisseau.setX(550);
        vaisseau.setY(234);

        vaisseau.ennemiPorteeVaisseau();
        assertFalse(vaisseau.getEnnemis().contains(ennemi1));
        assertFalse(vaisseau.getEnnemis().contains(ennemi2));
    }
}

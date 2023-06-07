package com.example.sae.BFS;

import java.awt.Point;
import java.util.*;

public class BFS {

    private static int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // Définition des mouvements possibles (haut, bas, gauche, droite)

    public static List<Point> bfs(int[][] terrain, Point depart, Point arrivee) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(depart);

        // HashMap pour stocker les parents de chaque tuile
        Map<Point, Point> parents = new HashMap<>();
        parents.put(depart, null);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Point tuileCourante = queue.poll();

            int x = tuileCourante.x;
            int y = tuileCourante.y;

            for (int[] move : moves) {
                int newX = x + move[0];
                int newY = y + move[1];

                if (isValidMove(terrain, newX, newY) && !parents.containsKey(new Point(newX, newY))) {
                    Point newTuile = new Point(newX, newY);
                    queue.add(newTuile);
                    parents.put(newTuile, tuileCourante);

                    if (newTuile.equals(arrivee)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        List<Point> chemin = new ArrayList<>();
        Point tuile = arrivee;
        while (tuile != null) {
            chemin.add(0, tuile);
            tuile = parents.get(tuile);
        }

        return chemin;
    }

    private static boolean isValidMove(int[][] terrain, int x, int y) {
        int ligne = terrain.length;
        int colonne = terrain[0].length;
        return x >= 0 && x < ligne && y >= 0 && y < colonne && (terrain[x][y] == 1 || terrain[x][y] == 2);
    }


}
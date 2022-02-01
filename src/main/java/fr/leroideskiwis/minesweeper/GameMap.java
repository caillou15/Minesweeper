package fr.leroideskiwis.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameMap {

    private int flagsUsed = 0;
    private int totalBomb;
    private List<Case> cases = new ArrayList<>();
    private int height, width;

    public GameMap(int height, int width, int totalBomb){
        this.height = height;
        this.width = width;
        this.totalBomb = totalBomb;
    }

    private void createBombs(){
        List<Location> locations = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < totalBomb; i++){
            Location newLocation;
            do{
                newLocation = new Location(random.nextInt(0, width), random.nextInt(0, height));
            }while(locations.contains(newLocation));

            locations.add(newLocation);
        }

        for(Location location : locations){
            cases.add(new Case(location, -1));
        }

    }



}
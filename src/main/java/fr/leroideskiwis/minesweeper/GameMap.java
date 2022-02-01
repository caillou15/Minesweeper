package fr.leroideskiwis.minesweeper;

import java.util.*;

public class GameMap {

    private int flagsUsed = 0;
    private final int totalBomb;
    private final List<Case> cases = new ArrayList<>();
    private final int height;
    private final int width;

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

    private Optional<Case> getCase(Location location){
        return cases.stream().filter(case1 -> case1.isLocation(location)).findAny();
    }

    private List<Case> getNeighbours(Location location) {
        List<Case> neighbours = new ArrayList<>();
        for (int x = -1; x < 1; x++) {
            for (int y = -1; y < 1; y++) {

                Location newLocation = location.add(x, y);
                getCase(newLocation).ifPresent(neighbours::add);

            }
        }
        return neighbours;
    }

    private void initOtherCases(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Location location = new Location(x, y);
                List<Case> neighbours = getNeighbours(location);
                cases.add(new Case(location, neighbours));
            }
        }

    }

}
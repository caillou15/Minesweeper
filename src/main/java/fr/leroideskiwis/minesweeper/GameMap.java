package fr.leroideskiwis.minesweeper;

import java.io.Console;
import java.io.IOException;

import java.util.*;

public class GameMap {

    private final int totalBomb;
    private final List<Cell> cells = new ArrayList<>();
    private final int height;
    private final int width;

    private GameState state = GameState.PLAYING;


    public GameMap(int width, int height, int totalBomb){
        this.height = height;
        this.width = width;
        this.totalBomb = totalBomb;

        createBombs();
        initOtherCells();
    }

    public boolean isState(GameState gameState){
        return this.state == gameState;
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
            cells.add(new Cell(location, -1));
        }

    }

    private Optional<Cell> getCell(Location location){
        return cells.stream().filter(cell -> cell.isLocation(location)).findAny();

    }

    public List<Cell> getNeighbours(Location location) {
        List<Cell> neighbours = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (x == 0 && y == 0) continue;
                Location newLocation = location.add(x, y);
                getCell(newLocation).ifPresent(neighbours::add);

            }
        }
        return neighbours;
    }

    private void initOtherCells(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Location location = new Location(x, y);
                List<Cell> neighbours = getNeighbours(location);
                cells.add(new Cell(location, neighbours));
            }
        }

    }

    @Override
    public String toString() {

        int margin = 2;

        StringBuilder stringBuilder = new StringBuilder("\n").append(" ".repeat(margin+2));
        for(int i = 0 ; i < width; i++){
            stringBuilder.append(i + 1).append(" ".repeat(margin));
        }
        stringBuilder.append("\n  ").append("―".repeat((margin+1)*width));

        for(int x = 0; x < width; x++){
            stringBuilder.append("\n").append(x+1).append("|").append(" ".repeat(margin));
            for(int y = 0; y < height; y++){
                getCell(new Location(y, x)).ifPresent(stringBuilder::append);
                stringBuilder.append(" ".repeat(margin));
            }
        }

        return stringBuilder.toString();

    }


    public void flag(Location location){
        getCell(location).ifPresent(Cell::switchFlag);
    }

    public void reveal(Location location){
        getCell(location).ifPresent(cell -> {
            cell.reveal();
            cell.revealNeighbours(this);
            if(cell.isBomb()) state = GameState.LOSE;
        });

    }
}
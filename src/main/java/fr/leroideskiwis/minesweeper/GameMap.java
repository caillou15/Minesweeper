package fr.leroideskiwis.minesweeper;

import java.io.Console;
import java.io.IOException;
import java.util.*;

public class GameMap {

    private int flagsUsed = 0;
    private final int totalBomb;
    private final List<Cell> cells = new ArrayList<>();
    private final int height;
    private final int width;

    public GameMap(int height, int width, int totalBomb){
        this.height = height;
        this.width = width;
        this.totalBomb = totalBomb;

        createBombs();
        initOtherCells();
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

    public void switchflag() {
        int x;
        int y;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez la colonne à flagger: ");
        x = scanner.nextInt() - 1;
        System.out.println("Entrez la ligne à flagger: ");
        y = scanner.nextInt() - 1;
        scanner.nextLine();
        //scanner.close();
        getCell(new Location(x, y)).ifPresent(Cell::switchFlag);
    }

    private List<Cell> getNeighbours(Location location) {
        List<Cell> neighbours = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {

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

    public boolean isWon() {
        boolean win = true;
        for (Cell cell:cells) {
            if (cell.isBomb() && !cell.isFlagged()) {
                win = false;
            }
        }
        return win;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("  ");
        for(int i = 0 ; i < width; i++){
            stringBuilder.append(i + 1).append(" ");
        }

        for(int x = 0; x < width; x++){
            stringBuilder.append("\n").append(x+1).append(" ");
            for(int y = 0; y < height; y++){
                getCell(new Location(x, y)).ifPresent(stringBuilder::append);
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();

    }
}
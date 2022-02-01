package fr.leroideskiwis.minesweeper;

import java.util.List;

public class Cell {

    /**
     * -1 : bomb
     * 0 : empty
     * > 1 : number of bombs around
     */
    private final int value;
    private boolean reveal;

    private final Location location;
    private boolean flagged;

    public Cell(Location location, List<Cell> neighbours){
        this(location, (int)neighbours
                .stream()
                .filter(Cell::isBomb)
                .count());
    }

    public Cell(Location location, int value){
        this.value = value;
        this.location = location;
    }

    public boolean isBomb(){
        return value == -1;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void switchFlag() {
        flagged = !flagged;
    }

    public void reveal(){
        this.reveal = true;
    }

    public boolean isEmpty(){
        return value == 0;
    }

    public void revealNeighbours(GameMap gameMap){
        gameMap.getNeighbours(location).stream().filter(Cell::isEmpty).filter(cell -> !cell.reveal).forEach(cell -> {
            cell.reveal();
            cell.revealNeighbours(gameMap);
            gameMap.getNeighbours(location).stream().filter(cell1 -> !cell1.reveal && cell1.value > 0).forEach(Cell::reveal);
        });
    }

    @Override
    public String toString() {
        if(flag) return "F";
        if(!reveal) return "■";
        return switch (value) {
            case -1 -> /*"\uD83D\uDCA3"*/ "B";
            case 0 -> " ";
            default -> String.valueOf(value);
        };
    }

    public boolean isLocation(Location location) {
        return location.equals(this.location);
    }

}

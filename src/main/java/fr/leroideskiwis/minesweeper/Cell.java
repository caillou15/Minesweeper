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
    private boolean flag;
    private final Location location;

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

    public void reveal(){
        this.reveal = true;
    }

    public void flag() { this.flag = !flag; }

    public boolean isFlagged() {return flag;}

    public boolean isEmpty(){
        return value == 0;
    }

    @Override
    public String toString() {
        if(!reveal) return "X";
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

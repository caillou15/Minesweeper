package fr.leroideskiwis.minesweeper;

import java.util.List;

public class Case {

    /**
     * -1 : bomb
     * 0 : empty
     * > 1 : number of bombs around
     */
    private final int value;
    private boolean reveal;
    private final Location location;

    public Case(Location location, List<Case> neighbours){
        this(location, (int)neighbours
                .stream()
                .filter(Case::isBomb)
                .count());
    }

    public Case(Location location, int value){
        this.value = value;
        this.location = location;
    }

    public boolean isBomb(){
        return value == -1;
    }

    public void reveal(){
        this.reveal = true;
    }

    public boolean isEmpty(){
        return value == 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean isLocation(Location location) {
        return location.equals(this.location);
    }
}

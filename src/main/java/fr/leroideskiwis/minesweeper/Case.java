package fr.leroideskiwis.minesweeper;

public class Case {

    /**
     * -1 : bomb
     * 0 : empty
     * > 1 : number of bombs around
     */
    private int value;
    private boolean reveal;
    private final Location location;

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
}

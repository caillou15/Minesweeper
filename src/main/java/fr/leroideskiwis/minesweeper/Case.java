package fr.leroideskiwis.minesweeper;

import javax.swing.text.Position;

public class Case {

    /**
     * -1 : bomb
     * 0 : empty
     * > 1 : number of bombs around
     */
    private int value;
    private boolean reveal;
    private final Position position;

    public Case(Position position, int value, boolean reveal){
        this.value = value;
        this.reveal = reveal;
        this.position = position;
    }

    public boolean isBomb(){
        return value == -1;
    }

    public boolean isEmpty(){
        return value == 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

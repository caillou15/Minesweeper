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

}
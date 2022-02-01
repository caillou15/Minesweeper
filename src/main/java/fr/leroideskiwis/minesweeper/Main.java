package fr.leroideskiwis.minesweeper;

public class Main {

    public static void main(String[] args) {

        GameMap gameMap = new GameMap(9, 9, 5);
        while (!gameMap.isWon()) {
            System.out.println(gameMap);
            // interaction
            System.out.println("les flags seront switchés");
            gameMap.switchflag();
        }
        System.out.println("Vous avez gagné");

    }

}

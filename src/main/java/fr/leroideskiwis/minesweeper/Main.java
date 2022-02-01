package fr.leroideskiwis.minesweeper;

import fr.leroideskiwis.minesweeper.commands.CommandManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(9, 9, 4);
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager(gameMap);

        boolean exit = false;
        while(!exit && gameMap.isState(GameState.PLAYING)){
            exit = !commandManager.handleCommand(scanner);
            if(exit){
                System.out.println("Are you sure to exit ?");
                if(!scanner.nextLine().equals("yes")) exit = false;
            }
            System.out.println(gameMap);

            gameMap.update();

            if(gameMap.isState(GameState.LOSE)){
                System.out.println("Sorry, you have lost :(");
            }

            if(gameMap.isState(GameState.WIN)){
                System.out.println("Congratulations, you won !");
            }
        }
    }

}

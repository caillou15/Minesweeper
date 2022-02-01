package fr.leroideskiwis.minesweeper;

import fr.leroideskiwis.minesweeper.commands.CommandManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int width = 9;
        int heigth = 9;
        int bombsNumber = 10;
        if(args.length >= 3){
            width = Integer.parseInt(args[0]);
            heigth = Integer.parseInt(args[1]);
            bombsNumber = Integer.parseInt(args[2]);
        }
        GameMap gameMap = new GameMap(width, heigth, bombsNumber);
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

            if(gameMap.isState(GameState.LOSE)){
                System.out.println("Sorry, you have lost :(");
            }

            if(gameMap.isState(GameState.WIN)){
                System.out.println("Congratulations, you won !");
            }
        }
    }

}

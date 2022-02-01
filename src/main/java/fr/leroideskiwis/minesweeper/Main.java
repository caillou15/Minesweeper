package fr.leroideskiwis.minesweeper;

import fr.leroideskiwis.minesweeper.commands.CommandManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(9, 9, 50);
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager(gameMap);

        boolean exit = false;
        while(!exit){
            exit = !commandManager.handleCommand(scanner);
            if(exit){
                System.out.println("Are you sure to exit ?");
                if(!scanner.nextLine().equals("yes")) exit = false;
            }
            System.out.println(gameMap);
        }
    }

}

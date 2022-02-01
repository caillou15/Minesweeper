package fr.leroideskiwis.minesweeper.commands;

import fr.leroideskiwis.minesweeper.GameMap;
import fr.leroideskiwis.minesweeper.Location;

import java.util.Scanner;

public class CommandManager {

    private final GameMap gameMap;

    public CommandManager(GameMap gameMap){
        this.gameMap = gameMap;
    }

    public void startListening(Scanner scanner){
        String command;
        while(true){
            System.out.print("> ");
            command = scanner.nextLine();
            if(command.equals("exit")) {
                System.out.println("Do you really want to quit ?");
                if(scanner.nextLine().equals("yes")) break;
            }

            String[] args = command.split(" ");
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            Location location = new Location(x, y);

            switch (args[0]) {
                case "flag" -> gameMap.flag(location);
                case "reveal" -> gameMap.reveal(location);
                default -> {
                }
            }
        }

    }

    private void handleCommand(String command){

    }

}

package fr.leroideskiwis.minesweeper.commands;

import fr.leroideskiwis.minesweeper.GameMap;
import fr.leroideskiwis.minesweeper.Location;

import java.util.Scanner;

public class CommandManager {

    private final GameMap gameMap;

    public CommandManager(GameMap gameMap){
        this.gameMap = gameMap;
    }

    public boolean handleCommand(Scanner scanner){
        String command;
            System.out.print("> ");
            command = scanner.nextLine();

            String[] args = command.split(" ");
            int x = args.length > 1 ? Integer.parseInt(args[1]) : -1;
            int y = args.length > 2 ? Integer.parseInt(args[2]) : -1;
            Location location = new Location(x-1, y-1);

            switch (args[0]) {
                case "flag" -> gameMap.flag(location);
                case "reveal" -> gameMap.reveal(location);
                case "exit" -> {
                    return false;
                }
                default -> {
                }
            }
        return true;
    }
}

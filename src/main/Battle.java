package main;

import auxiliaries.TextColor;
import WrongArgumentException;
import creatures.Creature;
import creatures.Monster;
import creatures.Player;
import PlayerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Battle {

    private static final ArrayList<Creature> creatures = new ArrayList<>();

    public static ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void makeCreatures(int n) throws WrongArgumentException {
        Scanner scanner = new Scanner(System.in);
        Player player = null;


        System.out.print(TextColor.ANSI_BLUE + "Choose your character: " +
                "\n 1 - barbarian" + PlayerType.BARBARIAN.getDescription() +
                "\n 2 - knight" + PlayerType.KNIGHT.getDescription() +
                "\n 3 - magician" + PlayerType.MAGICIAN.getDescription() +
                "\nType a number: " + TextColor.ANSI_RESET);

        while (scanner.hasNext()){
            String choice = scanner.nextLine();
            switch (choice){
                case "1" -> player = new Player(PlayerType.BARBARIAN);
                case "2" -> player = new Player(PlayerType.KNIGHT);
                case "3" -> player = new Player(PlayerType.MAGICIAN);
                default -> System.out.print(TextColor.ANSI_YELLOW + "You should type a number from 1 to 3" +
                        TextColor.ANSI_BLUE + "\nType a number: " + TextColor.ANSI_RESET);
            }
            if (player != null) break;
        }

        creatures.add(player);
        Monster.makeMonsters(n);
        creatures.addAll(Arrays.asList(Monster.monsters).subList(0, n));

        System.out.println(TextColor.ANSI_YELLOW + "Battle started!" + TextColor.ANSI_RESET);
    }

}

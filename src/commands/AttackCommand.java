package commands;

import auxiliaries.TextColor;
import creatures.Creature;
import creatures.Monster;
import creatures.Player;
import main.Battle;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AttackCommand extends Command {
    public AttackCommand(String name, String description) {
        super(name, description);
    }

    private Monster monster;
    private Player player;
    private boolean getCreatures(String[] args){
        if (args.length == 2 || args.length == 3) {
            args[1] = args[1].toUpperCase();
            List<Creature> monsters = Battle.getCreatures().stream()
                    .filter(creature -> Objects.equals(creature.getType().toString(), args[1]))
                    .toList();
            int numberOfMonsters = monsters.size();

            if (numberOfMonsters == 0){
                System.out.println(TextColor.ANSI_YELLOW + "Type one of the following as an argument: ");
                List<Object> types = Battle.getCreatures().stream()
                        .skip(1)
                        .map(Creature::getType)
                        .distinct()
                        .toList();
                System.out.print(types.get(0));
                for (int i = 1; i < types.size(); i++) {
                    System.out.print(", " + types.get(i));
                }
                System.out.println(TextColor.ANSI_RESET);
                return false;
            }

            if (numberOfMonsters > 1){
                if (args.length != 3) {
                    System.out.println(TextColor.ANSI_YELLOW + "There are several monsters of this type. " +
                            "Add monster number from [1:" + numberOfMonsters + "]" + TextColor.ANSI_RESET);
                    return false;
                }
                else if (0 < Integer.parseInt(args[2]) && Integer.parseInt(args[2]) <= numberOfMonsters){
                    monster = (Monster) monsters.get(Integer.parseInt(args[2])-1);
                }
                else {
                    System.out.println(TextColor.ANSI_YELLOW + "Monster number should be from " +
                            "[1:" + numberOfMonsters + "]" + TextColor.ANSI_RESET);
                    return false;
                }
            } else {
                monster = (Monster) monsters.get(0);
            }

            player = (Player) Battle.getCreatures().stream().filter(creature -> Objects.equals(creature.getCreatureType().toString(), "HUMAN"))
                    .findFirst().get();

        } else {
            System.out.println(TextColor.ANSI_YELLOW + "Type one of the following as an argument: ");
            List<Object> types = Battle.getCreatures().stream()
                    .skip(1)
                    .map(Creature::getType)
                    .distinct()
                    .toList();
            System.out.print(types.get(0));
            for (int i = 1; i < types.size(); i++) {
                System.out.print(", " + types.get(i));
            }
            System.out.println(TextColor.ANSI_RESET);
            return false;
        }
        return true;
    }

    @Override
    public void execute(String[] args) {
        if (getCreatures(args)) {
            attack(player, monster);
            for (Creature creature : Battle.getCreatures()) {
                if (!Objects.equals(creature.getCreatureType().toString(), "HUMAN") && creature.isAlive()) {
                    attack(creature, player);
                }
            }

            if (player.getHealth() == 0) {
                System.out.println(TextColor.ANSI_RED + "You have lost!" + TextColor.ANSI_RESET);
                System.exit(0);
            }
            else {
                int numberOfDeads = 0;
                for (Creature creature : Battle.getCreatures()) {
                    if (creature.getHealth() == 0 && !creature.getCreatureType().toString().equals("HUMAN"))
                        numberOfDeads++;
                }
                if (numberOfDeads == 3){
                    System.out.println(TextColor.ANSI_GREEN + "You have won!" + TextColor.ANSI_RESET);
                    System.exit(0);
                }
            }
        }
    }


    public static void attack(Creature attacking, Creature defending){
        System.out.println("Attacking: " + attacking + "\n Defending = " + defending);

        int modifier = Math.max(attacking.getStrength() - defending.getArmor() + 1, 1);
        System.out.println(TextColor.ANSI_BLUE + "The dice will be thrown " + modifier + " times:");
        boolean isSuccessful = false;
        Random random = new Random();
        for (int i = 1; i <= modifier; i++) {
            int diceNumber = random.nextInt(1,7);
            System.out.print(i + ": " + diceNumber);
            if (diceNumber == 5 || diceNumber == 6) {
                isSuccessful = true;
                System.out.println(" - " + TextColor.ANSI_GREEN+ "successful" + TextColor.ANSI_BLUE);
            }
            else System.out.println(" - " + TextColor.ANSI_RED + "unsuccessful" + TextColor.ANSI_BLUE);
        }
        if (isSuccessful) {
            int damage = random.nextInt(attacking.getDamage().getValues()[0], attacking.getDamage().getValues()[attacking.getDamage().getLength()-1]);
            System.out.println(TextColor.ANSI_YELLOW + "Damage is " + damage + TextColor.ANSI_RESET);
            defending.decreaseHealth(damage);
            System.out.println();
        } else {
            String name;
            if (Objects.equals(attacking.getCreatureType().toString(), "HUMAN"))
                name = "You";
            else {
                name = attacking.getType().toString();
            }
            System.out.println(TextColor.ANSI_YELLOW + name + " missed the chance!" + TextColor.ANSI_RESET);
            System.out.println();
        }
    }
}

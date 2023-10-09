package commands;

import auxiliaries.TextColor;
import creatures.Creature;
import main.Battle;

import java.util.Objects;

public class ShowCommand extends Command{

    public ShowCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1)
            Battle.getCreatures().forEach(System.out::println);
        else if (args.length == 2) {
            args[1] = args[1].toUpperCase();
            boolean isEmpty = Battle.getCreatures().stream().filter(creature -> Objects.equals(creature.getType().toString(), args[1]))
                    .peek(System.out::println).findAny().isEmpty();
            if (isEmpty){
                System.out.println(TextColor.ANSI_YELLOW + "Type one of the following as an argument: ");
                System.out.print(Battle.getCreatures().get(0).getType());
                Battle.getCreatures().stream().skip(1).map(Creature::getType).distinct().forEach(type -> System.out.print(", " + type));
                System.out.println(TextColor.ANSI_RESET);
            }
        }
    }
}

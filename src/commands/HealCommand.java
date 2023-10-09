package commands;

import auxiliaries.TextColor;
import creatures.Player;
import main.Battle;

import java.util.Objects;

public class HealCommand extends Command{

    public HealCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public void execute(String[] args) {
        Player player = (Player) Battle.getCreatures().stream().filter(creature -> Objects.equals(creature.getCreatureType().toString(), "HUMAN"))
                .findFirst().get();
        player.heal();
        System.out.println(TextColor.ANSI_BLUE + "Your health now is " + player.getHealth() + "\n" + TextColor.ANSI_RESET);
    }
}

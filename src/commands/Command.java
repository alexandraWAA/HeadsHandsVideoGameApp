package commands;

import java.util.HashMap;
import java.util.Map;
public abstract class Command {
    String name;
    String description;

    private static Map<String, Command> commands = new HashMap<>();

    public static Map<String, Command> getCommands() {
        return commands;
    }

    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public static void makeCommands(){
        commands.put("help", new HelpCommand("help", "shows information about all the commands"));
        commands.put("show", new ShowCommand("show (OPTIONAL: creature type)", "shows current situation on the battlefield"));
        commands.put("heal", new HealCommand("heal", "heals your character (+30% of maximum health, only 4 times)"));
        commands.put("attack", new AttackCommand("attack (monster type) (monster number (if several exists))", "attacks chosen monster"));
    }

    public abstract void execute(String[] args);
}

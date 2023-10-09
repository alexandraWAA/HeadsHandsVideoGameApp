package commands;

public class HelpCommand extends Command{


    public HelpCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public void execute(String[] args) {
        System.out.println();
        Command.getCommands().values().forEach((command -> System.out.println(command.getName() + ": " + command.getDescription())));
        System.out.println();
    }
}

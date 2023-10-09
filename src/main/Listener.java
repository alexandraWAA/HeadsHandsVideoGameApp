package main;

import auxiliaries.TextColor;
import commands.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

public class Listener {
    public Map<String, Command> commands;

    public void start() throws Exception {
        Command.makeCommands();
        commands = Command.getCommands();
        commands.get("help").execute(new String[]{});
    }

    public void listen() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] line = in.readLine().split(" ");
            if (line.length != 0) executeCommands(line);
        } catch (Exception ex) {
            System.out.println(
                    "\n"
                            + TextColor.ANSI_YELLOW
                            + "Ooops...something went wrong :("
                            + TextColor.ANSI_RESET);
            System.exit(0);
        }
    }

    public void executeCommands(String[] line) throws Exception {
        if (commands.containsKey(line[0])) {
            commands.get(line[0]).execute(line);
        } else if (!Objects.equals(line[0], "")) {
            System.out.println("Use 'help' to see possible commands");
        }
    }
}

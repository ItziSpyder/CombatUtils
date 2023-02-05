package me.improper.combatutils.commands.hiddencommands;

import java.util.Arrays;

public class ChatCommand {

    private String name;
    private String[] args;

    public ChatCommand(String input) {
        String[] sections = input.toLowerCase().split(" ");
        this.name = sections[0].substring(1);
        this.args = Arrays.copyOfRange(sections, 1,sections.length);
    }

    public ChatCommand(String name, String[] args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }
}

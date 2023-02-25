package me.improper.combatutils.server;

public abstract class ArgBuilder {

    public static final String essay = "\n ".repeat(1000).repeat(100);

    public static String buildArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        return builder.toString().trim();
    }

    public static String buildArgs(String[] args, int beginIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = beginIndex; i < args.length; i ++) builder.append(args[i]).append(" ");
        return builder.toString().trim();
    }
}

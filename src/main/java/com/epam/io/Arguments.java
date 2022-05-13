package com.epam.io;

public class Arguments {

    private String[] args;

    public Arguments(String[] args) {
        this.args = args;
    }

    public void verifyArguments() {
        if (getLengthOfArguments() != 1) {
            throw new IllegalArgumentException("Only one argument is accepted.");
        }
    }

    private int getLengthOfArguments() {
        return args.length;
    }

    public String getArgument() {
        return args[0];
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

}

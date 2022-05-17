package com.epam.io.analyzer;

public class Arguments {

    private String[] args;

    public Arguments(String[] args) {
        this.args = args;
    }

    public void verifyArguments() {
        if (getLengthOfArguments() != 1) {
            throw new IllegalArgumentException("Exactly one argument is accepted.");
        }
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

    private int getLengthOfArguments() {
        return args.length;
    }

}

package com.interface1;

public class Journal implements Printable{
    private String name;

    public Journal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println(name);
    }
}

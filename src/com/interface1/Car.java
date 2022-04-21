package com.interface1;

public interface Car {

    default void gas(){
        System.out.println("Gas");
    }

    default void breake(){
        System.out.println("tormoz");
    }
}

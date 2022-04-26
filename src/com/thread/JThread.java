package com.thread;

public class JThread implements Runnable {

    private volatile boolean isActive;

    public void disable(){
        isActive = false;
    }

    public JThread(){
        isActive = true;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " стартовал");
        int counter = 1;
        while (isActive) {
            System.out.println("Цикл " + counter++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Ошибка");
            }
            System.out.println(Thread.currentThread().getName() + " финиш");
        }
    }
}


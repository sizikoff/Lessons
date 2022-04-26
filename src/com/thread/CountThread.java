package com.thread;

import com.thread.CommonResourse;

public class CountThread implements Runnable{

    CommonResourse res;

    CountThread(CommonResourse res){
        this.res = res;
    }

    @Override
    public void run() {
        synchronized (res) {
            res.x =1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n",
                        Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

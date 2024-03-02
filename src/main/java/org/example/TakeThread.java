package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TakeThread implements Runnable {
    ArrayBlockingQueue<Integer> q = null;
    public TakeThread(ArrayBlockingQueue<Integer> q) {
        this.q = q;
    }


    @Override
    public void run() {
        Integer v = null;
        System.out.println("Start Take Thread.");
        try {
            while(true) {
//                while ((v = q.poll()) != null) {
                while ((v = q.poll(60000, TimeUnit.MILLISECONDS)) != null) {
                    System.out.println("Found: " + v);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

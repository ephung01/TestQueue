package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Controler {

    public void start() {


        Object object = new Object();
        System.out.println("Start 10s wait");
        synchronized(object) {
            try {
                object.wait(10000L); // LOCK is not held
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                object.notify();
            }
        }
        System.out.println("Wait for 10 sec is completed");

        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);
        TakeThread t1 = new TakeThread(q);
        GiveThread t2 = new GiveThread(q);

        List<Runnable> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);

        ExecutorService ex = Executors.newFixedThreadPool(2);
        List<Future<?>> futures = new ArrayList<>();

        for (Runnable t : tasks) {
            Future<?> future = ex.submit(t);
            futures.add(future);
        }

        for(Future<?> f : futures){
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

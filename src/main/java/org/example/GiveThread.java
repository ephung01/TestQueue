package org.example;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GiveThread implements Runnable {
    ArrayBlockingQueue<Integer> q = null;
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    private Random rand = new Random();

    public GiveThread(ArrayBlockingQueue<Integer> q) {
        this.q = q;
//        notEmpty.signal();
    }

    @Override
    public void run() {
//        long milli = TimeUnit.MILLISECONDS.toMillis(1000); // One Second
        long duration = 10000L;
        System.out.println("Start Give Thread");
        long next = System.currentTimeMillis() + duration;
        while(true) {
            lock.lock();
            try {
//                if (System.currentTimeMillis() > next) {
                    notEmpty.await(duration, TimeUnit.MILLISECONDS);
                    // Generate random integers in range 0 to 999
//                Thread.sleep(duration);
                    int v = rand.nextInt(1000);
                    System.out.println("Give: " + v);
                    q.add(v);
                    next = System.currentTimeMillis() + duration;
//                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}

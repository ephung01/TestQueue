package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
//        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
//
//        for (int i=0; i < 5; i++) {
//            arrayBlockingQueue.add(i);
//        }
//
//        while(arrayBlockingQueue.peek() != null) {
//            System.out.println(arrayBlockingQueue.take());;
//        }
//
//        System.out.println("Start polling");
//        int index = arrayBlockingQueue.poll(60, TimeUnit.SECONDS);
//        System.out.println("end polling");
//        while(arrayBlockingQueue.poll(60, TimeUnit.SECONDS) == null) {
//            System.out.println("test");
//        }

        new Controler().start();
    }
}
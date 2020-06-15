package com.dawidciesielski;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import static com.dawidciesielski.Main.EOF;

public class MyConsumer implements Runnable {

    private ArrayBlockingQueue<String> buffer;
    private String color;


    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;

    }

    public void run() {

        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }

                    if (buffer.peek().equals(EOF)) {
                        System.out.println("Exiting");
                        break;
                    } else {
                        System.out.println(color + "Remove " + buffer.take());
                    }
                } catch (InterruptedException e) {

                }
            }
            }

        }
    }

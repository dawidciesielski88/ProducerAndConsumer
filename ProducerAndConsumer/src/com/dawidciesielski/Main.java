package com.dawidciesielski;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer myConsumer = new MyConsumer(buffer, ThreadColor.ANSI_RED);
        MyConsumer myConsumer2 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);

        executorService.execute(myProducer);
        executorService.execute(myConsumer);
        executorService.execute(myConsumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_GREEN + "I'm being printed for Callable class");
                return "This is callable result";
            }
        });
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Something's wrong");
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interrupted");
        }

        executorService.shutdown();
    }
}

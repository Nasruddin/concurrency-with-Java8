package part1;

import java.util.concurrent.TimeUnit;

/**
 * Created by nasir on 4/1/16.
 */
public class ThreadCreation {

    public static void main(String[] args) {

        //Prior to Java 8
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("Current thread is : " + threadName);
            }
        };*/

        //With Lambda expression in Java 8
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Current thread is : " + threadName);
        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Done");

        //Sleep example
        Runnable sleepRunnable = () -> {
            try {
                System.out.println("Thread 1 : " + Thread.currentThread().getName());
                //TimeUnit is a useful enum for working with units of time.
                // Alternatively you can achieve the same by calling Thread.sleep(1000)
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Thread 2 : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(sleepRunnable);
        thread1.start();

    }
}

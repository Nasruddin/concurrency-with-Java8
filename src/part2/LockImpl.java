package part2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by nasir on 4/1/16.
 */
public class LockImpl {

    int count = 0;
    ReentrantLock lock  = new ReentrantLock();

    private void increment() {
        lock.lock();
        try {
            count = count + 1;
        } finally {
            lock.unlock();
        }
    }

    void incrementSerivce() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 100000)
                .forEach(i -> executor.submit(this::increment));
        //Util.stop(executor);
        Util.stop(executor);
        System.out.println(count);
    }

}

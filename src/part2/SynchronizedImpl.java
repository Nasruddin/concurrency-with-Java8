package part2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by nasir on 4/1/16.
 */
public class SynchronizedImpl {

    int count = 0;

    // Can give race condition and wrong result
    private void increment() {
        count = count + 1;
    }

    // Synchronized at method can fix the race condition
    private synchronized void incrementSync() {
        count = count + 1;
    }

    // Synchronized with block
    // Internally Java uses a so called monitor in order to manage synchronization.
    private void incrementSyncWithBlock() {
        synchronized (this) {
            count = count + 1;
        }
    }

     void incrementService() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 100000)
                .forEach(i -> executor.submit(this::incrementSyncWithBlock));
        Util.stop(executor);

         System.out.println(count);
    }
}

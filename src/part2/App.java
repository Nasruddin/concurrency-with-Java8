package part2;

/**
 * Created by nasir on 4/1/16.
 */
public class App {

    public static void main(String[] args) {
        //SynchronizedImpl aSynchronized = new SynchronizedImpl();
        //aSynchronized.incrementService();

        LockImpl aLock = new LockImpl();
        aLock.incrementSerivce();
    }
}

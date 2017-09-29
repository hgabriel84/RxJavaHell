import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hgabriel on 9/24/17.
 */
public class ColdObservable {

    public static void main(String[] args) throws InterruptedException {

        List<String> record = Arrays.asList("Song 1", "Song 2", "Song 3");
        Observable coldRecord = Observable.fromArray(record);

        Consumer listener1 = song-> System.out.println("Listener 1 " + song);
        Consumer listener2 = song-> System.out.println("Listener 2 " + song);
        Consumer listener3 = song-> System.out.println("Listener 3 " + song);
        Consumer listener4 = song-> System.out.println("Listener 4 " + song);

        coldRecord.subscribe(listener1);
        coldRecord.subscribe(listener2);
        System.out.println(System.currentTimeMillis());
        Thread.sleep(2000);
        System.out.println(System.currentTimeMillis());
        coldRecord.subscribe(listener3);
        coldRecord.subscribe(listener4);
    }
}

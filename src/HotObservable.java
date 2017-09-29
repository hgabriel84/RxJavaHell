import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import java.util.concurrent.TimeUnit;

/**
 * Created by hgabriel on 9/27/17.
 */
public class HotObservable {

    public static void main(String[] args) throws InterruptedException {

        Observable<Long> record = Observable.interval(1, TimeUnit.SECONDS);
        ConnectableObservable<Long> hotRecord = record.publish();
        hotRecord.connect();

        Consumer listener1 = song-> System.out.println("Listener 1 " + song);
        Consumer listener2 = song-> System.out.println("Listener 2 " + song);
        Consumer listener3 = song-> System.out.println("Listener 3 " + song);
        Consumer listener4 = song-> System.out.println("Listener 4 " + song);

        Thread.sleep(2000);
        hotRecord.subscribe(listener1);
        Thread.sleep(1000);
        hotRecord.subscribe(listener2);
        Thread.sleep(4000);
        hotRecord.subscribe(listener3);
        hotRecord.subscribe(listener4);

        while (true);
    }

}

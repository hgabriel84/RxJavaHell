import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by hgabriel on 9/27/17.
 */
public class HotVsColdObservable {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Observable<Integer> just = Observable.create(
                source -> {
                    Observable.interval(1000, TimeUnit.MILLISECONDS)
                            .subscribe(aLong -> {
                                int value = random.nextInt();
                                System.out.println("Emitted data: " + value);
                                source.onNext(value);
                            });
                }
        ); // Simple same Observable which we are using only I added a one thing now this will produce data after every one second.
        ConnectableObservable<Integer> publish = just.publish();
        publish.connect();

        Thread.sleep(2000); // Hot observable start emitting data and our new subscribers will subscribe after 2 second.
        publish.subscribe(s -> System.out.println(s));
        publish.subscribe(s -> System.out.println(s));

        while (true);
    }
}

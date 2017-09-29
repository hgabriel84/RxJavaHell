import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by hgabriel on 9/27/17.
 */

public class ReplayObservable {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        Observable<Integer> just = Observable.create(
                source -> {
                    Observable.interval(500, TimeUnit.MILLISECONDS)
                            .subscribe(aLong -> {
                                int value = random.nextInt();
                                System.out.println("Emitted data: " + value);
                                source.onNext(value);
                            });
                }
        );
        ConnectableObservable<Integer> publish = just.replay();
        publish.connect();

        Thread.sleep(2000);
        publish.subscribe(s -> System.out.println("Subscriber 1: "+s));
        publish.subscribe(s -> System.out.println("Subscriber 2: "+s));

        while (true);

    }

}

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * Created by hgabriel on 9/27/17.
 */

public class EmitOnlyWhenSubscribedObserver {

    public static void main(String[] args) throws InterruptedException {

        Observable<Long> observable = Observable.interval(500, TimeUnit.MILLISECONDS).publish().refCount();

        Consumer<Long > firstSubscriber = s -> System.out.println("Subscriber 1: "+s);
        Consumer<Long > secondSubscriber = s -> System.out.println("Subscriber 2: "+s);

        Disposable subscribe1 = observable.subscribe(firstSubscriber);
        Disposable subscribe2 = observable.subscribe(secondSubscriber);

        Thread.sleep(2000);
        subscribe1.dispose();
        Thread.sleep(2000);
        subscribe2.dispose();

        Consumer<Long > thirdSubscriber = s -> System.out.println("Subscriber 3: "+s);
        Disposable subscribe3 = observable.subscribe(thirdSubscriber);

        Thread.sleep(2000);
        subscribe3.dispose();

        while (true);
    }

}

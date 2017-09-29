import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hgabriel on 9/27/17.
 */
public class ColdIntoHotObservable {

    public static void main(String[] args) throws InterruptedException {

        Consumer consumer1 = count-> System.out.println("Consumer 1 " + count);
        Consumer consumer2 = count-> System.out.println("Consumer 2 " + count);

        List<Integer> integers = new ArrayList<>();
        Observable.range(0, 10000)
                .subscribe(count -> integers.add(count));

        Observable<List<Integer>> listObservable = Observable.fromArray(integers);
        ConnectableObservable connectableObservable = listObservable.publish();
        connectableObservable.subscribe(consumer1);
        connectableObservable.subscribe(consumer2);
        connectableObservable.connect();
    }

}

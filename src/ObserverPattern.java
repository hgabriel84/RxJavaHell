import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hgabriel on 9/23/17.
 */
public class ObserverPattern {
    public static void main(String[] args) throws InterruptedException {
        currentDateTime();
        Data data = new Data();
        data.subscribe(observer);

        Thread.sleep(4000);
        currentDateTime();
        data.add("E");
        currentDateTime();
        data.add("F");

        data.unSubscribe(observer);
    }

    private interface Observable {
        void subscribe(Observer observer);
        void unSubscribe(Observer observer);
        void notifyEveryone();
    }

    private interface Observer {
        void dataChanged(List data);
    }

    private static class Data implements Observable {

        private List<Observer> observers = new ArrayList<>();
        private List<String> data = new ArrayList<>();

        @Override
        public void subscribe(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void unSubscribe(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyEveryone() {
            for (Observer observer : observers) {
                observer.dataChanged(data);
            }
        }

        public Data() {
            data.add("A");
            data.add("B");
            data.add("C");
            data.add("D");
            iterateOnData(data);
        }

        void add(String object) {
            data.add(object);
            notifyEveryone();
        }
    }

    private static Observer observer = new Observer() {
        @Override
        public void dataChanged(List data) {
            iterateOnData(data);
        }
    };

    private static void iterateOnData(List data) {
        Iterator iterator = data.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void currentDateTime() {
        System.out.println(new Date(System.currentTimeMillis()).toString());
    }
}

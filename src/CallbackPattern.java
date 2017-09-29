import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hgabriel on 9/23/17.
 */
public class CallbackPattern {
    public static void main(String[] args) throws InterruptedException {
        currentDateTime();
        Data data = new Data(callback);

        Thread.sleep(4000);
        currentDateTime();
        data.add("E");
        currentDateTime();
        data.add("F");
    }

    private static class Data {

        private interface Callback {
            void dataChanged(List data);
        }

        private List<String> data = new ArrayList<>();
        private Callback callback;

        public Data(Callback callback) {
            this.callback = callback;
            data.add("A");
            data.add("B");
            data.add("C");
            data.add("D");
            iterateOnData(data);
        }

        void add(String object) {
            data.add(object);
            callback.dataChanged(data);
        }
    }

    private static Data.Callback callback = new Data.Callback() {
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

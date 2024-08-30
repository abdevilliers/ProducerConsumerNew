import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AtomicBoolean produce = new AtomicBoolean(true);
            SharedResource sharedResource = new SharedResource(10,produce);
            //AtomicBoolean produce = new AtomicBoolean(true);
            int lim = 10;
            for (int i = 0; i < lim; i++) {
                int finalI = i;
                new Thread(() -> {


                        sharedResource.addItem(finalI);


                }).start();
            }
            for (int i = 0; i < lim; i++) {
                int finalI = i;
                new Thread(() -> {


                        sharedResource.consumeItem();


                }).start();
            }


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }
}
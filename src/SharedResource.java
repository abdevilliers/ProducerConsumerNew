import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class SharedResource {
    private List<Integer> buffer;
    private int maxSize;
    private AtomicBoolean produce;
    public SharedResource(int n,AtomicBoolean produce){
        buffer = new ArrayList(n);
        maxSize = n;
        this.produce = produce;
    }
    public synchronized void addItem(Integer item){
        try {
            while (buffer.size() == maxSize) {
                //sleep(5000);
                wait();
            }
            while(this.produce.get() == false){
                wait();
            }
            System.out.println(item+" added by Thread:"+Thread.currentThread().getName());
            buffer.add(item);
            produce.set(false);
            notifyAll();
        }
        catch(InterruptedException exc){

        }
    }
    public synchronized void consumeItem(){
        try{
            while(buffer.size()==0){
                wait();
            }
            while(produce.get()==true){
                wait();
            }
            Integer consumedItem = buffer.get(0);
            buffer.remove(0);
            produce.set(true);
            System.out.println("Item:"+consumedItem+" has been consumed by Thread"+Thread.currentThread().getName());
            notifyAll();
        }
        catch(InterruptedException exc){

        }
    }
}

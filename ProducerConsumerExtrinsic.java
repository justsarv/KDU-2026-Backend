
import java.util.ArrayDeque;
import java.util.Deque;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.concurrent.*;

class MessageQueue{
    public static final MessageQueue mq=new MessageQueue();
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition condition=lock.newCondition();
    Deque<String>dq=new ArrayDeque<String>();
        
     public void  put(String message){
        lock.lock();
        dq.addFirst(message);
        condition.signalAll();
        lock.unlock();
    } 
     String take(){
        lock.lock();
        while(dq.isEmpty()){
            try{condition.await();}
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        String ReturnValue =dq.getFirst();
        dq.removeFirst();
        lock.unlock();
        return ReturnValue;
    }
}




class ProducerConsumerExtrinsic {
    public static void main(String[] args) throws Exception {

        ExecutorService producers = Executors.newFixedThreadPool(3);
        ExecutorService consumers = Executors.newFixedThreadPool(3);

        Callable<List<String>> producerTask = () -> {
            List<String> produced = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                String msg = "This message was inserted at " + LocalTime.now();
                MessageQueue.mq.put(msg);
                produced.add(msg);
                System.out.println(msg);
            }
            return produced;
        };

        Callable<List<String>> consumerTask = () -> {
            List<String> consumed = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                String msg = MessageQueue.mq.take();
                consumed.add(msg);
                System.out.println(msg + " and read by consumer");
            }
            return consumed;
        };

        List<Future<List<String>>> pf = new ArrayList<>();
        List<Future<List<String>>> cf = new ArrayList<>();

        for (int i = 0; i < 3; i++) pf.add(producers.submit(producerTask));
        for (int i = 0; i < 3; i++) cf.add(consumers.submit(consumerTask));

        // collect results
        List<String> allProduced = new ArrayList<>();
        for (Future<List<String>> f : pf) allProduced.addAll(f.get());

        List<String> allConsumed = new ArrayList<>();
        for (Future<List<String>> f : cf) allConsumed.addAll(f.get());

        System.out.println("Produced: " + allProduced.size());
        System.out.println("Consumed: " + allConsumed.size());

        producers.shutdown();
        consumers.shutdown();
    }
}

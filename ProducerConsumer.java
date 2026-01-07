
import java.util.ArrayDeque;
import java.util.Deque;
import java.time.LocalTime;
class MessageQueue{
    public static final MessageQueue mq=new MessageQueue();
    
    Deque<String>dq=new ArrayDeque<String>();
        
    synchronized public void  put(String message){
        dq.addFirst(message);
        notifyAll();
    } 
    synchronized String  take(){
        while(dq.isEmpty()){
            try{wait();}
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        String ReturnValue =dq.getFirst();
        dq.removeFirst();
        return ReturnValue;
    }
}

class MessageSender implements Runnable{
    public void run(){
        for(int i=0;i<5;i++){
            String toBeInserted="This message was inserted at " + LocalTime.now();
            MessageQueue.mq.put(toBeInserted);
            System.out.println(toBeInserted);
        }
    }
}

class MessageConsumer implements Runnable{
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(MessageQueue.mq.take() + "and read by consumer");
        }
    }
}



class ProducerConsumer{
    public static void main(String[] args) {
        Thread producer1=new Thread(new MessageSender());
        Thread producer2=new Thread(new MessageSender());
        Thread producer3=new Thread(new MessageSender());

        Thread consumer1=new Thread(new MessageConsumer());
        Thread consumer2=new Thread(new MessageConsumer());
        Thread consumer3=new Thread(new MessageConsumer());

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}
import java.util.concurrent.*;
class CallableFuture{

    public static void main(String[] args) throws Exception{
        int N=10;
        Callable<Integer> SumToN=() ->{
            Integer sum=0;
            for(int i=1;i<=N;i++){
                sum+=i;
            }
            return sum;
        };
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future=service.submit(SumToN);
        Integer result=future.get();
        System.out.println(result);
    }
}
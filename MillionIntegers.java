import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class MillionIntegers {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(1_000_000);

        for (int i = 1; i <= 1_000_000; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        Integer million= list.stream().mapToInt(Integer::intValue).sum();
        long end = System.currentTimeMillis();

        System.out.println("The time for normal/sequential streams is:" + (end-start));

       long startP = System.currentTimeMillis();
        Integer millionParallel=list.parallelStream().mapToInt(Integer::intValue).sum();
        long endP = System.currentTimeMillis();

        System.out.println("The time for PARALLEL streams is:" + (endP-startP));
    }
}

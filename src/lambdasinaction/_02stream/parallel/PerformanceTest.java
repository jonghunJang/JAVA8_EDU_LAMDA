package lambdasinaction._02stream.parallel;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vega on 2017-06-23.
 */

public class PerformanceTest {
    public static void main(String[] args) {
        System.out.println(Math.pow(10,9));
        System.out.println( 1000000 / Math.pow(10,9));


        //Calculating square root of even numbers from 1 to N
        int min = 1;
        int max = 1000000;

        List<Integer> sourceList = new ArrayList<>();
        for (int i = min; i < max; i++) {
            sourceList.add(i);
        }
        /*
		Collections: Elapsed time:	 130075899 ns 	(0.130076 seconds)
		Streams: Elapsed time:		 89526301 ns 	(0.089526 seconds)
		Parallel streams: Elapsed time:	 71302491 ns 	(0.071302 seconds)
        */
        //List<Double> result = new LinkedList<>();

        /*
		Collections: Elapsed time:	 62070693 ns 	(0.062071 seconds)
		Streams: Elapsed time:		 33113643 ns 	(0.033114 seconds)
		Parallel streams: Elapsed time:	 87378458 ns 	(0.087378 seconds)
         */
        List<Double> result = new ArrayList<>();


        //Collections approach
        long t0 = System.nanoTime();
        long elapsed = 0;

        for (Integer i : sourceList) {
            if(i % 2 == 0){
                //제곱근 계산
                result.add(Math.sqrt(i));
            }
        }

        elapsed = System.nanoTime() - t0;
        // %d : 정수, %f : 실수
        System.out.printf("Collections: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


        //Stream approach
        Stream<Integer> stream = sourceList.stream();
        t0 = System.nanoTime();
        result = stream.filter(i -> i%2 == 0).map(i -> Math.sqrt(i)).collect(Collectors.toList());
        elapsed = System.nanoTime() - t0;
        System.out.printf("Streams: Elapsed time:\t\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


        //Parallel stream approach
        stream = sourceList.stream().parallel();
        t0 = System.nanoTime();
        result = stream.filter(i -> i%2 == 0).map(i -> Math.sqrt(i)).collect(Collectors.toList());
        elapsed = System.nanoTime() - t0;
        System.out.printf("Parallel streams: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));
    }
}

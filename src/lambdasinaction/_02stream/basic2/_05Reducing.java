package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import lambdasinaction._02stream.basic1.Dish;


public class _05Reducing {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    	
        //reduce - - reduce를 사용하여 sum 을 구하는 방법
        int sum = numbers.stream().reduce(0, (a,b) -> a+b);
        System.out.println(sum);
        sum = numbers.stream().reduce(0, (a,b) -> Integer.sum(a, b));
        sum = numbers.stream().reduce(0, Integer::sum);
        
        Optional<Integer> optional = numbers.stream().reduce(Integer::sum);
        optional.ifPresent(System.out::println);
        
        //reduce를 사용하여 최소값 구하는 방법
        Optional<Integer> minx = numbers.stream().reduce(Integer::min);
        minx.ifPresent(System.out::println);
        //reduce를 사용하여 최대값 구하는 방법
        Optional<Integer> maxx = numbers.stream().reduce(Integer::max);
        maxx.ifPresent(System.out::println);
        //칼로리 합계를 구하는 여러가지 방법

        //1. reduce 함수를 직접 구현
        int sumCalory = Dish.menu.stream().map(Dish::getCalories).reduce(0, (d1,d2)->d1+d2);
        System.out.println(sumCalory);
        
        //2. reduce 함수에서 Integer.sum 호출
        sumCalory = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(sumCalory);
        
        //3. reduce() 사용하지 않고 mapToInt(), sum() 사용
        sumCalory = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        
        System.out.println(sumCalory);
        
        //4. 집계함수를 한꺼번에 제공하는 summaryStatistics() 사용
        System.out.println(IntStream.rangeClosed(1, 100).summaryStatistics());
        
    }
}

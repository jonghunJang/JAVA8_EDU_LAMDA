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
    	
        //reduce - - reduce�� ����Ͽ� sum �� ���ϴ� ���
        int sum = numbers.stream().reduce(0, (a,b) -> a+b);
        System.out.println(sum);
        sum = numbers.stream().reduce(0, (a,b) -> Integer.sum(a, b));
        sum = numbers.stream().reduce(0, Integer::sum);
        
        Optional<Integer> optional = numbers.stream().reduce(Integer::sum);
        optional.ifPresent(System.out::println);
        
        //reduce�� ����Ͽ� �ּҰ� ���ϴ� ���
        Optional<Integer> minx = numbers.stream().reduce(Integer::min);
        minx.ifPresent(System.out::println);
        //reduce�� ����Ͽ� �ִ밪 ���ϴ� ���
        Optional<Integer> maxx = numbers.stream().reduce(Integer::max);
        maxx.ifPresent(System.out::println);
        //Į�θ� �հ踦 ���ϴ� �������� ���

        //1. reduce �Լ��� ���� ����
        int sumCalory = Dish.menu.stream().map(Dish::getCalories).reduce(0, (d1,d2)->d1+d2);
        System.out.println(sumCalory);
        
        //2. reduce �Լ����� Integer.sum ȣ��
        sumCalory = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(sumCalory);
        
        //3. reduce() ������� �ʰ� mapToInt(), sum() ���
        sumCalory = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        
        System.out.println(sumCalory);
        
        //4. �����Լ��� �Ѳ����� �����ϴ� summaryStatistics() ���
        System.out.println(IntStream.rangeClosed(1, 100).summaryStatistics());
        
    }
}

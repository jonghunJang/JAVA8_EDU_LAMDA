package lambdasinaction._02stream.basic2;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import lambdasinaction._02stream.basic1.Dish;

public class _03Mapping {

    public static void main(String...args){

        //1. map -Dish의 name 목록만
    	List<String> dishNames = Dish.menu
    									  .stream()
    									  .map(Dish::getName)
    									  .collect(toList());
    	dishNames.forEach(System.out::println);

    	//2. map -Calory 합계
    	int sumValue = Dish.menu.stream()
    						.map(Dish::getCalories)
    						.reduce(0, (prev,curr) -> prev + curr);
    	System.out.println(sumValue);
    	
        // map 
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        //2. map - 중복된 문자 제거한 word 리스트
        words.stream().map(w -> w.split(""))
        					.distinct()
        					.forEach(System.out::println);
        

        //3.flatMap  - 중복된 문자 제거가 word 리스트
        words.stream().map(w->w.split(""))
//        					.flatMap(strs -> Arrays.stream(strs))
        					.flatMap(Arrays::stream)
        					.distinct()
        					.forEach(System.out::print);
        words.stream().flatMap(w -> Arrays.stream(w.split(""))).distinct().forEach(System.out::print);


        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}

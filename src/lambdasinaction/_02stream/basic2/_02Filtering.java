package lambdasinaction._02stream.basic2;
import static java.util.stream.Collectors.*;
import java.util.Arrays;
import java.util.List;

import lambdasinaction._02stream.basic1.Dish;

public class _02Filtering {

    public static void main(String...args){

       //  1. Filtering with predicate ( isVegeterian() )
    	List<Dish> vegeList = Dish.menu
    								.stream()
    								.filter(Dish::isVegetarian)
    								.collect(toList());


        // 2. Filtering unique elements
    	List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    	
    	

        //3. Truncating 3 stream ( d.getCalories() > 300 )
    	List<Dish> dishesLimit3 = Dish.menu.stream()
    									.filter(dish -> dish.getCalories() > 300)
    									.limit(3)
    									.collect(toList());
    	System.out.println("=====dishesLimit3=======");
    	dishesLimit3.forEach(System.out::println);
    	

        //4. Skipping elements
    	List<Dish> dishesSkip2 = Dish.menu.stream().skip(2).collect(toList());
    	System.out.println("=====dishesSkip2=======");
    	dishesSkip2.forEach(System.out::println);



    }
}

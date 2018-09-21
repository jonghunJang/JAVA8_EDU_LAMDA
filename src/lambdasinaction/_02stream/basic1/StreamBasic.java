package lambdasinaction._02stream.basic1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
 
public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu)
        .forEach(list -> System.out.println(list));

        getLowCaloricDishesNamesInJava8(Dish.menu)
        .forEach(System.out::println);
        
        System.out.println(getGroupingMenu(Dish.menu));
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
//        return dishes.stream()
//        			.filter(dish -> dish.getCalories() <= 400)
//        			.sorted(Comparator.comparing(dish -> dish.getCalories()))
//        			.map(dish -> dish.getName())
//        			.collect(Collectors.toList())
//        			.subList(0, 3);
        return dishes.stream()
    			.filter(dish -> dish.getCalories() <= 400)
    			.sorted(comparing(Dish::getCalories))
    			.map(Dish::getName)
    			.collect(toList())
    			.subList(0, 3);	
//    	Predicate<? super Dish> predicate = (dish) -> dish.getCalories() >= 100;
//        return dishes.stream()
//    			.filter(predicate).map(dish -> dish.getName()).collect(Collectors.toList());	
    }
    
    //400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>>  getGroupingMenu(List<Dish> dishes){
    	return dishes.stream()
    			.collect(groupingBy(dish -> {
    				if(dish.getCalories() <= 400) {
    					return "Diet";
    				}else {
    					return "Normal";
    				}
    			}));
 
    }
    
 
    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCaloryDish (List<Dish> dishes) {
    	return dishes.stream()
    			.max(comparing(Dish::getCalories))
    			.get();
    	
    	
    }
}

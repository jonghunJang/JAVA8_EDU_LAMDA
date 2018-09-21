package lambdasinaction._01lambda.basic2;

import java.util.*;
import java.util.function.Predicate;

public class FilteringApples{

    public static void main(String ... args){

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));	

        // ���ٽ� ��� [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        filterApples(inventory, apple -> apple.getColor().equals("green"));
        
        // Method Reference ��� [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        filterApples(inventory, FilteringApples::isGreenApple);
        
        // ���ٽ� ���[Apple{color='green', weight=155}]
        filterApples(inventory, apple -> apple.getWeight() > 150);
        
        // Method Reference ��� [Apple{color='green', weight=155}]
        filterApples(inventory, FilteringApples::isHeavyApple);
        
        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || 
                                                                       "brown".equals(a.getColor()));
        System.out.println(weirdApples);
        
        Predicate<Apple> redApples = apple -> "red".equals(apple.getColor());
        Predicate<Apple> notRedAppless = redApples.negate();
        Predicate<Apple> redHeavyApples = redApples.and(a -> a.getWeight() > 100);
        
        System.out.println("redApples : " +filterApples(inventory, redApples));
        System.out.println("notRedAppless : " +filterApples(inventory, notRedAppless));
        System.out.println("redHeavyApples : " +filterApples(inventory, redHeavyApples));
        
        System.out.println("redApples : " +filterApples(inventory, apple -> "red".equals(apple.getColor())));
//        System.out.println("notRedAppless : " +filterApples(inventory, notRedAppless));
//        System.out.println("redHeavyApples : " +filterApples(inventory, redHeavyApples));
    }


    public static boolean isGreenApple(Apple apple) {
        return apple.getColor().equals("green"); 
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
			if(p.test(apple)) {
				result.add(apple);
			}
		}
    	return result;
    }       
}

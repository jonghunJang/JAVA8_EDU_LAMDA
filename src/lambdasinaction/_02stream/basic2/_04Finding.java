package lambdasinaction._02stream.basic2;
import java.util.Optional;

import lambdasinaction._02stream.basic1.Dish;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return Dish.menu.stream().anyMatch(Dish::isVegetarian);
    }
    //2.allMatch
    private static boolean isHealthyMenu(){

    	//return Dish.menu.stream().allMatch(Dish::isVegetarian);
    	return Dish.menu.stream().allMatch(dish->dish.getCalories()<=800);
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){

//    	return Dish.menu.stream().noneMatch(Dish::isVegetarian);
    	return Dish.menu.stream().noneMatch(kkk -> kkk.getCalories() > 800);
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){

    	return Dish.menu.stream().findAny();
    }
    
}

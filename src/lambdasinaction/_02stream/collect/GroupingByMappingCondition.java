package lambdasinaction._02stream.collect;

import java.util.function.Function;

import lambdasinaction._02stream.collect._04GroupingDishes.CaloricLevel;

public class GroupingByMappingCondition implements Function<Dish,  CaloricLevel> {

	@Override
	public CaloricLevel apply(Dish dish) {
		if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
	}
}

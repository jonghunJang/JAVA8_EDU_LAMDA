package lambdasinaction._02stream.collect;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;
import static lambdasinaction._02stream.collect.Dish.menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import lambdasinaction._02stream.collect.Dish.Type;

public class _04GroupingDishes {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

//    private static Function<T,  v>
    //1. type별 그룹핑
    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
//        return menu.stream().collect(groupingBy(Dish::getType));
//    	Function<T, R> fc = new Function<T, R>() {
//    		
//		};
//    	return menu.stream().collect(groupingBy(Dish::getType));
    	Function<Dish,  Dish.Type> function = new Function<Dish, Dish.Type>() {
    		@Override
    		public Dish.Type apply(Dish d) {
    			return d.getType();
    		}
		};
    	
    	return menu.stream().collect(groupingBy(function));
    }
    //2. 칼로리별 그룹핑
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream().collect(groupingBy(d->{
        	if(d.getCalories()<=400) return CaloricLevel.DIET;
        	else if (d.getCalories()<=700) return CaloricLevel.NORMAL;
        	else return CaloricLevel.FAT;
        }));
    }
    //3. type별로 그룹핑 후에 다시 칼로리별로 그룹핑
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream()
        		.collect(groupingBy(Dish::getType,
        					groupingBy(d->{
        			        	if(d.getCalories()<=400) return CaloricLevel.DIET;
        			        	else if (d.getCalories()<=700) return CaloricLevel.NORMAL;
        			        	else return CaloricLevel.FAT;
        					}))
        				)
        		;

    }
    private static CaloricLevel lamdatest(Dish d) {
    	if(d.getCalories()<=400) return CaloricLevel.DIET;
    	else if (d.getCalories()<=700) return CaloricLevel.NORMAL;
    	else return CaloricLevel.FAT;
    }
    
    //4. type별 갯수 카운팅
    private static Map<Dish.Type, Long> countDishesInGroups() {
        return null;
    }
    //5. type별 그룹에서 가장 칼로리가 높은 Dish 찾기
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return null;
    }
    //5.1 type별 그룹에서 가장 칼로리가 높은 Dish 찾기 - collectingAndThen() 사용
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return null;
        		    
    }

    //6. type별로 그룹핑하여 칼로리의 합계 내기
    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return null;
    }

//    private Function<Dish,  CaloricLevel> groupingByMappingCondition(){
//		@Override
//		public CaloricLevel apply(Dish dish) {
//			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//      else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//      else return CaloricLevel.FAT;
//		}
//    }
    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
    	
      return menu.stream().collect(
										      groupingBy(Dish::getType, mapping(
												              dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
												              else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
												              else return CaloricLevel.FAT; },
												              toSet() 
												              )
										    		  )
										      );
		
      
      
      
      
      
      //1. 람다식 별도 인자로 받아서 생성
//  	Function<Dish,  CaloricLevel> groupingByMappingCondition =  
//		dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//					else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//					else return CaloricLevel.FAT; 
//		};
//		 return menu.stream().collect(
									//      groupingBy(Dish::getType, mapping(groupingByMappingCondition,
									//              toSet() )));
      
      
    //2. 람다식 익명함수로 변환 후 호출     
//      return menu.stream().collect(
//		      groupingBy(Dish::getType, mapping(
//											    		  		new Function<Dish, CaloricLevel>() {
//											    		    		@Override
//											    		    		public CaloricLevel apply(Dish dish) {
//											    		    			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//												    			          else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//												    			          else return CaloricLevel.FAT;
//											    		    		}
//											    				}
//										    		  			,toSet() 
//				         									)
//		    		  )
//		      );
      
      //3. 익명함수 선언 후 호출   
  //    	Function<Dish,  CaloricLevel> groupingByMappingCondition = new Function<Dish, CaloricLevel>() {
//    		@Override
//    		public CaloricLevel apply(Dish dish) {
//    			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//	          else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//	          else return CaloricLevel.FAT;
//    		}
//		};
    	
    	//4. Funcion 인터페이스 구현 후 해당 메소드 호출
//    	GroupingByMappingCondition groupingByMappingCondition = new GroupingByMappingCondition();
//		 Map<Dish.Type, Set<CaloricLevel>> returnMap =  menu.stream().collect(
//	                groupingBy(Dish::getType, mapping(groupingByMappingCondition,
//	                        toSet() )));
//        return returnMap;
    }
}

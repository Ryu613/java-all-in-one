package example.p121.groupingBy;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Starter {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        //自定义分组,按照卡路里热量区间划分
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if(dish.getCalories() <= 700) return CaloricLevel.NORNAL;
            else return CaloricLevel.FAT;
        }));
        //多级分组,先按照热量区间分组，再按照类型分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORNAL;
                            else return CaloricLevel.FAT;
                        }))
                );
        //按照菜的类型分组，选取其中热量最高的
        //如果菜的类型中没有相关元素，则不会有key
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
            .collect(groupingBy(
                Dish::getType,
                maxBy(Comparator.comparingInt(Dish::getCalories))
                )
            );
        //若想去掉Optional，可以使用collectingAndThen
        Map<Dish.Type, Dish> mostCaloricByTypeNoOptional = menu.stream()
                .collect(groupingBy(
                            Dish::getType,
                            collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                            )
                        )
                );
        //每种类型的菜，都有哪些热量等级
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(groupingBy(
                    Dish::getType,
                    mapping(
                        dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORNAL;
                            else return CaloricLevel.FAT;
                         },
                        toSet()
                        //如果需要对返回的Set类型做指定，用
                        // toCollection(HashSet::new)
                    )
                ));
        //分区
        //找到素食和非素食热量最高的菜
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(
                        partitioningBy(
                                Dish::isVegerarian,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                )
                        )
                );
    }
}

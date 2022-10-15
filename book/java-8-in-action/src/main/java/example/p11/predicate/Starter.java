package example.p11.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Starter {
    public static void main(String[] args) {
        Apple a1 = new Apple(ColorEnum.GREEN,130);
        Apple a2 = new Apple(ColorEnum.RED, 160);
        Apple a3 = new Apple(ColorEnum.RED, 110);
        Apple a4 = new Apple(ColorEnum.GREEN, 170);
        List<Apple> appleList = Stream.of(a1, a2, a3, a4).collect(Collectors.toList());
        //写法1:filter中传方法引用
        List<Apple> appleList1 = Stream.of(a1, a2, a3, a4).filter(Apple::filterHeavyApple).collect(Collectors.toList());
        List<Apple> appleList2 = Stream.of(a1, a2, a3, a4).filter(Apple::filterGreenApple).collect(Collectors.toList());
        //写法2:方法引用用Predicate显式定义
        List<Apple> heavyList = filter(appleList, Apple::filterHeavyApple);
        List<Apple> greenList = filter(appleList, Apple::filterGreenApple);
    }

    static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}

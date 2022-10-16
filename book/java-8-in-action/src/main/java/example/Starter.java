package example;

import example.p11.predicate.Apple;
import example.p11.predicate.AppleTool;
import example.p11.predicate.ColorEnum;
import example.p26.prettyprint.PrintWeightColorPrintPredicate;
import example.p26.prettyprint.PrintWeightPredicate;
import example.p31.predicate.GeneralFilter;

import java.util.List;
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
        List<Apple> heavyList = AppleTool.filter(appleList, Apple::filterHeavyApple);
        List<Apple> greenList = AppleTool.filter(appleList, Apple::filterGreenApple);
        //p26
        AppleTool.prettyPrintApple(appleList, new PrintWeightColorPrintPredicate());
        AppleTool.prettyPrintApple(appleList, new PrintWeightPredicate());
        //lambda
        System.out.println("=============\nlambda:\r");
        AppleTool.prettyPrintApple(appleList, (Apple apple) -> ColorEnum.RED.equals(apple.getColor()));
        //p31 general filter
        List<Apple> greenApples = GeneralFilter.filter(appleList, (apple) -> ColorEnum.GREEN.equals(apple.getColor()));
    }
}

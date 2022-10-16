package example.p11.predicate;

import example.p23.predicate.ApplePredicate;
import example.p26.prettyprint.PrintPredicate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleTool {
    public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p ) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, PrintPredicate p) {
        for(Apple apple : inventory) {
            String output = p.prettyPrint(apple);
            System.out.println(output);
        }
    }

    public static void prettyPrintApple(List<Apple> inventory, Predicate<Apple> p) {
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                String output = "color: " + apple.getColor() + ", weight: " + apple.getWeight();
                System.out.println(output);
            }
        }
    }

}

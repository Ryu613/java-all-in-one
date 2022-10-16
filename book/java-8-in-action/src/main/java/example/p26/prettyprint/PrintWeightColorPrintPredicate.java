package example.p26.prettyprint;

import example.p11.predicate.Apple;

public class PrintWeightColorPrintPredicate implements PrintPredicate {
    @Override
    public String prettyPrint(Apple apple) {
        return "color: " + apple.getColor() + ", weight: " + apple.getWeight();
    }
}

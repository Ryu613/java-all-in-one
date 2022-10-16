package example.p26.prettyprint;

import example.p11.predicate.Apple;

public class PrintWeightPredicate implements PrintPredicate {
    @Override
    public String prettyPrint(Apple apple) {
        return "weight: " + apple.getWeight();
    }
}

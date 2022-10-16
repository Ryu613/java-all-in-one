package example.p23.predicate;

import example.p11.predicate.Apple;
import example.p11.predicate.ColorEnum;

public class AppleGreenColorPredicate implements  ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return ColorEnum.GREEN.equals(apple.getColor());
    }
}

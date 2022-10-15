package example.p11.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Apple {
    private ColorEnum color;
    private Integer weight;

    public Apple(ColorEnum color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static boolean filterGreenApple(Apple apple) {
        return ColorEnum.GREEN.equals(apple.getColor());
    }

    public static boolean filterHeavyApple(Apple apple) {
        return apple.weight > 150;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", weight=" + weight +
                '}';
    }
}

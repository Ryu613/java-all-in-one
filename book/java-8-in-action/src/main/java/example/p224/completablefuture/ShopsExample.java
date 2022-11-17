package example.p224.completablefuture;

import java.util.Arrays;
import java.util.List;

public class ShopsExample {
    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));
    }
}

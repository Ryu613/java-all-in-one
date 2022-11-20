package example.p224.completablefuture;

public class Discount {
    public enum Code {
        NONE(0),SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);

        public final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(),
                quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        AsyncSimulator.delay();
        return format(price * (100 - code.percentage) / 100);
    }

    private static double format(double number) {
        return number;
    }
}

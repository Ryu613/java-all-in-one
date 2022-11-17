package example.p224.completablefuture;

import java.util.concurrent.Future;

public class AsyncExample {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " ms");
        //这里可以做点别的，不用等待结果返回
        try {
            double price = futurePrice.get();
            System.out.printf("price is %.2f%n", price);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " ms");
    }
}

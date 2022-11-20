package example.p224.completablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static example.p224.completablefuture.AsyncSimulator.delay;

public class Shop {
    private String name;
    public static final Random random = new Random();

    public Shop(String name){
        this.name = name;
    }

    //同步方法，由于delay()会阻塞
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPrice2(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                //出结果时，设置返回值
                futurePrice.complete(price);
            } catch(Exception e) {
                futurePrice.completeExceptionally(e);
            }

        }).start();
        //如果计算尚未结束，也无需等待，直接返回Future对象
        return futurePrice;
    }

    //使用CompletableFuture自带的工厂方法
    public Future<Double> getPriceAsyncFromFactory(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}

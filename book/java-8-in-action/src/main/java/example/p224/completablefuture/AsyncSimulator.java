package example.p224.completablefuture;

public class AsyncSimulator {

    public static void delay() {
        try{
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

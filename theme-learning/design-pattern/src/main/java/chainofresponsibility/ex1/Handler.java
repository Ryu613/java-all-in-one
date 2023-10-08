package chainofresponsibility.ex1;

public interface Handler {
    void setNextHandler(Handler nextHandler);

    //具体执行方法
    boolean handle(int amount);
}

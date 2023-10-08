package state.ex1;

public class OrderState implements State {
    @Override
    public void doAction(StateContext ctx) {
        System.out.println("商品已经接单，处理中...");
    }
}

package state.ex1;

public class ProductOutState implements State {
    @Override
    public void doAction(StateContext ctx) {
        System.out.println("商品已经出库");
    }
}

package state.ex1;

/**
 * 参考自网络：秒懂设计模式系列
 */
public class StatePatternStarter {
    public static void main(String[] args) {
        StateContext ctx = new StateContext();
        //下单状态
        OrderState orderState = new OrderState();
        ctx.setState(orderState);
        ctx.doAction();
        //出货状态
        ProductOutState poState = new ProductOutState();
        ctx.setState(poState);
        ctx.doAction();
    }
}

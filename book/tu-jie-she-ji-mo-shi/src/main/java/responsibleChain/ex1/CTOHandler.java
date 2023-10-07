package responsibleChain.ex1;

public class CTOHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if(amount < 50000) {
            System.out.println("CTO: 同意");
            return true;
        }
        System.out.println(amount + "太贵了，无法批复!");
        return false;
    }
}

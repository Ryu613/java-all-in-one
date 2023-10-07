package responsibleChain.ex1;

public class ManagerHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if(amount < 10000) {
            System.out.println("经理: 不贵，可以同意");
            return true;
        }
        System.out.println(amount + "超出经理权限，请更高级领导批复");
        return nextHandler.handle(amount);
    }
}

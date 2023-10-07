package responsibleChain.ex1;

public class GroupHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if(amount < 2000) {
            System.out.println("组长: 小case,批了");
            return true;
        }
        System.out.println(amount + "超出小组长权限，请更高级领导批复");
        return nextHandler.handle(amount);
    }
}

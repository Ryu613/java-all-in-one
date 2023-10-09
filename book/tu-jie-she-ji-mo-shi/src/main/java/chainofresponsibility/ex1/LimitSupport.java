package chainofresponsibility.ex1;

public class LimitSupport extends Support {
    private int limit; // 可以解决编号小于limit的问题
    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() < limit) {
            return true;
        } else {
            return false;
        }
    }
}

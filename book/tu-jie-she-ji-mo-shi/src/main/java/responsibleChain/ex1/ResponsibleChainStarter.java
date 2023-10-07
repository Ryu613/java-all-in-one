package responsibleChain.ex1;

public class ResponsibleChainStarter {
    public static void main(String[] args) {
        GroupHandler leader = new GroupHandler();
        ManagerHandler manager = new ManagerHandler();
        CTOHandler cto = new CTOHandler();
        leader.setNextHandler(manager);
        manager.setNextHandler(cto);

        System.out.println("需要申请资金");
        if(leader.handle(60000)) {
            System.out.println("谢谢领导");
        } else {
            System.out.println("审批不通过!");
        }
    }
}

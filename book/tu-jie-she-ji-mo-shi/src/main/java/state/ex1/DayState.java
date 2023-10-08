package state.ex1;

/**
 * 白天状态类，用于处理白天情况下的操作行为
 */
public class DayState implements State {
    //单例化
    private static DayState singleton = new DayState();
    private DayState() {}
    public static State getInstance(){
        return singleton;
    }

    @Override
    public void doClock(Context ctx, int hour) {
        if(hour < 9 || hour >= 17) {
            ctx.changeState(NightState.getInstance());
        }
    }

    @Override
    public void doUse(Context ctx) {
        ctx.recordLog(" 使用金库(白天)");
    }

    @Override
    public void doAlarm(Context ctx) {
        ctx.callSecurityCenter(" 按下警铃(白天)");
    }

    @Override
    public void doPhone(Context ctx) {
        ctx.callSecurityCenter(" 正常通话(白天)");
    }

    public String toString() {
        return "[ 白天 ]";
    }
}

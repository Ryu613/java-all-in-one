package state.ex1;

public class NightState implements State {
    private static NightState singleton = new NightState();
    private NightState(){}
    public static State getInstance() {
        return singleton;
    }
    @Override
    public void doClock(Context ctx, int hour) {
        if(hour >= 9 && hour < 17) {
            ctx.changeState(DayState.getInstance());
        }
    }

    @Override
    public void doUse(Context ctx) {
        ctx.callSecurityCenter(" 紧急:晚上使用金库! ");
    }

    @Override
    public void doAlarm(Context ctx) {
        ctx.callSecurityCenter(" 按下警铃(晚上) ");
    }

    @Override
    public void doPhone(Context ctx) {
        ctx.recordLog(" 晚上的通话录音");
    }

    public String toString() {
        return "[ 晚上 ]";
    }
}

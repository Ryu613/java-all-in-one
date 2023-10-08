package state.ex1;

public interface State {
    void doClock(Context ctx, int hour); //设置时间
    void doUse(Context ctx); //使用金库
    void doAlarm(Context ctx); //按下警铃
    void doPhone(Context ctx); // 正常通话
}

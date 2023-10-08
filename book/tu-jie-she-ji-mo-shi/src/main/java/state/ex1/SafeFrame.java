package state.ex1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 金库界面
 */
public class SafeFrame extends Frame implements ActionListener, Context {
    private TextField textClock = new TextField(60); //显示当前时间
    private TextArea textScreen = new TextArea(10, 60); // 显示警报中心的记录
    private Button btnUse = new Button("使用金库 "); //使用金库按钮
    private Button btnAlarm = new Button("按下警铃"); //按下警铃按钮
    private Button btnPhone = new Button("正常通话"); //正常通话按钮
    private Button btnExit = new Button("结束"); //结束按钮

    private State state = DayState.getInstance(); //当前的状态

    public SafeFrame(String title) {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        //配置textClock
        add(textClock, BorderLayout.NORTH);
        textClock.setEditable(false);
        //配置textScreen
        add(textScreen, BorderLayout.CENTER);
        textScreen.setEditable(false);
        //为界面添加按钮
        Panel panel = new Panel();
        panel.add(btnUse);
        panel.add(btnAlarm);
        panel.add(btnPhone);
        panel.add(btnExit);
        //配置界面
        add(panel, BorderLayout.SOUTH);
        //显示
        pack();
        setVisible(true);
        //设置监视器
        btnUse.addActionListener(this);
        btnAlarm.addActionListener(this);
        btnPhone.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        if(e.getSource() == btnUse) {
            state.doUse(this);
        } else if(e.getSource() == btnAlarm) {
            state.doAlarm(this);
        } else if(e.getSource() == btnPhone) {
            state.doPhone(this);
        } else if(e.getSource() == btnExit) {
            System.exit(0);
        } else {
            System.out.println("?");
        }

    }

    @Override
    public void setClock(int hour) {
        String clockStr = "现在时间是 ";
        if(hour < 10) {
            clockStr += "0" + hour + ":00";
        } else {
            clockStr += hour + ":00";
        }
        System.out.println(clockStr);
        textClock.setText(clockStr);
        state.doClock(this, hour);
    }

    @Override
    public void changeState(State state) {
        System.out.println(" 从" + this.state + "状态变为了 " + state + "状态。");
        this.state = state;
    }

    @Override
    public void callSecurityCenter(String msg) {
        textScreen.append("call! " + msg + "\n");
    }

    @Override
    public void recordLog(String msg) {
        textScreen.append("record ... " + msg + "\n");
    }
}

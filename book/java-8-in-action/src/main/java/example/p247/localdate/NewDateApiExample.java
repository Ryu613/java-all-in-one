package example.p247.localdate;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class NewDateApiExample {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014,3,18);
        //2014
        int year = date.getYear();
        //March
        Month month = date.getMonth();
        //18
        int day = date.getDayOfMonth();
        //Tuesday
        DayOfWeek dow = date.getDayOfWeek();
        //31
        int len = date.lengthOfMonth();
        //false
        boolean leap = date.isLeapYear();
        //获取当前日期
        LocalDate today = LocalDate.now();
        //使用TemporalField
        int year1 = date.get(ChronoField.YEAR);
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);
        //LocalTime
        LocalTime time = LocalTime.of(13,45,20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        //LocalTime和LocalDate可以通过字符串解析获得，使用parse方法
        //也可以传一个DateTimeFormatter,来定义如何格式化一个日期或时间
        LocalDate date1 = LocalDate.parse("2014-03-18");
        LocalTime time1 = LocalTime.parse("13:45:20");
        //结合体LocalDateTime
        LocalDateTime dt1 = LocalDateTime.of(2014,Month.MARCH, 18,13,45,20);
        LocalDateTime dt2 = LocalDateTime.of(date,time);
        LocalDateTime dt3 = date.atTime(13,45,20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        //也可以转换到单个对象
        LocalDate date2 = dt1.toLocalDate();
        LocalTime time2 = dt1.toLocalTime();
        //Instant机器使用的日期时间对象
        Instant ins1 = Instant.ofEpochSecond(3,0);
        //2秒之后加一百万纳秒(1s)
        Instant ins2 = Instant.ofEpochSecond(2, 1_000_000_000);
        //两个LocalTime或Instant间
        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(dt1, dt2);
        Duration d3 = Duration.between(ins1, ins2);
        //使用Period处理日期之间的间隔
        Period tenDays = Period.between(LocalDate.of(2014,3,8),
                LocalDate.of(2014,3,18));
        //使用工厂方法创建
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes1 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays1 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2,6,1);
        //利用已创建好的时间日期对象创建副本
        LocalDate date11 = LocalDate.of(2014,3,18);
        LocalDate date22 = date1.withYear(2011);
        LocalDate date33 = date2.withDayOfMonth(25);
        LocalDate date44 = date33.with(ChronoField.MONTH_OF_YEAR,9);
        //取相对值创建副本
        LocalDate date55 = date11.plusWeeks(1);
        //以此类推
        //利用TemporalAdjuster做时间的复杂操作
       // 预定义好的TemporalAdjuster

     }
}

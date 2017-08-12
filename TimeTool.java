import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by chengs on 17-7-25.
 */
public class TimeTool {
    public static void main(String[] args) {
        System.out.println(getMondayDate());
        System.out.println(getNowTimestamp());
        System.out.println(getNeedDate(1));
        System.out.println(getNeedSection(12));
        System.out.println(add(getNeedDate(1),-7));
    }
    public static java.sql.Date getMondayDate(){
        java.util.Date time=new java.util.Date();
        Calendar cal= Calendar.getInstance();
        boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);//一周第一天是否为星期天
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);//获取周几
        if(isFirstSunday){//若一周第一天为星期天，则-1.
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }
        int delta=1-weekDay;
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_YEAR, delta);
        return new java.sql.Date((cal.getTime()).getTime());
    }

    public static Timestamp getNowTimestamp(){
        java.util.Date time=new java.util.Date();
        return new Timestamp(time.getTime());
    }
    public static java.sql.Date getNeedDate(int timeIndex){
        java.util.Date time=new java.util.Date();
        Calendar cal= Calendar.getInstance();
        boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);//一周第一天是否为星期天
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);//获取周几
        if(isFirstSunday){//若一周第一天为星期天，则-1
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }
        int delta=timeIndex/3+1-weekDay;
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_YEAR, delta);
        return new java.sql.Date((cal.getTime()).getTime());
    }

    public static int getNeedSection(int timeIndex){
        return timeIndex%3+1;
    }
    public static java.sql.Date add(java.sql.Date date,int days){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return new java.sql.Date((cal.getTime()).getTime());
    }
}

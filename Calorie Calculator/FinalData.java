import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FinalData class for 08-671 final exam.
 * @author Luxiao Ding (id: luxiaod) 10/20/2016
 */
public class FinalData implements Comparable<FinalData> {
    private Date date;
    private String activity;
    private int hours;
    private int calories;

    public FinalData(Date d, String a, int h, int c) {
        date = d;
        activity = a;
        hours = h;
        calories = c;
    }

    public Date getData() {
        return date;
    }

    public String getActivity() {
        return activity;
    }

    public int getHours() {
        return hours;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public int compareTo(FinalData o) {
        // TODO Auto-generated method stub
        if (date.getTime() > o.getData().getTime()) {
            return 1;
        } else if (date.getTime() < o.getData().getTime()) {
            return -1;
        } else {
            return activity.compareTo(o.getActivity());
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("  " + sdf.format(date)).append("          " + activity)
                .append("                 " + String.format("%,d", hours))
                .append("                 " + String.format("%,d", calories));
        return sb.toString();
    }

    public String dateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    }
}

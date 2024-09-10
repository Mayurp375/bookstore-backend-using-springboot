package bookstore.bookstore.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String currentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return formatter.format(date);
    }
}

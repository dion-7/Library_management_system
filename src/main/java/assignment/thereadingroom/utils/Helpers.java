package assignment.thereadingroom.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helpers {
    public static String getCurrentDateTimeIsoString(){
        LocalDateTime now = LocalDateTime.now();
        String isoDateTime = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return isoDateTime;
    }

    public static LocalDateTime getDateTimeFromString(String isoDateTime){
        LocalDateTime localDateTime = LocalDateTime.parse(isoDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return localDateTime;
    }


}

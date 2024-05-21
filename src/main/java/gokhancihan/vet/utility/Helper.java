package gokhancihan.vet.utility;

import java.time.LocalDateTime;

public class Helper {
    public static boolean isExactHour(LocalDateTime dateTime) {
        int minutes = dateTime.getMinute();
        int seconds = dateTime.getSecond();
        return (minutes == 0) & (seconds == 0);
    }
}

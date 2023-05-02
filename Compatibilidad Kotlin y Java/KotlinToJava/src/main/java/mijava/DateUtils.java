package mijava;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getFechaActual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}

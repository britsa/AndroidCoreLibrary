package britsa.core.alpha.utils;

import android.content.Context;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryCommonUtils {

    public static String convertObjectToString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static Object convertStringToObject(String value, Class<?> object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(value, object);
    }

    public static void toastShort(final Context context, String information) {
        Toast.makeText(context, information, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(final Context context, String information) {
        Toast.makeText(context, information, Toast.LENGTH_LONG).show();
    }

}

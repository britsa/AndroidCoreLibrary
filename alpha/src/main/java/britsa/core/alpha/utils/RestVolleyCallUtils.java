package britsa.core.alpha.utils;

import android.content.Context;

import britsa.core.alpha.exceptions.AppError;
import britsa.core.alpha.exceptions.AppException;

public class RestVolleyCallUtils {

    private RestVolleyCallUtils() {}

    // Returns Method string with the type passed
    public static String getMethodType(final Context context, int requestMethod) throws AppException {
        switch (requestMethod) {
            case 0:
                return "GET";
            case 1:
                return "POST";
            case 2:
                return "PUT";
            case 3:
                return "DELETE";
            case 5:
                return "OPTIONS";
            case 7:
                return "PATCH";
            default:
                throw new AppException(context, AppError.UNDEFINED_METHOD_TYPE);
        }
    }

}

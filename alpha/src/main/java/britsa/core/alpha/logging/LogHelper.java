package britsa.core.alpha.logging;

import android.content.Context;
import android.util.Log;

import java.text.MessageFormat;

import britsa.core.alpha.exceptions.AppError;

public class LogHelper {

    private LogHelper() {
    }

    private static String CONSTANT_LOGGER_CODE_MESSAGE = "{0} - {1}";

    public static void info(final Context context, final String message) {
        Log.i(context.getClass().toString(), message);
    }

    public static void warning(final Context context, final String message) {
        Log.w(context.getClass().toString(), message);
    }

    public static void warning(final Context context, final AppError appError) {
        Log.w(context.getClass().toString(), MessageFormat.format(CONSTANT_LOGGER_CODE_MESSAGE, appError.getAppErrorCode(), appError.getAppErrorMessage()));
    }

    public static void error(final Context context, final AppError appError) {
        Log.e(context.getClass().toString(), MessageFormat.format(CONSTANT_LOGGER_CODE_MESSAGE, appError.getAppErrorCode(), appError.getAppErrorMessage()));
    }

}

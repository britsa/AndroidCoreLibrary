package britsa.core.alpha.exceptions;

import android.content.Context;

import britsa.core.alpha.logging.LogHelper;
import britsa.core.alpha.utils.LibraryCommonUtils;

public class AppException extends Exception {

    private Context context;
    private AppError appError;

    public AppException(Context context, AppError appError) {
        super(appError.getAppErrorMessage());

        this.context = context;
        this.appError = appError;
        LogHelper.error(context, appError);
    }

    public void display() {
        LibraryCommonUtils.toastLong(context, appError.getAppErrorMessage());
    }

}

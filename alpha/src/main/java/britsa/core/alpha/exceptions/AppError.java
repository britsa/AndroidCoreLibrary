package britsa.core.alpha.exceptions;

public enum AppError {
    ERROR_APP_CRASH(11000, "Application crashed unexpectedly"),
    ERROR_SESSION_OBJ_JSON_PROCESSING(11001, "Error when converting object to JSON in session"),
    ERROR_OBJ_JSON_PROCESSING(11002, "Error when converting object to JSON"),
    UNDEFINED_METHOD_TYPE(11003, "Request method type should be one of GET, POST, PUT, PATCH, DELETE, OPTIONS"),
    SERVICE_CALL_FAILED(11004, "Service Call failed"),
    FIREBASE_REALTIME_DB_READ_ERROR(11005, "Some failure occurred while reading data from Firebase Realtime Database.")
    ;

    private int appErrorCode;
    private String appErrorMessage;

    AppError(int code, String message) {
        this.appErrorCode = code;
        this.appErrorMessage = message;
    }

    public int getAppErrorCode() {
        return appErrorCode;
    }

    public String getAppErrorMessage() {
        return appErrorMessage;
    }
}

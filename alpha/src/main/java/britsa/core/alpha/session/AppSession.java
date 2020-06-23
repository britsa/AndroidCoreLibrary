package britsa.core.alpha.session;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import britsa.core.alpha.exceptions.AppError;
import britsa.core.alpha.exceptions.AppException;
import britsa.core.alpha.utils.LibraryCommonUtils;

public class AppSession {

    private SharedPreferences sharedPreferences;

    public static AppSession getSessionObject() {
        return new AppSession();
    }

    private void initialize(Context context) {
        String SESSION_NAME = "britsa_preference";
        this.sharedPreferences = context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);
    }

    public void setNewSession(Context context, int id, Object value) {
        this.setNewSession(context, context.getString(id), value);
    }

    public void setNewSession(Context context, String id, Object value) {
        String stringValue;

        this.initialize(context);
        SharedPreferences.Editor editor = this.sharedPreferences.edit();

        if (value.getClass().equals(String.class)) {
            stringValue = (String) value;
            editor.putString(id, stringValue);
        } else {
            try {
                stringValue = LibraryCommonUtils.convertObjectToString(value);
                editor.putString(id, stringValue);
            } catch (IOException e) {
                new AppException(context, AppError.ERROR_SESSION_OBJ_JSON_PROCESSING).display();
            }
        }

        editor.apply();
    }

    public String getSessionString(Context context, String id) {
        return getSessionStringWithDefault(context, id, null);
    }

    public String getSessionString(Context context, int id) {
        return getSessionStringWithDefault(context, context.getString(id), null);
    }

    public String getSessionStringWithDefault(Context context, int id, String defaultValue) {
        return getSessionStringWithDefault(context, context.getString(id), defaultValue);
    }

    public String getSessionStringWithDefault(Context context, String id, String defaultValue) {
        this.initialize(context);
        return this.sharedPreferences.getString(id, defaultValue);
    }

    public void deleteSession(Context context, int id) {
        deleteSession(context, context.getString(id));
    }

    public void deleteSession(Context context, String id) {
        this.initialize(context);
        this.sharedPreferences.edit().remove(id).apply();
    }

}

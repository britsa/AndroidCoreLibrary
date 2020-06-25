package britsa.core.alpha.rest;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import britsa.core.alpha.exceptions.AppError;
import britsa.core.alpha.exceptions.AppException;
import britsa.core.alpha.logging.LogHelper;
import britsa.core.alpha.utils.LibraryCommonUtils;
import britsa.core.alpha.utils.RestVolleyCallUtils;

public class RestVolleyCall {

    // No headers, No body
    public static Object make(@NonNull final Context context, final int requestMethod, @NonNull final String serviceURL, final Class<?> classType) throws IOException, AppException {
        return RestVolleyCall.make(context, requestMethod, serviceURL, null, null, classType);
    }

    // No body
    public static Object make(@NonNull final Context context, final int requestMethod, @NonNull final String serviceURL, final Map<String, String> headers, final Class<?> classType) throws IOException, AppException {
        return RestVolleyCall.make(context, requestMethod, serviceURL, headers, null, classType);
    }

    // No headers
    public static Object make(@NonNull final Context context, final int requestMethod, @NonNull final String serviceURL, final Object body, final Class<?> classType) throws IOException, AppException {
        return RestVolleyCall.make(context, requestMethod, serviceURL, null, body, classType);
    }

    // headers and body
    public static Object make(@NonNull final Context context, final int requestMethod, @NonNull final String serviceURL, final Map<String, String> headers, final Object body, final Class<?> classType) throws IOException, AppException {
        // Initializing the required log formats
        final String REQUEST_LOG = "ServiceCall Request Log: {0} method call to {1}; Headers: [{2}]; Body: [{3}]";
        final String RESPONSE_LOG_SUCCESS = "ServiceCall Response Success Log: {0} method call to {1}; Time: [{2}]; Response: [{3}]";
        final String RESPONSE_LOG_FAILURE = "ServiceCall Response Failure Log: {0} method call to {1}; Time: [{2}]; Response: [{3}]";
        final String RESPONSE_STRING = "response_string";

        // Declaring time variables
        final long requestStartTime;
        final long[] totalResponseTime = new long[1];

        // Declaring and initializing the response value variable
        final String[] responseValue = new String[1];
        responseValue[0] = null;

        // Creating a request queue object
        RequestQueue queue = Volley.newRequestQueue(context);

        // fetching the headers of the request
        Map<String, String> requestHeaders = new HashMap<>();
        if (headers != null)
            requestHeaders.putAll(headers);
        final Map<String, String> FINAL_REQUEST_HEADERS = requestHeaders;

        // fetching the body of the request
        String requestBody = null;
        if (body != null)
            requestBody = LibraryCommonUtils.convertObjectToString(body);
        final String FINAL_REQUEST_BODY = requestBody;

        // Request Method
        final String requestMethodString = RestVolleyCallUtils.getMethodType(context, requestMethod);

        // Logging the request
        LogHelper.info(context, MessageFormat.format(REQUEST_LOG, requestMethodString, serviceURL, LibraryCommonUtils.convertObjectToString(FINAL_REQUEST_HEADERS), FINAL_REQUEST_BODY));

        // Start time of request hit
        requestStartTime = System.currentTimeMillis();

        // Making a call
        StringRequest stringRequest = new StringRequest(
                requestMethod,
                serviceURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // storing the response to a variable
                        responseValue[0] = response;

                        //calculating the duration
                        totalResponseTime[0] = System.currentTimeMillis() - requestStartTime;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                String errorMessage = new String(response.data);

                //calculating the duration
                totalResponseTime[0] = System.currentTimeMillis() - requestStartTime;

                // Logging the error message
                LogHelper.info(context, MessageFormat.format(RESPONSE_LOG_FAILURE, requestMethodString, serviceURL, totalResponseTime[0], errorMessage));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return FINAL_REQUEST_HEADERS;
            }

            @Override
            public byte[] getBody() {
                return FINAL_REQUEST_BODY == null ? null : FINAL_REQUEST_BODY.getBytes(StandardCharsets.UTF_8);
            }
        };
        // Adding the request to the queue
        queue.add(stringRequest);

        // Fetching the response string
        String response = responseValue[0];

        // Converting Response string to object
        if (response != null) {
            LogHelper.info(context, MessageFormat.format(RESPONSE_LOG_SUCCESS, requestMethodString, serviceURL, totalResponseTime[0], response));
            return LibraryCommonUtils.convertStringToObject(response, classType);
        }

        // throwing the service call failure exception
        throw new AppException(context, AppError.SERVICE_CALL_FAILED);
    }

}

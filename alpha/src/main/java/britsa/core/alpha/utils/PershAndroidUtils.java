package britsa.core.alpha.utils;

import android.content.Context;

import java.text.MessageFormat;

import britsa.core.alpha.logging.LogHelper;

public class PershAndroidUtils {

    public static String phoneNumberToCustomerId(final Context context, final String phoneNumber) {
        final String CUSTOMER_ID_PREFIX = "0x{0}";
        final String OUTPUT_LOG = "Phone Number {0} = Customer ID {1}";

        int recurrenceValue = 0x811c9dc5;
        final int length = phoneNumber.length();

        for (int iteration = 0; iteration < length; iteration++) {
            recurrenceValue ^= phoneNumber.charAt(iteration);
            recurrenceValue *= 0x01000193;
        }

        String hexString = Integer.toHexString(recurrenceValue);
        String customerId = MessageFormat.format(CUSTOMER_ID_PREFIX, hexString);

        LogHelper.info(context, MessageFormat.format(OUTPUT_LOG, phoneNumber, customerId));
        return customerId;
    }

}

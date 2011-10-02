package by.kofi.scd.common.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Internationalization support
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 19:41
 */

public class I18nSupport {
    /**
     * path to a main file properties
     */
    private static final String FILE_BUNDLE_PATH = "i18n";
    private static final ResourceBundle BUNDLE;
    private static final String DEFAULT_LOCALE_CODE = "ru";

    static {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(FILE_BUNDLE_PATH);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(FILE_BUNDLE_PATH, new Locale(DEFAULT_LOCALE_CODE));
        }
        BUNDLE = bundle;
    }

    /**
     * Returns text of properties file
     *
     * @param key    - name in file
     * @param params - adding parameters. It is possible use any count, even empty
     * @return String - text from property file
     */
    public static String getText(String key, Object... params) {
        String value = BUNDLE.getString(key);
        if (params != null && params.length > 0) {
            return MessageFormat.format(value, params);
        }
        return value;
    }
}

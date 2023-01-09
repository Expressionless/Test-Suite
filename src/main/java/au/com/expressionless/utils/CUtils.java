package au.com.expressionless.utils;

import org.jboss.logging.Logger;

import au.com.expressionless.suite.test.callbacks.FIGetStringCallBack;
/**
 * Some simple Utilities for the unit test suite
 *
 * @author Bryn
 */
public class CUtils {
	static final Logger log = Logger.getLogger(CUtils.class);

    /**
     * Get a JSON style array of objects using {@link Object#toString()} for each object
     * @param <T> type
     * @param arr - array to get stringified array of
     * @return a JSON style array of object
     */
    public static <T> String getArrayString(T[] arr) {
        return getArrayString(arr, Object::toString);
    }

    /**
     * Get a JSON style array of objects. Pass a callback for custom values. Will default to {@link Object#toString()} otherwise
     * @param <T> type
     * @param array - list to get array of
     * @param stringCallback - callback to use to retrieve a string value of the object
     * @return a JSON style array of objects, where each item is the value returned from stringCallback
     */
    public static <T> String getArrayString(T[] array, FIGetStringCallBack<T> stringCallback) {
        if(array == null) return "null";
        if(array.length == 0) return "[]";
        
        StringBuilder result = new StringBuilder("[");
        int i;
        for(i = 0; i < array.length - 1; i++) {
            result.append("\"")
            .append(stringCallback.getString(array[i]))
            .append("\",");
        }

        result.append("\"")
        .append(stringCallback.getString(array[i]))
        .append("\"]");
        
        return result.toString();
    }

    /**
     * Create an equals break (======) of size len
     * @param len length of the equals break
     * @return The equals string
     */
    public static String equalsBreak(int len) {
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < len; i++) {
            ret.append("=");
        }

        return ret.toString();
    }
}

package tue.horse.utils;

/**
 * Created by KTRAGANOS on 12-4-2017.
 */
public class StringUtils {

    /**
     * Removes quotes from a string.
     * @param toRemove The string to remove quotes from.
     * @return String without quotes.
     */
    public static String removeLeadingAndTrailingQuotes(String toRemove) {
        if (toRemove.length() > 0) {
            if (toRemove.charAt( 0 ) == '\"') {
                toRemove = toRemove.substring(1);
            }
            if (toRemove.charAt(toRemove.length() - 1) == '\"') {
                toRemove = toRemove.substring(0, toRemove.length() - 1);
            }
        }
        return toRemove;
    }


}
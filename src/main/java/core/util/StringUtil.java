package core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String getSubStringByPattern(String pattern, String inputString){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputString);
        if (m.find()) {
            return m.group();
        }
        return null;
    }
}

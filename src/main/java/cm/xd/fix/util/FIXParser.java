package cm.xd.fix.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by deepy on 2016-12-30.
 */
public class FIXParser {
    public static SortedMap<Integer, String> parse(String message) {
        TreeMap<Integer, String> results = new TreeMap<>();
        for (String pair : message.split("\u0001")) {
            if ("\r\n".equals(pair)) break;
            String[] split = pair.split("=");
            if (split.length < 2) {
                results.put(Integer.valueOf(split[0]), null);
            } else {
                results.put(Integer.valueOf(split[0]), split[1]);
            }
        }
        return results;
    }

    public static String unparse(SortedMap<Integer, String> message) {
        StringWriter sw = new StringWriter();
        for (Map.Entry<Integer, String> entry : message.entrySet()) {
            sw.write(entry.getKey());
            sw.write("=");
            if (entry.getValue() != null) {
                sw.write(entry.getValue());
            }
            sw.write("\u0001");
        }
        return sw.toString();
    }
}

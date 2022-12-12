package networking;
import android.util.Patterns;
public class IPAddress {
    public static final String DEFAULT = "127.0.0.1";
    private static final String ADDRESS_PREFIX = "http://";
    private static final String ADDRESS_SUFFIX = ":8000";

    public static boolean correctFormat(String ip) {
        return Patterns.IP_ADDRESS.matcher(ip).matches();
    }

    public static String getBaseAddressUrl(String ip) {
        return ADDRESS_PREFIX + ip + ADDRESS_SUFFIX;
    }
}

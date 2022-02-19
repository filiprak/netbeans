package org.netbeans.modules.custom.fmt.formatters;

import org.netbeans.modules.custom.fmt.CliOptions;
import org.netbeans.modules.custom.fmt.formatters.php.PHPFormatter;

import java.util.HashMap;
import java.util.function.BiConsumer;

public final class RegisteredFormatters {
    private static final HashMap<String, Formatter> registered = new HashMap<>();

    static {
        registered.put(CliOptions.Mime.PHP, new PHPFormatter());
    }

    public static Formatter get(String mimeType) {
        return registered.getOrDefault(mimeType, new PHPFormatter());
    }

    public static void each(BiConsumer<String, Formatter> call) {
        registered.forEach(call);
    }
}

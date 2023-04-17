package org.netbeans.modules.custom.fmt.formatters;

import org.netbeans.modules.custom.fmt.CliOptions;
import org.netbeans.modules.custom.fmt.formatters.php.PHPCodeFormatter;
import org.openide.util.NotImplementedException;

import java.util.HashMap;
import java.util.function.BiConsumer;

public final class RegisteredFormatters {
    private static final HashMap<String, Formatter> registered = new HashMap<>();

    static {
        registered.put(CliOptions.Mime.PHP, new PHPCodeFormatter());
    }

    public static Formatter get(String mimeType) {
        if (registered.containsKey(mimeType)) {
            return registered.get(mimeType);
        } else {
            throw new NotImplementedException(String.format("Mime type '%s' is not supported", mimeType));
        }
    }

    public static void each(BiConsumer<String, Formatter> call) {
        registered.forEach(call);
    }
}

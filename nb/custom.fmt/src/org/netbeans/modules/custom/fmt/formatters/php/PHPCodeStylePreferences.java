package org.netbeans.modules.custom.fmt.formatters.php;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.*;

import static org.netbeans.modules.php.editor.indent.FmtOptions.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.netbeans.modules.custom.fmt.CliOptions;
import org.netbeans.modules.custom.fmt.formatters.FormatterException;
import org.netbeans.modules.php.editor.indent.FmtOptions;


public class PHPCodeStylePreferences extends AbstractPreferences {
    private final HashMap<String, String> prefs = new HashMap<>();

    public PHPCodeStylePreferences() {
        super(null, "");

        load();
    }

    public void load() {
        JSONObject json = CliOptions.getConfigJson();

        if (json != null) {
            prefs.clear();

            Map<String, String> defaults = FmtOptions.getDefaults();

            defaults.forEach((name, value) -> {
                try {
                    if (json.has(name)) {
                        prefs.put(name, json.get(name).toString());
                    }
                } catch (Exception ex) {
                    System.out.println("Warning: failed to parse json config, using default config instead");
                    ex.printStackTrace();
                }
            });
        }
    }

    @Override
    protected void putSpi(String s, String s1) {
        prefs.put(s, s1);
    }

    @Override
    protected String getSpi(String s) {
        return prefs.get(s);
    }

    @Override
    protected void removeSpi(String s) {
        prefs.remove(s);
    }

    @Override
    protected void removeNodeSpi() throws BackingStoreException {

    }

    @Override
    protected String[] keysSpi() throws BackingStoreException {
        return prefs.keySet().toArray(new String[0]);
    }

    @Override
    protected String[] childrenNamesSpi() throws BackingStoreException {
        return new String[0];
    }

    @Override
    protected AbstractPreferences childSpi(String s) {
        return null;
    }

    @Override
    protected void syncSpi() throws BackingStoreException {

    }

    @Override
    protected void flushSpi() throws BackingStoreException {

    }
}

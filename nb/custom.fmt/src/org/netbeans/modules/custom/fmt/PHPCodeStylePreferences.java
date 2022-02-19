package org.netbeans.modules.custom.fmt;

import java.util.HashMap;
import java.util.prefs.*;

import static org.netbeans.modules.php.editor.indent.FmtOptions.*;


public class PHPCodeStylePreferences extends AbstractPreferences {
    private final HashMap<String, String> prefs = new HashMap<>();

    public PHPCodeStylePreferences() {
        super(null, "");

        prefs.put(EXPAND_TAB_TO_SPACES, "true");
        prefs.put(TAB_SIZE, "2");
        prefs.put(INDENT_SIZE, "7");
        prefs.put(CONTINUATION_INDENT_SIZE, "2");
        prefs.put(ALIGN_MULTILINE_ARRAY_INIT, "true");
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

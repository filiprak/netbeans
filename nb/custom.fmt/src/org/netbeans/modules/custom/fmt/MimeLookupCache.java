package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.custom.fmt.formatters.RegisteredFormatters;
import org.netbeans.modules.custom.fmt.formatters.php.PHPCodeStylePreferences;
import org.netbeans.modules.custom.fmt.formatters.php.PHPFormatter;
import org.netbeans.modules.editor.mimelookup.MimeLookupCacheSPI;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.InstanceContent;

import java.util.HashMap;


@ServiceProvider(service = MimeLookupCacheSPI.class)
public final class MimeLookupCache extends MimeLookupCacheSPI {

    private static final HashMap<String, Lookup> contentByMime = new HashMap<>();

    public MimeLookupCache() {
        super();

        System.out.println("init mime cache");

        RegisteredFormatters.each((mimeType, fmtInstance) -> {
            contentByMime.put(mimeType, new AbstractLookup(fmtInstance.getMimeLookupContent()));
        });
    }

    @Override
    public synchronized Lookup getLookup(MimePath mp) {
        String mimeType = mp.getMimeType(0);

        if (contentByMime.containsKey(mimeType)) {
            return contentByMime.get(mimeType);
        } else {
            return Lookups.fixed();
        }
    }

}

package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.custom.fmt.formatters.RegisteredFormatters;
import org.netbeans.modules.editor.mimelookup.MimeLookupCacheSPI;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.Lookup;

import java.util.HashMap;


@ServiceProvider(service = MimeLookupCacheSPI.class)
public final class MimeLookupCache extends MimeLookupCacheSPI {

    private static final HashMap<String, Lookup> lookupByMime = new HashMap<>();

    public MimeLookupCache() {
        super();

        RegisteredFormatters.each((mimeType, fmtInstance) -> {
            lookupByMime.put(mimeType, new AbstractLookup(fmtInstance.getMimeLookupContent()));
        });
    }

    @Override
    public synchronized Lookup getLookup(MimePath mp) {
        String mimeType = mp.getMimeType(0);

        if (lookupByMime.containsKey(mimeType)) {
            return lookupByMime.get(mimeType);
        } else {
            return Lookups.fixed();
        }
    }

}

package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.csl.core.GsfParserFactory;
import org.netbeans.modules.editor.indent.spi.CodeStylePreferences;
import org.netbeans.modules.editor.mimelookup.MimeLookupCacheSPI;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.InstanceContent;

import java.net.URL;


@ServiceProvider(service = MimeLookupCacheSPI.class)
public final class MimeLookupCache extends MimeLookupCacheSPI {
    public static InstanceContent content = new InstanceContent();
    public static Lookup lookup = new AbstractLookup(content);

    public MimeLookupCache() {
        super();

        content.add(new org.netbeans.modules.custom.fmt.PHPCodeStylePreferences());
        content.add(new org.netbeans.modules.csl.core.GsfIndentTaskFactory());
    }

    public static void add(Object obj) {
        content.add(obj);
    }

    @Override
    public synchronized Lookup getLookup(MimePath mp) {
        return lookup;
    }

}

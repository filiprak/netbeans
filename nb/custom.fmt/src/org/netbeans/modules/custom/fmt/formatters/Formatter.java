package org.netbeans.modules.custom.fmt.formatters;

import org.openide.util.lookup.InstanceContent;

public abstract class Formatter {

    protected static final InstanceContent lookupContent = new InstanceContent();

    public abstract String reformat(String text);

    public InstanceContent getMimeLookupContent() {
        return lookupContent;
    }
}

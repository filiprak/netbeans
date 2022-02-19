package org.netbeans.modules.custom.fmt;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.Callable;
import javax.swing.text.Document;

import org.netbeans.modules.parsing.implspi.EnvironmentFactory;
import org.netbeans.modules.parsing.implspi.SchedulerControl;
import org.netbeans.modules.parsing.implspi.SourceControl;
import org.netbeans.modules.parsing.implspi.SourceEnvironment;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.netbeans.modules.parsing.api.Source;
import org.openide.util.NotImplementedException;
import org.openide.util.lookup.ServiceProvider;


@ServiceProvider(service = EnvironmentFactory.class, position = 50000)
public class DefaultEnvironmentFactory implements EnvironmentFactory {
    @Override
    public Class<? extends Scheduler> findStandardScheduler(String schedulerName) {
        return null;
    }

    @Override
    public SourceEnvironment createEnvironment(Source src, SourceControl control) {
        return new DefaultEnvironmentFactory.Env(control);
    }

    @Override
    public <T> T runPriorityIO(Callable<T> r) throws Exception {
        return r.call();
    }

    @Override
    public Lookup getContextLookup() {
        return Lookup.getDefault();
    }

    @Override
    public synchronized Parser findMimeParser(Lookup context, String mimeType) {
        return null;
    }

    @Override
    public Collection<? extends Scheduler> getSchedulers(Lookup context) {
        return getContextLookup().lookupAll(Scheduler.class);
    }

    static class Env extends SourceEnvironment {

        public Env(SourceControl ctrl) {
            super(ctrl);
        }

        @Override
        public boolean isReparseBlocked() {
            return false;
        }

        @Override
        public Document readDocument(FileObject f, boolean forceOpen) throws IOException {
            return null;
        }

        @Override
        public void attachScheduler(SchedulerControl s, boolean attach) {
            throw new NotImplementedException();
        }

        @Override
        public void activate() {
            throw new NotImplementedException();
        }
    }
}

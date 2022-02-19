package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.custom.fmt.formatters.RegisteredFormatters;

class Main {
    public static void main(String[] args) {
        CliOptions.init(args);

        try {
            String file = CliOptions.getInputFileContent();

            Formatter fmt = RegisteredFormatters.get(CliOptions.getMimeType());

            System.out.println(fmt.reformat(file));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}

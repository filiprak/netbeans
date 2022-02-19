package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.custom.fmt.formatters.FormatterException;
import org.netbeans.modules.custom.fmt.formatters.RegisteredFormatters;

class Main {
    public static void main(String[] args) {
        CliOptions.init(args);

        try {
            String text = CliOptions.getInputFileContent();

            if (!text.equals("")) {
                Formatter fmt = RegisteredFormatters.get(CliOptions.getMimeType());

                String reformatted = fmt.reformat(text, CliOptions.getStartOffset(), CliOptions.getEndOffset());

                System.out.println(reformatted);
            }

        } catch (FormatterException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}

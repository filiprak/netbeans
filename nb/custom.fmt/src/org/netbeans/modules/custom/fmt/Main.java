package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.custom.fmt.formatters.FormatterException;
import org.netbeans.modules.custom.fmt.formatters.RegisteredFormatters;

import java.io.IOException;

class Main {
    public static void main(String[] args) {
        CliOptions.init(args);

        try {
            String text = CliOptions.getInputFileContent();

            if (!text.equals("")) {
                Formatter fmt = RegisteredFormatters.get(CliOptions.getMimeType());

                String reformatted = fmt.reformat(text, CliOptions.getStartOffset(), CliOptions.getEndOffset());

                CliOptions.saveReformattedFile(reformatted);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);

        } catch (FormatterException ex) {
            ex.printStackTrace();
            System.exit(10);
        }
    }
}

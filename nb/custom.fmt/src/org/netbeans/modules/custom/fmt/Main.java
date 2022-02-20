package org.netbeans.modules.custom.fmt;

import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.custom.fmt.formatters.FormatterException;
import org.netbeans.modules.custom.fmt.formatters.RegisteredFormatters;

import java.io.IOException;

class Main {
    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        CliOptions.init(args);
        long t1 = System.currentTimeMillis();

        try {
            String text = CliOptions.getInputFileContent();
            long t2 = System.currentTimeMillis();

            if (!text.equals("")) {
                Formatter fmt = RegisteredFormatters.get(CliOptions.getMimeType());

                long t3 = System.currentTimeMillis();
                String reformatted = fmt.reformat(text, CliOptions.getStartOffset(), CliOptions.getEndOffset());
                long t4 = System.currentTimeMillis();

                CliOptions.saveReformattedFile(reformatted);

                long t5 = System.currentTimeMillis();

                System.out.println("Init: " + (t1 - t0) + "ms");
                System.out.println("Read: " + (t2 - t1) + "ms");
                System.out.println("Form: " + (t4 - t3) + "ms");
                System.out.println("Save: " + (t5 - t4) + "ms");
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

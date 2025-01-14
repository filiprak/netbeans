package org.netbeans.modules.custom.fmt;

import org.apache.commons.cli.*;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public final class CliOptions {

    public static final class Mime {
        public final static String PHP = "text/x-php5";
    }

    public static final class Args {
        public static final String START_OFFSET = "s";
        public static final String START_OFFSET_L = "start-offset";
        public static final String END_OFFSET = "e";
        public static final String END_OFFSET_L = "end-offset";
        public static final String MIME = "m";
        public static final String MIME_L = "mime-type";
        public static final String CONFIG = "c";
        public static final String CONFIG_L = "config";
        public static final String OUTPUT = "o";
        public static final String OUTPUT_L = "out";
    }

    private static String detectedMime;
    private static CommandLine cmd;


    public static void init(String[] args) {
        Options options = new Options();

        Option input = new Option(Args.START_OFFSET, Args.START_OFFSET_L, true, "Formatting start file offset");
        input.setRequired(false);
        options.addOption(input);

        Option output = new Option(Args.END_OFFSET, Args.END_OFFSET_L, true, "Formatting end file offset");
        output.setRequired(false);
        options.addOption(output);

        Option type = new Option(Args.MIME, Args.MIME_L, true, "File mime type (default: 'text/x-php5')");
        type.setRequired(false);
        options.addOption(type);

        Option config = new Option(Args.CONFIG, Args.CONFIG_L, true, "Formatter config json string");
        config.setRequired(false);
        options.addOption(config);

        Option outFile = new Option(Args.OUTPUT, Args.OUTPUT_L, true, "Output file path (default same as input file)");
        outFile.setRequired(false);
        options.addOption(outFile);

        CommandLineParser parser = new DefaultParser();

        try {
            cmd = parser.parse(options, args);

            if (getInputFilename() == null) {
                throw new ParseException("Input filename required");
            }

        } catch (ParseException e) {
            (new HelpFormatter())
                    .printHelp("nb-fmt.jar [options] <input-file-path>", options);

            System.exit(1);
        }
    }

    private static int getIntOption(String arg, int def) {
        if (cmd.hasOption(arg)) {
            try {
                return Integer.parseInt(cmd.getOptionValue(arg));
            } catch (NumberFormatException ex) {
                return def;
            }
        } else {
            return def;
        }
    }

    public static String getMimeType() {
        if (cmd.hasOption(Args.MIME)) {
            return cmd.getOptionValue(Args.MIME, Mime.PHP);
        } else {
            return detectedMime != null ? detectedMime : Mime.PHP;
        }
    }

    public static int getStartOffset() {
        return getIntOption(Args.START_OFFSET, 0);
    }

    public static int getEndOffset() {
        return getIntOption(Args.END_OFFSET, 0);
    }

    public static String getInputFilename() {
        if (!cmd.getArgList().isEmpty()) {
            return cmd.getArgList().get(0);
        } else {
            return null;
        }
    }

    public static JSONObject getConfigJson() {
        try {
            if (cmd.hasOption(Args.CONFIG)) {
                return new JSONObject(cmd.getOptionValue(Args.CONFIG));
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Failed to parse json: " + ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(1);
            return null;
        }
    }

    public static String getInputFileContent() {
        try {
            if (getInputFilename() != null) {
                Path path = Paths.get(getInputFilename());

                detectedMime = Files.probeContentType(path);

                return new String(Files.readAllBytes(path));
            } else {
                throw new IOException("File name is empty");
            }
        } catch (Exception ex) {
            System.out.println("Unable to read file: " + ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(1);
            return "";
        }
    }

    public static void saveReformattedFile(String formatted) throws IOException {
        BufferedWriter writer = null;

        try {
            String outFilename = cmd.getOptionValue(Args.OUTPUT, getInputFilename());

            File file = new File(outFilename);

            if (!file.isDirectory()) {
                if (file.getParentFile() == null || file.getParentFile().exists() || file.getParentFile().mkdirs()) {
                    writer = new BufferedWriter(new FileWriter(outFilename));
                    writer.write(formatted);

                    writer.close();
                } else {
                    throw new IOException("Failed to create directiories");
                }
            } else {
                throw new IOException("Output file is a directory");
            }

        } catch (Exception ex) {
            System.out.println("Unable to write file: " + ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}

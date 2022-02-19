package org.netbeans.modules.custom.fmt;

import javax.swing.text.*;

import org.netbeans.modules.editor.indent.api.Reformat;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.indent.spi.ReformatTask;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.php.editor.indent.PHPFormatter;
import org.netbeans.modules.php.editor.parser.ASTPHP5Parser;
import org.netbeans.modules.php.editor.parser.ASTPHP5Scanner;
import org.netbeans.modules.php.editor.parser.astnodes.Program;
import org.netbeans.modules.php.editor.parser.PHPParseResult;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.php.editor.parser.ParserErrorHandler;
import org.netbeans.modules.php.editor.lexer.PHPTokenId;
import org.netbeans.api.lexer.Language;
import java.io.*;
import java.nio.file.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.Scanner;
import java.io.StringReader;
import org.netbeans.modules.custom.fmt.CliOptions;
import org.apache.commons.cli.*;


class Main {
    public byte[] readFile(String path) throws IOException {
        Path p = Paths.get(path);
        return Files.readAllBytes(p);
    }

    public static BaseDocument getDocument(String text) {
        BaseDocument doc = new BaseDocument(false, "text/x-php5");

        try {
            doc.putProperty(Language.class, PHPTokenId.language());
            doc.putProperty("skipLockCheck", true);
            doc.insertString(0, text, null);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public static String reformatString(String text) throws BadLocationException {
        BaseDocument doc = getDocument(text);

        System.out.println("--- Start Doc Format:\n\n" + doc.getText(0, doc.getLength()));

        // calling the php ast parser itself
        ASTPHP5Scanner scanner = new ASTPHP5Scanner(new StringReader(doc.getText(0, doc.getLength())), true, true);
        ASTPHP5Parser parser = new ASTPHP5Parser((Scanner) scanner);

        parser.setErrorHandler(new ParserErrorHandler() {
            @Override
            public void handleError(Type type, short[] expectedTokens, Symbol current, Symbol previous) {
                System.out.println("Parsing error: " + type.toString());
            }
        });

        try {
            PHPFormatter formatter = new PHPFormatter();
            Symbol root = parser.parse();
            PHPParseResult compilationInfo = new PHPParseResult(null, (Program) root.value);

            MimeLookupCache.add(new ReformatTask.Factory() {
                public ReformatTask createTask(Context context) {
                    return new ReformatTask() {
                        public void reformat() throws BadLocationException {
                            formatter.reformat(context, compilationInfo);
                        }

                        public ExtraLock reformatLock() {
                            return null;
                        }
                    };
                }
            });

            final Reformat fmt = Reformat.get(doc);

            fmt.lock();

            try {
                fmt.reformat(0, doc.getLength());
            } finally {
                fmt.unlock();
            }

            System.out.println("--- Doc formatted:\n\n" + doc.getText(0, doc.getLength()));

        } catch (Exception e) {
            System.out.println(e.getMessage());

            e.printStackTrace();
        }

        return doc.getText(0, doc.getLength());
    }

    public static void main(String[] args) throws BadLocationException, IOException {
        CliOptions.init(args);

        String file = CliOptions.getInputFileContent();

        //String doc1 = "<?php\n\nfunction test() \n\n\n{$z=1;$c=66;}\n\n\n\n$test   = \n\n  [\n1,2,3\n] ;\n\n";

        String doc2 = "<?php\r\n" +
                "\r\n" +
                "function test() {\r\n" +
                "$z = 1;\r\n" +
                "  $c = 66;\r\n" +
                "  }\r\n" +
                "\r\n" +
                "  $test = [\r\n" +
                " 1, 2, 3\r\n" +
                "];\r\n";

        //reformatString(doc1);
        reformatString(doc2);
    }
}

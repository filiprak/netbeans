package org.netbeans.modules.custom.fmt.formatters.php;

import java_cup.runtime.Symbol;
import org.netbeans.api.lexer.Language;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.custom.fmt.CliOptions;
import org.netbeans.modules.custom.fmt.DefaultEnvironmentFactory;
import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.custom.fmt.formatters.FormatterException;
import org.netbeans.modules.editor.indent.api.Reformat;
import org.netbeans.modules.editor.indent.api.Indent;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.ReformatTask;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.netbeans.modules.php.editor.indent.PHPFormatter;
import org.netbeans.modules.php.editor.lexer.PHPTokenId;
import org.netbeans.modules.php.editor.parser.ASTPHP5Parser;
import org.netbeans.modules.php.editor.parser.ASTPHP5Scanner;
import org.netbeans.modules.php.editor.parser.PHPParseResult;
import org.netbeans.modules.php.editor.parser.ParserErrorHandler;
import org.netbeans.modules.php.editor.parser.astnodes.Program;

import javax.swing.text.BadLocationException;
import java.io.StringReader;

public class PHPCodeFormatter extends Formatter {
    static {
        lookupContent.add(new PHPCodeStylePreferences());
        lookupContent.add(new DefaultEnvironmentFactory());
    }

    private BaseDocument getDocument(String text) {
        BaseDocument doc = new BaseDocument(false, CliOptions.Mime.PHP);

        try {
            doc.putProperty(Language.class, PHPTokenId.language());
            doc.putProperty("skipLockCheck", true);
            doc.insertString(0, text, null);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return doc;
    }

    private InternalParserErrorHandler getParserErrorHandler() {
        return new InternalParserErrorHandler() {
            private boolean critical = false;

            @Override
            public void handleError(Type type, short[] expectedTokens, Symbol current, Symbol previous) {
                if (type == Type.FATAL_PARSER_ERROR) {
                    critical = true;
                }
            }

            @Override
            public boolean isCritical() {
                return critical;
            }
        };
    }

    @Override
    public String reformat(String text, int start, int end) throws FormatterException {
        try {
            BaseDocument doc = getDocument(text);

            ASTPHP5Scanner scanner = new ASTPHP5Scanner(new StringReader(doc.getText(0, doc.getLength())), true, true);
            ASTPHP5Parser parser = new ASTPHP5Parser(scanner);
            InternalParserErrorHandler errorHandler = getParserErrorHandler();
            parser.setErrorHandler(errorHandler);

            PHPFormatter formatter = new PHPFormatter();

            Symbol parsed = parser.parse();

            if (!errorHandler.isCritical()) {
                PHPParseResult parseInfo = new PHPParseResult(null, (Program) parsed.value);

                ReformatTask.Factory task1 = new ReformatTask.Factory() {
                    public ReformatTask createTask(Context context) {
                        return new ReformatTask() {
                            public void reformat() {
                                System.out.println("reformating...");
                                formatter.reformat(context, parseInfo);
                            }

                            public ExtraLock reformatLock() {
                                return null;
                            }
                        };
                    }
                };

                // IndentTask.Factory task2 = new IndentTask.Factory() {
                //     public IndentTask createTask(Context context) {
                //         return new IndentTask() {
                //             public void reindent() {
                //                 System.out.println("indenting...");
                //                 formatter.reindent(context);
                //             }

                //             public ExtraLock indentLock() {
                //                 return null;
                //             }
                //         };
                //     }
                // };

                lookupContent.add(task1);
                // lookupContent.add(task2);

                // format
                final Reformat fmt = Reformat.get(doc);

                fmt.lock();

                try {
                    int realStart = start > 0 ? Math.min(start, doc.getLength()) : 0;
                    int realEnd = end > 0 ? Math.max(realStart, Math.min(doc.getLength(), end)) : doc.getLength();

                    fmt.reformat(realStart, realEnd);
                } finally {
                    fmt.unlock();
                    lookupContent.remove(task1);
                }

                // indent
                // final Indent ind = Indent.get(doc);

                // ind.lock();

                // try {
                //     int realStart = start > 0 ? Math.min(start, doc.getLength()) : 0;
                //     int realEnd = end > 0 ? Math.max(realStart, Math.min(doc.getLength(), end)) : doc.getLength();

                //     ind.reindent(realStart, realEnd);
                // } finally {
                //     ind.unlock();
                //     lookupContent.remove(task2);
                // }

                return doc.getText(0, doc.getLength());
            } else {
                throw new Exception("Critical parser error");
            }

        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }

    private interface InternalParserErrorHandler extends ParserErrorHandler {
        public boolean isCritical();
    }
}

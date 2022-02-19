package org.netbeans.modules.custom.fmt.formatters.php;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import org.netbeans.api.lexer.Language;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.custom.fmt.CliOptions;
import org.netbeans.modules.custom.fmt.FmtEnvironmentFactory;
import org.netbeans.modules.custom.fmt.formatters.Formatter;
import org.netbeans.modules.editor.indent.api.Reformat;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.ReformatTask;
import org.netbeans.modules.php.editor.lexer.PHPTokenId;
import org.netbeans.modules.php.editor.parser.ASTPHP5Parser;
import org.netbeans.modules.php.editor.parser.ASTPHP5Scanner;
import org.netbeans.modules.php.editor.parser.PHPParseResult;
import org.netbeans.modules.php.editor.parser.ParserErrorHandler;
import org.netbeans.modules.php.editor.parser.astnodes.Program;
import org.openide.util.lookup.InstanceContent;

import javax.swing.text.BadLocationException;
import java.io.StringReader;

public class PHPFormatter extends Formatter {
    static {
        lookupContent.add(new PHPCodeStylePreferences());
        lookupContent.add(new FmtEnvironmentFactory());
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

    @Override
    public String reformat(String text) {
        try {
            BaseDocument doc = getDocument(text);

            ASTPHP5Scanner scanner = new ASTPHP5Scanner(new StringReader(doc.getText(0, doc.getLength())), true, true);
            ASTPHP5Parser parser = new ASTPHP5Parser((Scanner) scanner);

            parser.setErrorHandler(new ParserErrorHandler() {
                @Override
                public void handleError(Type type, short[] expectedTokens, Symbol current, Symbol previous) {
                    System.out.println("Parsing error: " + type.toString());
                }
            });
            org.netbeans.modules.php.editor.indent.PHPFormatter formatter = new org.netbeans.modules.php.editor.indent.PHPFormatter();
            Symbol root = parser.parse();
            PHPParseResult compilationInfo = new PHPParseResult(null, (Program) root.value);

            ReformatTask.Factory task = new ReformatTask.Factory() {
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
            };

            lookupContent.add(task);

            final Reformat fmt = Reformat.get(doc);

            fmt.lock();

            try {
                fmt.reformat(0, doc.getLength());
            } finally {
                fmt.unlock();
                lookupContent.remove(task);
            }

            return doc.getText(0, doc.getLength());

        } catch (Exception e) {
            System.out.println(e.getMessage());

            e.printStackTrace();
        }
        return null;
    }
}

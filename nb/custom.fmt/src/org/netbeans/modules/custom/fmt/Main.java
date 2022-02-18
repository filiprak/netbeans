package org.netbeans.modules.custom.fmt;

import javax.swing.text.*;

import org.netbeans.modules.editor.indent.api.Reformat;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.indent.spi.*;
import org.netbeans.modules.php.editor.indent.*;
import org.netbeans.modules.php.editor.indent.PHPFormatter;
import org.netbeans.modules.php.editor.parser.ASTPHP5Parser;
import org.netbeans.modules.php.editor.parser.ASTPHP5Scanner;
import org.netbeans.modules.php.editor.parser.astnodes.Program;
import org.netbeans.modules.php.editor.parser.PHPParseResult;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.settings.storage.StorageImpl;
import org.netbeans.modules.editor.settings.storage.spi.TypedValue;
import org.netbeans.modules.editor.settings.storage.preferences.PreferencesStorage;
import org.netbeans.modules.editor.settings.storage.api.EditorSettingsStorage;
import org.netbeans.api.editor.mimelookup.MimePath;
import java_cup.runtime.Symbol;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


class Main {
    public static BaseDocument getDocument() {
        BaseDocument doc = new BaseDocument(false, "text/x-php5");

        try {
            doc.putProperty(org.netbeans.api.lexer.Language.class, org.netbeans.modules.php.editor.lexer.PHPTokenId.language());
            doc.insertString(0, "<?php\n\nfunction test() \n\n\n{$z=1;$c=66;}\n\n\n\n$test   = \n\n  [\n1,2,3\n] ;\n\n", null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

//    public static Map<String, TypedValue> loadPreferences() throws IOException {
//        //StorageImpl<String, TypedValue> storage = new StorageImpl<>(new PreferencesStorage(), null);
//        EditorSettingsStorage<String, TypedValue> storage1 = EditorSettingsStorage.get("Preferences");
//
//        Map<String, TypedValue> map2 = new HashMap<>();
//        map2.put("tab-size", new TypedValue("77", Integer.class.getName()));
//        map2.put("yyy", new TypedValue("88", Integer.class.getName()));
//        storage1.save(MimePath.get("text/x-php5"), null, false, map2);
//
//        EditorSettingsStorage<String, TypedValue> storage = EditorSettingsStorage.get("Preferences");
//        Map<String, TypedValue> map = storage.load(MimePath.get("text/x-php5"), null, false);
//
//        System.out.println("\nLoaded preferences: ---------");
//
//        map.forEach((s, typedValue) -> {
//            System.out.print(s + ": ");
//            System.out.println(typedValue);
//        });
//
//        System.out.println("\n---------\n\n");
//
//        return map;
//    }

    public static void main(String[] args) throws BadLocationException {
        System.out.println("Starting formatter");

        BaseDocument doc = getDocument();

        System.out.println("Doc:\n\n" + doc.getText(0, doc.getLength()));

        // calling the php ast parser itself
        ASTPHP5Scanner scanner = new ASTPHP5Scanner(new StringReader(doc.getText(0, doc.getLength())), true, true);
        ASTPHP5Parser parser = new ASTPHP5Parser(scanner);

        parser.setErrorHandler(new org.netbeans.modules.php.editor.parser.ParserErrorHandler() {
            @Override
            public void handleError(Type type, short[] expectedTokens, Symbol current, Symbol previous) {
                System.out.println("Parsing error: " + type.toString());
            }
        });

        try {
            PHPFormatter formatter = new PHPFormatter();
            Symbol root = parser.parse();
            PHPParseResult compilationInfo = new PHPParseResult(null, (Program) root.value);

            org.netbeans.modules.custom.fmt.MimeLookupCache.add(new ReformatTask.Factory() {
                public ReformatTask createTask(Context context) {
                    final Context ctx = context;
                    return new ReformatTask() {
                        public void reformat() throws BadLocationException {
                            formatter.reformat(ctx, compilationInfo);
                            //formatter.reindent(ctx);

                            System.out.println("Doc formatted:\n\n" + ctx.document().getText(0, ctx.document().getLength()));
                        }

                        public ExtraLock reformatLock() {
                            return null;
                        }
                    };
                }
            });

            org.netbeans.modules.custom.fmt.MimeLookupCache.add(new IndentTask.Factory() {
                public IndentTask createTask(Context context) {
                    final Context ctx = context;
                    return new IndentTask() {
                        public void reindent() throws BadLocationException {
                            formatter.reindent(ctx);
                        }

                        public ExtraLock indentLock() {
                            return null;
                        }
                    };
                }
            });

            final Reformat fmt = Reformat.get(doc);

            fmt.lock();

            try {
                doc.atomicLock();
                try {
                    fmt.reformat(0, doc.getLength());

                } finally {
                    doc.atomicUnlock();
                }
            } finally {
                fmt.unlock();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

            e.printStackTrace();
        }

    }
}

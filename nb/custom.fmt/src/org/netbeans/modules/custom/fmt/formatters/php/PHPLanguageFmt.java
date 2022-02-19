package org.netbeans.modules.custom.fmt.formatters.php;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.api.Formatter;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import org.netbeans.modules.custom.fmt.CliOptions;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.php.editor.indent.PHPFormatter;
import org.netbeans.modules.php.editor.lexer.PHPTokenId;
import org.netbeans.modules.php.editor.parser.GSFPHPParser;


@LanguageRegistration(mimeType = CliOptions.Mime.PHP, useMultiview = true)
public class PHPLanguageFmt extends DefaultLanguageConfig {

    public static final String LINE_COMMENT_PREFIX = "//"; // NOI18N

    @Override
    public String getLineCommentPrefix() {
        return LINE_COMMENT_PREFIX;
    }

    @Override
    public boolean isIdentifierChar(char c) {
        return Character.isJavaIdentifierPart(c) || (c == '@'); //NOI18N
    }

    @Override
    public Language getLexerLanguage() {
        return PHPTokenId.language();
    }

    @Override
    public String getDisplayName() {
        return "PHP";
    }

    @Override
    public String getPreferredExtension() {
        return "php"; // NOI18N
    }

    // Service Registrations

    @Override
    public Parser getParser() {
        return new GSFPHPParser();
    }

    @Override
    public boolean hasFormatter() {
        return true;
    }

    @Override
    public Formatter getFormatter() {
        return new PHPFormatter();
    }

}

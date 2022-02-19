package org.netbeans.modules.custom.fmt.formatters;

public class FormatterException extends Exception {

    public FormatterException(String message) {
        super(message);
    }

    public FormatterException(Exception e) {
        super(e);
    }
}

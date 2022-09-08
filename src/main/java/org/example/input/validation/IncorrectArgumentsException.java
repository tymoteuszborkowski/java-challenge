package org.example.input.validation;

public class IncorrectArgumentsException extends RuntimeException {
    public IncorrectArgumentsException(String s) {
        super(s);
    }
}

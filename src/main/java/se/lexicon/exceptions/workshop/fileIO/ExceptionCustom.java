package se.lexicon.exceptions.workshop.fileIO;

public class ExceptionCustom extends Exception{

    private String paramValue;
    private String message;
    private Integer errorCode;

    public ExceptionCustom(String paramValue, String message, Integer errorCode) {
        this.paramValue = paramValue;
        this.message = message;
        this.errorCode = errorCode;
    }
}

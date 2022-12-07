package se.lexicon.exceptions.workshop.fileIO;

public class DuplicateNameException extends Exception{
    private String message;
    private Integer errorCode;

    public DuplicateNameException(String message, Integer errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}

package models;

/**
 * Created by martin on 3/25/16.
 */
public class ErrorMsg {
    private String message;

    public ErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

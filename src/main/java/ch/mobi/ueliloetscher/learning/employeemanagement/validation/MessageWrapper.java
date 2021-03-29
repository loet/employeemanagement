package ch.mobi.ueliloetscher.learning.employeemanagement.validation;

import java.io.Serializable;

public class MessageWrapper implements Serializable {
    private String message;
    private String path;

    public MessageWrapper() {

    }

    public MessageWrapper(String message, String propertyPath) {
        this.message = message;
        this.path = propertyPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        return message + "(path: " + path + ")";
    }
}

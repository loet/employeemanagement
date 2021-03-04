package ch.mobi.ueliloetscher.learning.employeemanagement.validation;

import java.io.Serializable;

public class MessageWrapper implements Serializable {
    private String message;
    private String property;

    public MessageWrapper() {

    }

    public MessageWrapper(String message, String propertyPath) {
        this.message = message;
        this.property = propertyPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String toString() {
        return message + "(property: " + property + ")";
    }
}

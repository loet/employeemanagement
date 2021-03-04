package ch.mobi.ueliloetscher.learning.employeemanagement.dto;

import java.io.Serializable;
import java.util.Collection;

public class CollectionWrapper implements Serializable {

    private Collection data;

    public CollectionWrapper() {
    }

    public CollectionWrapper(Collection data) {
        this.data = data;
    }

    public Collection getData() {
        return data;
    }

    public void setData(Collection data) {
        this.data = data;
    }

    public String toString() {
        return "data: " + getData().toString();
    }
}

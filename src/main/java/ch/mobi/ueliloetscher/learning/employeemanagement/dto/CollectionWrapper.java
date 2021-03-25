package ch.mobi.ueliloetscher.learning.employeemanagement.dto;

import java.io.Serializable;
import java.util.Collection;

public class CollectionWrapper<T> implements Serializable {

    private Collection<T> data;

    public CollectionWrapper() {
    }

    public CollectionWrapper(Collection<T> data) {
        this.data = data;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public String toString() {
        return "data: " + getData().toString();
    }
}

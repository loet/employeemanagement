package ch.mobi.ueliloetscher.learning.employeemanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NamedQuery(query = "Select d from Department d where d.name = :name", name = "search department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long did) {
        this.id = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package ch.mobi.ueliloetscher.learning.employeemanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
//@NamedQuery(query = "Select e from Employee e where e.ename like :ename ", name="search employees by ename")
@NamedQuery(query = "Select e from Employee e where e.name_search like lower(concat('%', :ename,'%')) ", name = "search employees by ename")
public class Employee implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "ename is required")
    private String name;
    private String name_search;
    @NotNull(message = "salary required")
    private BigDecimal salary;
    @NotBlank(message = "deg is required")
    private String degree;

    @ManyToOne
    @NotNull(message = "department required")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "skills required")
    private List<Skill> skills = new ArrayList<>();

    public Employee(Long id, String name, BigDecimal salary, String degree) {
        super();
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.degree = degree;
    }

    public Employee() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long eid) {
        this.id = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String ename) {
        this.name = ename;
    }

    public String getName_search() {
        return name_search;
    }

    public void setName_search(String ename_search) {
        this.name_search = ename_search;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String deg) {
        this.degree = deg;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", ename=" + name + ", salary=" + salary + ", deg=" + degree + "]";
    }
}

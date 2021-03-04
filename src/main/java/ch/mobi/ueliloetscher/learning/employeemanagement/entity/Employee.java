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
@NamedQuery(query = "Select e from Employee e where e.ename_search like lower(concat('%', :ename,'%')) ", name = "search employees by ename")
public class Employee implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "ename is required")
    private String ename;
    private String ename_search;
    @NotNull(message = "salary required")
    private BigDecimal salary;
    @NotBlank(message = "deg is required")
    private String deg;

    @ManyToOne
    @NotNull(message = "department required")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "skills required")
    private List<Skill> skills = new ArrayList<>();

    public Employee(Long id, String ename, BigDecimal salary, String deg) {
        super();
        this.id = id;
        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEname_search() {
        return ename_search;
    }

    public void setEname_search(String ename_search) {
        this.ename_search = ename_search;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
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
        return "Employee [id=" + id + ", ename=" + ename + ", salary=" + salary + ", deg=" + deg + "]";
    }
}

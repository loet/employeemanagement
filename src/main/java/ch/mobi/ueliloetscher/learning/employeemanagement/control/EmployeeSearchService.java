package ch.mobi.ueliloetscher.learning.employeemanagement.control;


import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class EmployeeSearchService {

    @Inject
    EntityManager em;

    public Collection<Employee> getAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        Collection<Employee> allEmployees = (Collection<Employee>) query.getResultList();
        return allEmployees;
    }

    public Collection<Employee> searchEmployees(String ename) {
        Query query = em.createNamedQuery("search employees by ename");

        query.setParameter("ename", ename);
        List<Employee> employees = query.getResultList();
        return employees;
    }

}

package ch.mobi.ueliloetscher.learning.employeemanagement.control;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Department;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class DepartmentService {

    @Inject
    EntityManager em;

    public Department searchDepartment(String name) {
        Query query = em.createNamedQuery("search department");

        query.setParameter("name", name);
        List<Department> employees = query.getResultList();
        if (employees.size() < 1) {
            return null;
        }
        return employees.get(0);
    }

    public Department addDepartment(@Valid Department department) {
        em.persist(department);
        return department;
    }

}

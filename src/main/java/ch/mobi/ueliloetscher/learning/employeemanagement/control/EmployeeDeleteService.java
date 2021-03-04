package ch.mobi.ueliloetscher.learning.employeemanagement.control;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class EmployeeDeleteService {

    @Inject
    EntityManager em;

    public boolean deleteEmployee(Long eid) {
        Employee employee = em.find(Employee.class, eid);
        if (employee == null) {
            return false;
        }
        em.remove(employee);
        return true;
    }

}

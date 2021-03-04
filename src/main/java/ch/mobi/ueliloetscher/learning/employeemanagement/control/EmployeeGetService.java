package ch.mobi.ueliloetscher.learning.employeemanagement.control;


import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class EmployeeGetService {

    @Inject
    EntityManager em;

    public Employee getEmployee(Long eid) {
        Employee employee = em.find(Employee.class, eid);
        if (employee == null) {
            return null;
        }
        return employee;
    }

}

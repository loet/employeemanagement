package ch.mobi.ueliloetscher.learning.employeemanagement.control;


import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class EmployeeUpdateService {

    @Inject
    EmployeeStorage employeeStorage;

    @Inject
    EntityManager em;

    public Employee updateEmployee(Long eid, @Valid Employee employee) throws IllegalArgumentException {
        if (!employee.getId().equals(eid)) {
            throw new IllegalArgumentException("eid and employee.eid do not match");
        }

        Employee existing = this.em.find(Employee.class, eid);
        if (existing == null) {
            return null;
        }

        return this.employeeStorage.store(employee);
    }

}

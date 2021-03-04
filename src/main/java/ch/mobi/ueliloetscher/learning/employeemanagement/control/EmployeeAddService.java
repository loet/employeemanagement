package ch.mobi.ueliloetscher.learning.employeemanagement.control;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

@ApplicationScoped
public class EmployeeAddService {

    @Inject
    EmployeeStorage employeeStorage;

    public Employee addEmployee(@Valid Employee employee) {
        return this.employeeStorage.store(employee);
    }

}
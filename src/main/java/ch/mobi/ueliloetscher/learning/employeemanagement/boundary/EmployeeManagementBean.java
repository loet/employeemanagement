package ch.mobi.ueliloetscher.learning.employeemanagement.boundary;

import ch.mobi.ueliloetscher.learning.employeemanagement.control.*;
import ch.mobi.ueliloetscher.learning.employeemanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.employeemanagement.dto.MessageDTO;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.validation.FormatValidationMessages;
import ch.mobi.ueliloetscher.learning.employeemanagement.validation.MessageWrapper;
import ch.mobi.ueliloetscher.learning.employeemanagement.validation.ValidationException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@FormatValidationMessages
public class EmployeeManagementBean implements EmployeeManagementResource {

    @Inject
    EntityManager em;

    @Inject
    EmployeeAddService employeeAddService;
    @Inject
    EmployeeUpdateService employeeUpdateService;
    @Inject
    EmployeeSearchService employeeSearchService;
    @Inject
    EmployeeGetService employeeGetService;
    @Inject
    EmployeeDeleteService employeeDeleteService;

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response ping() {
        return Response.ok(new MessageDTO(LocalDateTime.now().toString())).build();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response searchEmployees(@QueryParam("ename") String ename) {
        if (ename != null) {
            return Response.ok(new CollectionWrapper(this.employeeSearchService.searchEmployees(ename))).build();
        } else {
            return Response.ok(new CollectionWrapper(this.employeeSearchService.getAllEmployees())).build();
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response getEmployee(@PathParam("eid") Long eid) {
        Employee employee = this.employeeGetService.getEmployee(eid);
        if (employee == null) {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }
        return Response.ok(employee).build();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response getEmployeeSlow(@PathParam("eid") Long eid) {
        Employee employee = this.employeeGetService.getEmployee(eid);
        if (employee == null) {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Response.ok(employee).build();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Response addEmployee(Employee employee) throws ValidationException {
        return Response.status(Response.Status.CREATED).entity(this.employeeAddService.addEmployee(employee)).build();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Response updateEmployee(@PathParam("eid") Long eid, Employee employee) {
        try {
            Employee updated = this.employeeUpdateService.updateEmployee(eid, employee);
            if (updated == null) {
                throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
            }
            return Response.ok(updated).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageWrapper(ex.getMessage(), "")).build();
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Response deleteEmployee(@PathParam("eid") Long eid) {
        if (this.employeeDeleteService.deleteEmployee(eid)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }
    }
}
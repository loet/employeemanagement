package ch.mobi.ueliloetscher.learning.employeemanagement.boundary;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.validation.ValidationException;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
public interface EmployeeManagementResource {
    @GET()
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    Response ping();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response searchEmployees(@QueryParam("ename") String ename);

    @GET
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getEmployee(@PathParam("eid") Long eid);

    @GET
    @Path("/slow/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getEmployeeSlow(@PathParam("eid") Long eid);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Response addEmployee(Employee employee) throws ValidationException;

    @PUT
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response updateEmployee(@PathParam("eid") Long eid, Employee employee);

    @DELETE
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteEmployee(@PathParam("eid") Long eid);
}

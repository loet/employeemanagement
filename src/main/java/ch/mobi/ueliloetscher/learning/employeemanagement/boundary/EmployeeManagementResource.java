package ch.mobi.ueliloetscher.learning.employeemanagement.boundary;

import ch.mobi.ueliloetscher.learning.employeemanagement.control.*;
import ch.mobi.ueliloetscher.learning.employeemanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.employeemanagement.dto.MessageDTO;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.validation.MessageWrapper;
import io.quarkus.runtime.configuration.ProfileManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Path("/employee")
public class EmployeeManagementResource {

    private Logger LOGGER = Logger.getLogger(this.getClass().getName().toString());

    @ConfigProperty(name = "quarkus.datasource.db-kind")
    String dbKind;

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

    @GET()
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response ping() {
        LOGGER.debug("ping called");

        return Response.ok(new MessageDTO(LocalDateTime.now().toString())).build();
    }

    @GET()
    @Path("/config")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDbKind() {
        Map<String, String> config = new HashMap<>();
        config.put("activeProfile", ProfileManager.getActiveProfile());
        config.put("dbKind", this.dbKind);
        return Response.ok(config).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response searchEmployees(@QueryParam("ename") String ename) {
        if (ename != null) {
            return Response.ok(new CollectionWrapper<Employee>(this.employeeSearchService.searchEmployees(ename))).build();
        } else {
            return Response.ok(new CollectionWrapper<Employee>(this.employeeSearchService.getAllEmployees())).build();
        }
    }

    @GET
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response getEmployee(@PathParam("eid") Long eid) {
        Employee employee = this.employeeGetService.getEmployee(eid);
        if (employee == null) {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }
        return Response.ok(employee).build();
    }

    @GET
    @Path("/slow/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response addEmployee(Employee employee) {
        Employee created = this.employeeAddService.addEmployee(employee);
        return Response
                .created(
                        UriBuilder.fromResource(EmployeeManagementResource.class)
                                .path(Long.toString(created.getId()))
                                .build()
                )
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
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

    @DELETE
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response deleteEmployee(@PathParam("eid") Long eid) {
        if (this.employeeDeleteService.deleteEmployee(eid)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }
    }
}
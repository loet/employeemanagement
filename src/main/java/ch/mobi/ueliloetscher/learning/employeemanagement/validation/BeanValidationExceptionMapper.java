package ch.mobi.ueliloetscher.learning.employeemanagement.validation;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BeanValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(createErrorMessage(e))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private JsonArray createErrorMessage(ConstraintViolationException e) {
        JsonArrayBuilder errors = Json.createArrayBuilder();
        e.getConstraintViolations().stream().forEach(violation -> {
            errors.add(
                    Json.createObjectBuilder()
                    .add("path", violation.getPropertyPath().toString())
                    .add("message", violation.getMessage())
            );
        });
        return errors.build();
    }
}

package ch.mobi.ueliloetscher.learning.employeemanagement.validation;

import javax.validation.ConstraintViolation;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationException extends BadRequestException {

    public ValidationException(Set<ConstraintViolation<?>> violations) {
        super(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(
                                violations.stream()
                                        .map(violation ->
                                                new MessageWrapper(
                                                        violation.getMessage(),
                                                        violation.getPropertyPath().toString()
                                                )
                                        )
                                        .collect(Collectors.toList())
                        )
                        .build()
        );
    }


}

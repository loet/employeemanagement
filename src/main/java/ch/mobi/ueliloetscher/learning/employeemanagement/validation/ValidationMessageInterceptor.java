package ch.mobi.ueliloetscher.learning.employeemanagement.validation;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

@FormatValidationMessages
@Priority(1)
@Interceptor
public class ValidationMessageInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } catch (ConstraintViolationException ex) {
            throw new ValidationException(ex.getConstraintViolations());
        }
    }

}

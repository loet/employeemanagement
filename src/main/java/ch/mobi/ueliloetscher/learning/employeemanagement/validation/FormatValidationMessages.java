package ch.mobi.ueliloetscher.learning.employeemanagement.validation;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

@Inherited
@InterceptorBinding
@Target(TYPE)
@Retention(RUNTIME)
public @interface FormatValidationMessages {
}

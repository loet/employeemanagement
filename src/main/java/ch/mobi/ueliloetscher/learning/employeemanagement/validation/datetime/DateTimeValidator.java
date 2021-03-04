package ch.mobi.ueliloetscher.learning.employeemanagement.validation.datetime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {

    private Pattern p = Pattern.compile("\\d\\d\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])T([0-1][0-9]|2[0-4]):([0-5]\\d):([0-5]\\d).\\d{1,3}+");

    @Override
    public boolean isValid(String dateTime, ConstraintValidatorContext constraintValidatorContext) {
        Matcher m = p.matcher(dateTime);
        return m.matches();
    }

    @Override
    public void initialize(ValidDateTime constraintAnnotation) {
    }
}

package ch.mobi.ueliloetscher.learning.employeemanagement.boundary;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Skill;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class EmployeeManagementResourceTest {

    @Test
    public void testPing() {
        given()
                .when().get("/employee/ping")
                .then()
                .statusCode(200)
                .body(containsString("message"));
    }

    @Test
    public void testGetEmployeeNotFound() {
        given()
                .pathParam("eid", 1)
                .when().get("/employee/{eid}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setEname("Foo");
        employee.setSalary(new BigDecimal(1000));
        employee.setDeg("Soso");
        employee.setDepartment(new Department("dep foo"));
        employee.setSkills(asList(new Skill("Play the Guitar"), new Skill("Play the Violin")));

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(employee)
                .when().post("/employee")
                .then()
                .statusCode(201)
                .body("ename", equalTo("Foo"))
                .body("salary", equalTo(1000))
                .body("deg", equalTo("Soso"))
                .body("department.name", equalTo("dep foo"))
                .body("skills", hasSize(2))
                .body("skills.skill", hasItems("Play the Guitar", "Play the Violin"))
                .body("skills[0].skill", equalTo("Play the Guitar"))
                .body("skills[1].skill", equalTo("Play the Violin"));
    }

    @Test
    public void testAddEmployeeBadRequest() {
        Employee employee = new Employee();
        given()
                .contentType(ContentType.JSON)
                .body(employee)
                .when().post("/employee")
                .then()
                .statusCode(400)
                .body("message",
                        hasItems(
                                "deg is required",
                                "skills required",
                                "ename is required",
                                "salary required",
                                "department required")
                );
    }


}
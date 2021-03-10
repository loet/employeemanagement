package ch.mobi.ueliloetscher.learning.employeemanagement.client;

import ch.mobi.ueliloetscher.learning.employeemanagement.boundary.EmployeeManagementResource;
import ch.mobi.ueliloetscher.learning.employeemanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.employeemanagement.dto.MessageDTO;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Skill;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoader {

    public static void main(String[] args) throws URISyntaxException {
        URI apiUri = new URI("http://127.0.0.1:8000");
        EmployeeManagementResource service = RestClientBuilder.newBuilder()
                .baseUri(apiUri)
                .build(EmployeeManagementResource.class);

        System.out.println("ping:" + service.ping().readEntity(MessageDTO.class).getMessage());

        service.addEmployee(createEmployeeData("Quang Pham", "AxonActive", Arrays.asList("Software Development", "Angular", "Ionic", "JavaScript", "TypeScript", "Java")));
        service.addEmployee(createEmployeeData("Tam Tang", "AxonActive", Arrays.asList("Software Development", "Angular", "Ionic", "JavaScript", "TypeScript", ".NET")));
        service.addEmployee(createEmployeeData("Hoa Bui", "AxonActive", Arrays.asList("Software Development", "Angular", "JavaScript", "TypeScript")));
        service.addEmployee(createEmployeeData("Dat Phan", "AxonActive", Arrays.asList("Software Development", "Angular", "JavaScript", "TypeScript", "Java", "JES")));
        service.addEmployee(createEmployeeData("Anh May", "AxonActive", Arrays.asList("Software Development", "QC", "Appium", "Jasmine")));
        service.addEmployee(createEmployeeData("Lydia Schweingruber", "Mobiliar", Arrays.asList("Software Development")));
        service.addEmployee(createEmployeeData("Ueli Lötscher", "Mobiliar", Arrays.asList("Software Development")));

        System.out.println("available " + service.searchEmployees("").readEntity(CollectionWrapper.class));
    }

    private static Employee createEmployeeData(String name, String department, Collection<String> skillStrings) {
        Employee newEmployee = new Employee();
        newEmployee.setEname(name);
        newEmployee.setSalary(BigDecimal.valueOf(1000));
        newEmployee.setDeg("PhD");
        newEmployee.setDepartment(new Department(department));
        List<Skill> skills = skillStrings.stream().map(Skill::new).collect(Collectors.toList());
        newEmployee.setSkills(skills);
        return newEmployee;
    }
}

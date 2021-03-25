package ch.mobi.ueliloetscher.learning.employeemanagement.control;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Skill;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import static java.util.Arrays.asList;

@QuarkusTest
public class EmployeeAddServiceTest {

    @Inject
    EmployeeStorage employeeStorage;

    @Inject
    EmployeeAddService employeeAddService;

    @InjectMock
    EmployeeStorage employeeStorageForMock;

    @InjectSpy
    EmployeeStorage employeeStorageSpy;

    @Test
    public void testAddEmployee() {
        // This test shows how to use QuarkusMock (with custom Mock implementation)
        QuarkusMock.installMockForInstance(new EmployeeStorageMock(), employeeStorage);

        Employee employee = new Employee();
        Assertions.assertEquals(1L, this.employeeStorage.store(employee).getId());
    }

    @Test
    public void testAddEmployeeMockitoExample() {
        // This test shows how to use QuarkusMock with Mockito
        Employee employee = new Employee();
        Employee employeeStored = new Employee();
        employeeStored.setId(1L);
        Mockito.when(employeeStorageForMock.store(employee)).thenReturn(employeeStored);

        Assertions.assertEquals(1L, this.employeeStorageForMock.store(employee).getId());
    }


    @Transactional
    public void testAddEmployeeMockitoSpyExample() throws InvocationTargetException, IllegalAccessException {
        // This test shows how to use QuarkusMock with Mockito
        Employee employee = new Employee();
        employee.setName("Foo");
        employee.setSalary(new BigDecimal(1000));
        employee.setDegree("Soso");
        Department department = new Department("dep foo");
        employee.setDepartment(department);
        employee.setSkills(asList(new Skill("Play the Guitar"), new Skill("Play the Violin")));

        this.employeeAddService.addEmployee(employee);
        Mockito.verify(employeeStorageSpy, Mockito.times(1)).store(employee);
    }

    public static class EmployeeStorageMock extends EmployeeStorage {
        @Override
        public Employee store(Employee employee) {
            // this mock is just for demonstration of the QuarkusMock feature!!!
            employee.setId(1L);
            return employee;
        }
    }

}

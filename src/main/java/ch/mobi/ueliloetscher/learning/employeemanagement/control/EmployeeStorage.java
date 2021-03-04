package ch.mobi.ueliloetscher.learning.employeemanagement.control;


import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Skill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class EmployeeStorage {

    @Inject
    DepartmentService departmentService;

    @Inject
    SkillService skillService;

    @Inject
    EntityManager em;

    public Employee store(Employee employee) {
        // set lowercase name for search statements
        employee.setEname_search(employee.getEname().toLowerCase());

        // handle department
        Department persistentDepartment = this.departmentService.searchDepartment(employee.getDepartment().getName());
        if (persistentDepartment != null) {
            employee.getDepartment().setId(persistentDepartment.getId());
        } else {
            employee.getDepartment().setId(null);
            this.departmentService.addDepartment(employee.getDepartment());
        }

        //handle skills
        employee.getSkills().stream()
                .forEach(skill -> {
                    Skill persistentSkill = this.skillService.searchSkill(skill.getSkill());
                    if (persistentSkill != null) {
                        skill.setId(persistentSkill.getId());
                    } else {
                        skill.setId(null);
                        this.skillService.addSkill(skill);
                    }
                });

        //store employee
        return em.merge(employee);
    }

}

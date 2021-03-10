package ch.mobi.ueliloetscher.learning.employeemanagement.control;

import ch.mobi.ueliloetscher.learning.employeemanagement.entity.Skill;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SkillServiceTest {

    @Inject
    SkillService skillService;

    @Test
    @Transactional
    public void testAddSkill() {
        Skill created = skillService.addSkill(new Skill("foo"));
        assertEquals("foo", created.getSkill());
        assertNotNull(created.getId());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.InstructorDTO;
import errorhandling.CourseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author root
 */
class InstructorFacadeTest extends BaseFacadeTest {

    private static final InstructorFacade FACADE = InstructorFacade.getInstructorFacade(getEntityManagerFactory());

    public InstructorFacadeTest() {
    }

    @Test
    void testGetAllInstructors() {
        int expectedNumberOfInstructors = 3;
        int resultNumberOfInstructors = FACADE.getAllInstructors().size();
        assertEquals(expectedNumberOfInstructors, resultNumberOfInstructors);
    }

    @Test
    void testCreateInstructorByDTO() {
        InstructorDTO instructorDTO = new InstructorDTO("Boris");
        InstructorDTO resultInstructorDTO = FACADE.createInstructor(instructorDTO);
        assertEquals(instructorDTO.getName(), resultInstructorDTO.getName());
    }

    @Test
    void testDeleteInstructorById() throws CourseException {
        InstructorDTO instructorDTO = FACADE.getAllInstructors().get(0);
        InstructorDTO resultInstructorDTO = FACADE.deleteInstructorById(instructorDTO.getId());
        assertEquals(instructorDTO.getName(), resultInstructorDTO.getName());
    }
}

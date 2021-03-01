/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.StudentDTO;
import entity.Student;
import errorhandling.UserException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
class StudentFacadeTest extends BaseFacadeTest{
    private static final StudentFacade FACADE = StudentFacade.getStudentFacade(getEntityManagerFactory());
    public StudentFacadeTest() {
    }

    @Test
    void testFindStudentById() throws UserException {
        Student expectedStudent = getStudent();
        StudentDTO resultStudent = FACADE.findStudentById(expectedStudent.getId());
        assertEquals(expectedStudent.getName(), resultStudent.getName());
    }
    
}

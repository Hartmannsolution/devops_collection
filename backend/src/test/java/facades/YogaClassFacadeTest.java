/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.YogaClassDTO;
import static facades.BaseFacadeTest.getEntityManagerFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author root
 */
class YogaClassFacadeTest extends BaseFacadeTest {

    private static final YogaClassFacade FACADE = YogaClassFacade.getYogaClassFacade(getEntityManagerFactory());

    public YogaClassFacadeTest() {
    }

    @Test
    void testGetAllYogaClasses() {
        int expectedNumberOfClasses = 6;
        int resultNumberOfClasses = FACADE.getAllYogaClasses().size();
        assertEquals(expectedNumberOfClasses, resultNumberOfClasses);
    }

    @Test
    void testCreateYogaClass() {
        YogaClassDTO classDTO = new YogaClassDTO(getYogaClass());
        YogaClassDTO resultClassDTO = FACADE.createYogaClass(classDTO);
        assertEquals(classDTO.getMaxParticipants(), resultClassDTO.getMaxParticipants());
    }

    @Test
    void testDeleteYogaClass() {
        YogaClassDTO classDTO = new YogaClassDTO(getYogaClass());
        YogaClassDTO resultClassDTO = FACADE.deleteYogaClass(classDTO);
        assertEquals(classDTO.getId(), resultClassDTO.getId());
    }

    @Test
    void testEditYogaClass() {
        YogaClassDTO classDTO = new YogaClassDTO(getYogaClass());
        classDTO.setMaxParticipants(99);
        YogaClassDTO resultClassDTO = FACADE.editYogaClass(classDTO);
        assertEquals(classDTO.getMaxParticipants(), resultClassDTO.getMaxParticipants());
    }

}

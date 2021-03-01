/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.InstructorDTO;
import errorhandling.CourseException;
import facades.InstructorFacade;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.glassfish.grizzly.http.util.HttpStatus;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 *
 * @author root
 */
public class InstructorResourceTest extends BaseResourceTest{
        private static final InstructorFacade FACADE = InstructorFacade.getInstructorFacade(getEntityManagerFactory());

    
    public InstructorResourceTest() {
    }

    @Test
    public void testGetAllInstructors() {
        given()
                .contentType(ContentType.JSON)
                .get("instructor/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(3));
    }
    
    @Test
    public void testCreateInstructor() {
        InstructorDTO instructorDTO = new InstructorDTO("Oscar");
        login("admin", "test");
        given()
                .contentType(ContentType.JSON)
                .header("x-access-token", getSecurityToken())
                .body(instructorDTO)
                .when()
                .post("/instructor")
                .then()
                .body("name", equalTo("Oscar"));
    }
    
    @Test
    public void testEditInstructor() throws CourseException {
        InstructorDTO instructorDTO = FACADE.getAllInstructors().get(0);
        instructorDTO.setName("Henrik");
        login("admin", "test");
        given()
                .contentType(ContentType.JSON)
                .header("x-access-token", getSecurityToken())
                .body(instructorDTO)
                .when()
                .put("/instructor")
                .then()
                .body("name", equalTo("Henrik"));
    }
    
}

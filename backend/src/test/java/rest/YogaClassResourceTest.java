/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.YogaClassDTO;
import errorhandling.CourseException;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.glassfish.grizzly.http.util.HttpStatus;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class YogaClassResourceTest extends BaseResourceTest{
    
    public YogaClassResourceTest() {
    }

    @Test
    public void testGetAllYogaClasses() {
        given()
                .contentType(ContentType.JSON)
                .get("yogaclass/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(6));
    }
    
    @Test
    public void testCreateYogaClass() {
        YogaClassDTO classDTO = new YogaClassDTO(getYogaClass());
        login("admin", "test");
        given()
                .contentType(ContentType.JSON)
                .header("x-access-token", getSecurityToken())
                .body(classDTO)
                .when()
                .post("/yogaclass")
                .then()
                .body("maxParticipants", is(10));
    }
    
    @Test
    public void testEditYogaClass() throws CourseException {
        YogaClassDTO classDTO = new YogaClassDTO(getYogaClass());
        classDTO.setMaxParticipants(99);
        login("admin", "test");
        given()
                .contentType(ContentType.JSON)
                .header("x-access-token", getSecurityToken())
                .body(classDTO)
                .when()
                .put("/yogaclass")
                .then()
                .body("maxParticipants", is(99));
    }
    
}

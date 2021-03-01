/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.CourseDTO;
import errorhandling.CourseException;
import facades.CourseFacade;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.glassfish.grizzly.http.util.HttpStatus;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author root
 */
public class CourseResourceTest extends BaseResourceTest{
    private static final CourseFacade FACADE = CourseFacade.getCourseFacade(getEntityManagerFactory());
    
    public CourseResourceTest() {
    }

    @Test
    public void testGetAllCourses() {
        given()
                .contentType(ContentType.JSON)
                .get("course/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(3));
    }
    
    @Test
    public void testCreateCourse() {
        CourseDTO courseDTO = new CourseDTO("new course", "new course description");
        login("admin", "test");
        given()
                .contentType(ContentType.JSON)
                .header("x-access-token", getSecurityToken())
                .body(courseDTO)
                .when()
                .post("/course")
                .then()
                .body("courseName", equalTo("new course"))
                .body("description", equalTo("new course description"));
    }
    
    @Test
    public void testEditCourse() throws CourseException {
        CourseDTO courseDTO = FACADE.findCourseByName("Hatha Yoga");
        courseDTO.setCourseName("name edited");
        courseDTO.setDescription("description edited");
        login("admin", "test");
        given()
                .contentType(ContentType.JSON)
                .header("x-access-token", getSecurityToken())
                .body(courseDTO)
                .when()
                .put("/course")
                .then()
                .body("courseName", equalTo("name edited"))
                .body("description", equalTo("description edited"));
    }
    
    @Disabled
    @Test
    public void testDeleteCourse() throws CourseException {
        CourseDTO courseDTO = FACADE.findCourseByName("Hatha Yoga");
        System.out.println(courseDTO);
        given()
                .contentType(ContentType.JSON)
                .body(courseDTO)
                .when()
                .delete("/course")
                .then()
                .body("courseName", equalTo(courseDTO.getCourseName()));
    }
    
}

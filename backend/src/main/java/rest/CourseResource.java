/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.CourseDTO;
import errorhandling.CourseException;
import facades.CourseFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author root
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Team Gold startcode API",
                version = "0.1",
                description = "This API is use as a base for building a backend for a separate Frontend",
                contact = @Contact(name = "Alexander", email = "cph-as485@cphbusiness.dk")
        ),
        tags = {
                @Tag(name = "Course", description = "API related to Courses"),
                @Tag(name = "login", description = "API related to Login"),
                @Tag(name = "scrape", description = "API related to WebScraping")
        },
        servers = {
                @Server(
                        description = "For Local host testing",
                        url = "http://localhost:8080/exam"
                ),
                @Server(
                        description = "Server API",
                        url = "https://www.sarson.codes/exam"
                )
        }
)
@Path("course")
public class CourseResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final CourseFacade FACADE = CourseFacade.getCourseFacade(EMF);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CourseResource
     */
    public CourseResource() {
    }

    @Operation(summary = "Returns a list of courses",
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested courses information"),
                @ApiResponse(responseCode = "404", description = "No courses found")})
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CourseDTO> getAllCourses()  {
        List<CourseDTO> listOfCourseDTOs = FACADE.getAllCourses();
        return listOfCourseDTOs;
    }
    
    @Operation(summary = "Create course",
            tags = {"course"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDTO.class))),
                @ApiResponse(responseCode = "200", description = "The course is created"),
                @ApiResponse(responseCode = "404", description = "Course not created")})
    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public CourseDTO createCourseByDTO(CourseDTO courseDTO
    ) {
        return FACADE.createCourse(courseDTO);
    }
    
    @Operation(summary = "Edit course",
            tags = {"course"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDTO.class))),
                @ApiResponse(responseCode = "200", description = "The course is edited"),
                @ApiResponse(responseCode = "404", description = "Course not edited")})
    @PUT
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public CourseDTO editCourseByDTO(CourseDTO courseDTO
    ) {
        return FACADE.editCourse(courseDTO);
    }
    
    @Operation(summary = "Delete course",
            tags = {"course"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDTO.class))),
                @ApiResponse(responseCode = "200", description = "The course is deleted"),
                @ApiResponse(responseCode = "404", description = "Course not deleted")})
    @DELETE
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public CourseDTO deleteCourseByDTO(CourseDTO courseDTO
    ) throws CourseException {
        return FACADE.deleteCourseById(courseDTO.getId());
    }
}

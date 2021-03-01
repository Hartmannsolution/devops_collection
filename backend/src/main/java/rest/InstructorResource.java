/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.InstructorDTO;
import errorhandling.CourseException;
import facades.InstructorFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Path("instructor")
public class InstructorResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final InstructorFacade FACADE = InstructorFacade.getInstructorFacade(EMF);
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InstructorResource
     */
    public InstructorResource() {
    }


    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public List<InstructorDTO> getAllCourses()  {
        List<InstructorDTO> listOfInstructorDTOs = FACADE.getAllInstructors();
        return listOfInstructorDTOs;
    }
    
    @Operation(summary = "Create instructor",
            tags = {"instructor"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstructorDTO.class))),
                @ApiResponse(responseCode = "200", description = "The instructor is created"),
                @ApiResponse(responseCode = "404", description = "Instructor not created")})
    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public InstructorDTO createInstructorByDTO(InstructorDTO instructorDTO
    ) {
        return FACADE.createInstructor(instructorDTO);
    }
    
    @Operation(summary = "Edit instructor",
            tags = {"instructor"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstructorDTO.class))),
                @ApiResponse(responseCode = "200", description = "The instructor is edited"),
                @ApiResponse(responseCode = "404", description = "Instructor not edited")})
    @PUT
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public InstructorDTO editInstructorByDTO(InstructorDTO instructorDTO
    ) {
        return FACADE.editInstructorByDTO(instructorDTO);
    }
    
    @Operation(summary = "Delete instructor",
            tags = {"instructor"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstructorDTO.class))),
                @ApiResponse(responseCode = "200", description = "The instructor is deleted"),
                @ApiResponse(responseCode = "404", description = "instructor not deleted")})
    @DELETE
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public InstructorDTO deleteInstructorByDTO(InstructorDTO instructorDTO
    ) throws CourseException {
        return FACADE.deleteInstructorById(instructorDTO.getId());
    }
}

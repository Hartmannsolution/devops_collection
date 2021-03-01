/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dtos.YogaClassDTO;
import facades.YogaClassFacade;
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
@Path("yogaclass")
public class YogaClassResource {
    
     private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final YogaClassFacade FACADE = YogaClassFacade.getYogaClassFacade(EMF);
    private static final Gson GSON = new Gson();


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of YogaClassResource
     */
    public YogaClassResource() {
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllCourses()  {
        List<YogaClassDTO> listOfYogaClassDTO = FACADE.getAllYogaClasses();
        return GSON.toJson(listOfYogaClassDTO);
    }
    
    @Operation(summary = "Create yoga class",
            tags = {"yoga class"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = YogaClassDTO.class))),
                @ApiResponse(responseCode = "200", description = "The yoga class is created"),
                @ApiResponse(responseCode = "404", description = "Yoga class not created")})
    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public YogaClassDTO createYogaClassByDTO(YogaClassDTO yogaClassDTO
    ) {
        return FACADE.createYogaClass(yogaClassDTO);
    }
    
    @Operation(summary = "Delete yoga class",
            tags = {"yoga class"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = YogaClassDTO.class))),
                @ApiResponse(responseCode = "200", description = "The yoga class is deleted"),
                @ApiResponse(responseCode = "404", description = "Yoga class not deleted")})
    @DELETE
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public YogaClassDTO deleteYogaClassByDTO(YogaClassDTO yogaClassDTO
    ) {
        return FACADE.deleteYogaClass(yogaClassDTO);
    }
    
    @PUT
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public YogaClassDTO editYogaClassByDTO(YogaClassDTO yogaClassDTO
    ) {
        return FACADE.editYogaClass(yogaClassDTO);
    }
}

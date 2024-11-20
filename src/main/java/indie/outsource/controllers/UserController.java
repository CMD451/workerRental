package indie.outsource.controllers;


import indie.outsource.DTO.user.CreateUserDTO;
import indie.outsource.dtoMappers.UserMapper;
import indie.outsource.exceptions.ResourceNotFoundException;
import indie.outsource.exceptions.UserAlreadyExistsException;
import indie.outsource.model.user.User;
import indie.outsource.services.UserService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Path("/users")
public class UserController {
    private final UserService userService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(userService.findAll().stream().map(UserMapper::getUserDTO).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") UUID id) {
        try{
            return Response.ok(UserMapper.getUserDTO(userService.findById(id))).build();
        }
        catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/login/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByLogin(@PathParam("login") String login) {
        try{
            return Response.ok(UserMapper.getUserDTO(userService.findByUsernameExact(login))).build();
        }
        catch (ResourceNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/loginContains/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByLoginContains(@PathParam("login") String login) {
        return Response.ok(userService.findByUsername(login).stream().map(UserMapper::getUserDTO).toList()).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid CreateUserDTO user) {
        try{
            return Response.ok(UserMapper.getUserDTO(userService.save(UserMapper.getUser(user)))).build();
        }
        catch(UserAlreadyExistsException e){
            return Response.status(Response.Status.CONFLICT).entity(UserMapper.getUserDTO(UserMapper.getUser(user))).build();
        }
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") UUID id, @Valid CreateUserDTO createUserDTO) {
        User user = UserMapper.getUser(createUserDTO);
        user.setId(id);
        try{
            return Response.ok(UserMapper.getUserDTO(userService.updateUser(user))).build();
        }
        catch(ResourceNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/activate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateUser(@PathParam("id") UUID id) {
        try{
            return Response.ok(UserMapper.getUserDTO(userService.activateUser(id))).build();
        }
        catch(ResourceNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/deactivate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deactivateUser(@PathParam("id") UUID id) {
        try{
            return Response.ok(UserMapper.getUserDTO(userService.deactivateUser(id))).build();
        }
        catch(ResourceNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}

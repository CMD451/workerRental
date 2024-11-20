package indie.outsource.controllers;


import indie.outsource.DTO.worker.CreateWorkerDTO;
import indie.outsource.dtoMappers.WorkerMapper;
import indie.outsource.exceptions.ResourceNotFoundException;
import indie.outsource.exceptions.WorkerRentedException;
import indie.outsource.services.WorkerService;
import indie.outsource.model.Worker;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;


@Path("/workers")
public class WorkerController {

    @Inject
    WorkerService workerService;

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorker(@PathParam("id") UUID id) {
        Worker worker;
        try{
            return Response.ok(WorkerMapper.getWorkerDto(workerService.findById(id))).build();
        }
        catch(ResourceNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllWorkers() {
        return Response.ok(workerService.findAll().stream().map(WorkerMapper::getWorkerDto).toList()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWorker(@Valid CreateWorkerDTO worker) {
        return Response.ok(WorkerMapper.getWorkerDto(workerService.save(WorkerMapper.getWorker(worker)))).build();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") UUID id, CreateWorkerDTO createWorkerDTO) {
        Worker worker = WorkerMapper.getWorker(createWorkerDTO);
        worker.setId(id);
        try{
            return Response.ok(WorkerMapper.getWorkerDto(workerService.save(worker))).build();
        }
        catch(ResourceNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteWorker(@PathParam("id") UUID id) {
        try{
            workerService.delete(id);
        }
        catch(ResourceNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (WorkerRentedException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

}

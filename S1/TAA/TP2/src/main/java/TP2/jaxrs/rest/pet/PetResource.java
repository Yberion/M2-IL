package TP2.jaxrs.rest.pet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import TP2.jaxrs.domain.pet.Pet;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/pet")
@Produces({ "application/json", "application/xml" })
public class PetResource
{
    @GET
    @Path("/{petId}")
    public Pet getPetById(@PathParam("petId") Long petId)
    {
        // return pet
        return new Pet();
    }

    @POST
    @Consumes("application/json")
    @Produces({ "text/plain" })
    public Response addPet(
            @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet)
    {
        // add pet
        return Response.ok().entity("SUCCESS").build();
    }
}
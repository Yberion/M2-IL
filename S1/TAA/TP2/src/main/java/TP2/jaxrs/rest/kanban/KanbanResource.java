package TP2.jaxrs.rest.kanban;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import TP2.jpa.kanban.dao.KanbanDAO;
import TP2.jpa.kanban.domain.Kanban;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/kanban")
@Produces({ "application/json", "application/xml" })
public class KanbanResource
{
    private KanbanDAO kanbanDAO = new KanbanDAO();
    
    @GET
    @Path("/get/{id}")
    public Kanban getKanbanById(@PathParam("id") Long id)
    {
        return kanbanDAO.findOne(id);
    }
    
    @POST
    @Consumes("application/json")
    @Produces({ "text/plain" })
    @Path("/add")
    public Response addKanban(
            @Parameter(description = "Kanban object that needs to be added", required = true) Kanban kanban)
    {
        //System.out.println(kanban.getId() + " - " + kanban.getName());
        
        kanbanDAO.save(kanban);
        
        return Response.ok().entity("SUCCESSFULLY ADDED").build();
    }
    
    @DELETE
    @Path("/remove/{id}")
    @Produces({ "text/plain" })
    public Response removeKanbanById(@PathParam("id") Long id)
    {
        kanbanDAO.deleteById(id);
        
        return Response.ok().entity("SUCCESSFULLY REMOVED").build();
    }
}

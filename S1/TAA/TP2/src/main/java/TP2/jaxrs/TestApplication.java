package TP2.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import TP2.jaxrs.rest.kanban.KanbanResource;
import TP2.jaxrs.rest.pet.PetResource;
import TP2.jaxrs.rest.swagger.SwaggerResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class TestApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        final Set<Class<?>> clazzes = new HashSet<>();
        clazzes.add(PetResource.class);
        clazzes.add(KanbanResource.class);
        clazzes.add(OpenApiResource.class);
        clazzes.add(SwaggerResource.class);
        return clazzes;
    }
}

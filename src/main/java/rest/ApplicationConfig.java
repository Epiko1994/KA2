package rest;

import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        
        //These two Resource Classes are not auto discovered so we add them manually
        resources.add(OpenApiResource.class);
        resources.add(AcceptHeaderOpenApiResource.class);

        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
        resources.add(errorhandling.GenericExceptionMapper.class);
        resources.add(io.swagger.v3.jaxrs2.SwaggerSerializers.class);
        resources.add(io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource.class);
        resources.add(io.swagger.v3.jaxrs2.integration.resources.OpenApiResource.class);
        resources.add(org.glassfish.jersey.jsonb.internal.JsonBindingProvider.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
        resources.add(rest.PersonResource.class);
    }

}

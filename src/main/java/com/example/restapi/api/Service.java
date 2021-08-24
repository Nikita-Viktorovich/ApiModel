package com.example.restapi.api;

import com.example.restapi.model.Model;
import com.example.restapi.repository.ModelDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Optional;

@Path("/api/*")
public class Service {

   private final ModelDao dao;

    public Service(ModelDao dao) {
        this.dao = dao;
    }

    @GET
    @Path("/get/{name}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Model getModel(@PathParam("name") String name) throws Exception {
        Optional<Model> model = Optional.of(dao.getModel(name));
        if (model.isPresent()) return model.get();
        else throw new IOException();
    }

    @PUT
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateModel(@PathParam("name") String name, String surname) throws Exception {
        Optional<Model> model = Optional.of(dao.getModel(name));
        if (model.isPresent()) dao.changeSurname(surname, name);
        else throw new IOException();
    }

}

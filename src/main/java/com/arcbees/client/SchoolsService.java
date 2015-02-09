package com.arcbees.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/skools")
public interface SchoolsService {
    @GET
    List<School> getSchools();

    @GET
    @Path("/{school-id}")
    School getSchool(@PathParam("school-id") long id);

    @POST
    @Path("/{school-id}")
    void updateSchool(@PathParam("school-id") long id, School school);

    @POST
    void createNewSchool(School school);
}
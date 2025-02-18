package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.Security.Service.AuthenticationService;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @Inject
    AuthenticationService authenticationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    @Path("/all")
    @Operation(summary = "List all Entries of all users", description = "Lists all Entries of all users, instead of listing only the entries of the user that sent the request")
    public List<Entry> listAll() {
        authenticationService.checkJWT();
        return entryService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "List all Entries of the User with the id", description = "Lists all Entries of the User with the id of the user that sent the request.")
    public List<Entry> list() {
        authenticationService.checkJWT();
        return entryService.findEntries(authenticationService.getIdFromJWT());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
        authenticationService.checkJWT();
        return entryService.createEntry(entry);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Delete an Entry", description = "Deletes an existing Entry with the id given")
    public void delete(@PathParam("id") long id) {
        authenticationService.checkJWT();
        entryService.deleteEntry(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Updates an Entry", description = "Updates an existing Entry with the id given")
    public void edit(@PathParam("id") long id, Entry entry) {
        authenticationService.checkJWT();
        entryService.editEntry(id, entry);
    }
}
      
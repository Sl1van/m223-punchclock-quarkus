package ch.zli.m223.punchclock.Security.Controller;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParser;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.Security.Service.AuthenticationService;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import io.undertow.security.api.SecurityContext;

@Path("/auth")
@Tag(name = "Authentication", description = "Handling the Authentication")
@RequestScoped
public class AuthenticationController {
    @Inject
    AuthenticationService authenticationService;
    
    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Path("register")
    @PermitAll
    public void register(User user){
        System.out.println(user.getUsername()+" "+user.getPassword());
        userService.createUser(user);
    }
}
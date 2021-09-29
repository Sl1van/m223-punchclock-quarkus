package ch.zli.m223.punchclock.Security.Controller;

import ch.zli.m223.punchclock.Security.Service.AuthenticationService;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Tag(name = "Authentication", description = "Handles the Authentication")
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
    @Operation(summary = "creates a new user", description = "creates a new user.")
    public void register(User user) {
        userService.createUser(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    @PermitAll
    @Operation(summary = "log user in and get a JWT", description = "The sender receives a valid JWT when the correct user credentials are provided.")
    public String login(User tempUser) {
        User user = userService.matchCredentials(tempUser.getUsername(), tempUser.getPassword());
        return authenticationService.GenerateValidJwtToken(user);
    }
}
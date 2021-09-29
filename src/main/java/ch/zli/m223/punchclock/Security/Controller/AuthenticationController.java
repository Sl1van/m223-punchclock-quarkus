package ch.zli.m223.punchclock.Security.Controller;

import ch.zli.m223.punchclock.Security.Service.AuthenticationService;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
    public void register(User user) {
        userService.createUser(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    @PermitAll
    public String login(User tempUser) {
        User user = userService.matchCredentials(tempUser.getUsername(), tempUser.getPassword());
        return authenticationService.GenerateValidJwtToken(user);
    }
}
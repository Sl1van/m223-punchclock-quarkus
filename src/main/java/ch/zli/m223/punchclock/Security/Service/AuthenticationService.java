package ch.zli.m223.punchclock.Security.Service;

import ch.zli.m223.punchclock.domain.User;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.ForbiddenException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class AuthenticationService {

    @Inject
    JsonWebToken jwt;

    @Inject
    EntityManager entityManager;

    public String GenerateValidJwtToken(User user) {
        String token =
                Jwt.issuer("https://zli.ch/issuer")
                        .upn(user.getUsername())
                        // .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .groups(new HashSet<>(getRolesFromUser(user)))
                        .claim("id", user.getId())
                        .expiresIn(Duration.ofHours(1))
                        .sign();
        return token;
    }

    private List<String> getRolesFromUser(User user) {
        return user.getRole().stream().map(role -> role.getName()).collect(Collectors.toList());
    }

    public long getIdFromJWT() {
        String idProperty = jwt.getClaim("id").toString();
        if (idProperty == null) {
            throw new RuntimeException("There wasn't an id in the jwt...");
        }
        long id = Long.parseLong(idProperty);
        checkJWT();
        return id;
    }

    public void checkJWT() {
        String idProperty = jwt.getClaim("id").toString();
        long id = Long.parseLong(idProperty);
        if (entityManager.find(User.class, id) == null) {
            throw new ForbiddenException();
        }
    }

}

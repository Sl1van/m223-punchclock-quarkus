package ch.zli.m223.punchclock.Security.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.jwt.JsonWebToken;

import ch.zli.m223.punchclock.domain.User;
import io.smallrye.jwt.build.Jwt;

@RequestScoped
public class AuthenticationService {
    @Inject
    JsonWebToken jwt; 
    
    public String GenerateValidJwtToken(User user){
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

    // TODO: delete before release!!!
    // public boolean checkValidJwtToken(SecurityContext ctx){
    //     String name;
    //     if (ctx.getUserPrincipal() == null) {
    //         name = "anonymous";
    //     } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
    //         throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
    //     } else {
    //         name = ctx.getUserPrincipal().getName();
    //     }
    //     jwt.getClaim("id")
    //     // return String.format("hello + %s,"
    //     //     + " isHttps: %s,"
    //     //     + " authScheme: %s,"
    //     //     + " hasJWT: %s",
    //     //     name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    //     return false;
    // }

    // private boolean hasJwt() {
    //     return jwt.getClaimNames() != null;
    // }



    private List<String> getRolesFromUser(User user){
        return user.getRole().stream().map(role -> role.getName()).collect(Collectors.toList());
    }

}

package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Role;
import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public User createUser(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            setDefaultUserRoles(user);
        }
        entityManager.persist(user);
        return user;
    }

    public User matchCredentials(String username, String password) {
        TypedQuery<User> tq = entityManager.createQuery("from User WHERE username=?1", User.class);
        User result;
        try {
            result = tq.setParameter(1, username).getSingleResult();
        } catch (NoResultException e) {
            throw new BadRequestException("No User was found with the following username: " + username);
        }
        if (result == null) {
            throw new BadRequestException("No User was found with the following username: " + username);
        } else if (!result.getPassword().equals(password)) {
            throw new RuntimeException("passwords do not match");
        }
        return result;
    }

    private void setDefaultUserRoles(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(entityManager.find(Role.class, 1L));
        roles.add(entityManager.find(Role.class, 2L));
        user.setRole(roles);
    }
}

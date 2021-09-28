package ch.zli.m223.punchclock.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Role;
import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public User createUser(User user) {
        if(user.getRole()==null ||user.getRole().isEmpty()){
            setDefaultUserRoles(user);   
        }
        entityManager.persist(user);
        return user;
    }
    private void setDefaultUserRoles(User user){
        List<Role> roles = new ArrayList<>();
        roles.add(entityManager.find(Role.class, 1L));
        roles.add(entityManager.find(Role.class, 2L));
        user.setRole(roles);
    }
}

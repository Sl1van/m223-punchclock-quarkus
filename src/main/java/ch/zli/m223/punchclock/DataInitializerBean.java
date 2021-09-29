package ch.zli.m223.punchclock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Role;
import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class DataInitializerBean {
    @Inject
    EntityManager em;
    
    @Transactional
    public void initData(){
        // init roles
        Role admin = new Role();
        admin.setName("admin");
        em.persist(admin);
        Role userRole = new Role();
        userRole.setName("user");
        em.persist(userRole);

        // init users
        User user = new User();
        // user.setId(1L);
        user.setUsername("TestUser");
        user.setPassword("password");
        List<Role> roles = new ArrayList<>();
        roles.add(em.find(Role.class, 1L));
        roles.add(em.find(Role.class, 2L));
        user.setRole(roles);
        em.persist(user);

        Category workActivity = new Category();
        workActivity.setName("work");
        em.persist(workActivity);
        Category holidayActivity = new Category();
        holidayActivity.setName("holiday");
        em.persist(holidayActivity);
        
        Entry entry = new Entry();
        entry.setCategory(em.find(Category.class, 1L));
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.now());
        entry.setUser(em.find(User.class, 1L));
        em.persist(entry);
        
        Entry entry2 = new Entry();
        entry2.setCategory(em.find(Category.class, 2L));
        entry2.setCheckIn(LocalDateTime.now());
        entry2.setCheckOut(LocalDateTime.now());
        entry2.setUser(em.find(User.class, 1L));
        em.persist(entry2);
    }
}

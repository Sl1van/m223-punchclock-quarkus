package ch.zli.m223.punchclock.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;

import ch.zli.m223.punchclock.Security.Service.AuthenticationService;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;
    
    @Inject
    private AuthenticationService authenticationService;


    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        User userCalling = entityManager.find(User.class, authenticationService.getIdFromJWT());
        entry.setUser(userCalling);
        entityManager.persist(entry);
        return entry;
    }

    public List<Entry> findAll() {
        TypedQuery<Entry> query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }

    public List<Entry> findEntries(long userId) {
        TypedQuery<Entry> query = entityManager.createQuery("FROM Entry where user_id= ?1", Entry.class);
        return query.setParameter(1, userId).getResultList();
    }

    @Transactional
    public Entry deleteEntry(long id) {
        Entry entry = entityManager.find(Entry.class, id);
        entityManager.remove(entry);
        
        return entry;
    }
    
    @Transactional
    public Entry editEntry(long id, Entry entry) {
        entityManager.merge(entry);    
        return entry;
    }
}

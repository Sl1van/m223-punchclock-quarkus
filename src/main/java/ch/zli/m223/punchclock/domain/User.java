package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    @ManyToMany
    @NotNull
    private List<Role> role;

    @Column
    @OneToMany(mappedBy="user_FK")
    private List<Entry> entries;
}
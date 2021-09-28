package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @OneToMany(mappedBy="entry_FK")
    private List<Entry> entries;
}

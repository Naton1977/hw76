package org.example.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "actors")
@ToString(exclude = "movieSet")
public class Actor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private int actorId;


    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "nationality")
    @NonNull
    private String nationality;

    @Column(name = "birth")
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date birth;

    @ManyToMany
    @JoinTable(name = "movie_actor",
    joinColumns = @JoinColumn(name = "actor_id"),
    inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movieSet;
}


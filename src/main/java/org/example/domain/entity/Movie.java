package org.example.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "Moviess")
@ToString(exclude = {"actorSet", "genreSet", "director"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @NonNull
    @Column(name = "title")
    private String title;

    @Column(name = "releas_year")
    @NonNull
    private Date releasYear;

    @Column(name = "rating", columnDefinition = "float")
    @NonNull
    private float rating;

    @Column(name = "plot", columnDefinition = "text")
    @NonNull
    private String plot;

    @Column(name = "movie_length")
    @NonNull
    @Temporal(TemporalType.TIME)
    private Date movieLength;

    @ManyToMany
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actorSet = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Director director;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genreSet = new HashSet<>();

    public void addGenreSet(Genre genre) {
        genreSet.add(genre);
    }

    public Set<Genre> getGenres (){
        return genreSet;
    }

    public void addActorSet(Actor actor) {
        actorSet.add(actor);
    }

    public void addDirector(Director director) {
        this.director = director;
    }

}

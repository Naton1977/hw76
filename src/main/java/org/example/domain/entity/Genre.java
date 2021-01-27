package org.example.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Genres")
@ToString(exclude = "movieSet")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;


    @NonNull
    @Column(name = "genre_name")
    private String genreName;

    @ManyToMany()
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movieSet = new HashSet<>();

    public void addMovieSet(Movie movie) {
        movieSet.add(movie);
    }
}
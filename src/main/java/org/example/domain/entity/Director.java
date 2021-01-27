package org.example.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Directors")
@ToString(exclude = "movieList")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private int directorId;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "Last_name")
    @NonNull
    private String lastName;

    @Column(name = "nationality")
    @NonNull
    private String nationality;

    @Column(name = "birth")
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date birth;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private Set<Movie> movieSet;

    public void addMovieSet(Movie movie) {
        movieSet.add(movie);
    }
}

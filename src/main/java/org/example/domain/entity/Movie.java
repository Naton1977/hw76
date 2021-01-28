package org.example.domain.entity;



import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Moviess")
public class Movie {

    public Movie(){

    }

    public Movie(String title, Date releasYear, float rating, String plot, Date movieLength) {
        this.title = title;
        this.releasYear = releasYear;
        this.rating = rating;
        this.plot = plot;
        this.movieLength = movieLength;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "releas_year")
    private Date releasYear;

    @Column(name = "rating", columnDefinition = "float")
    private float rating;

    @Column(name = "plot", columnDefinition = "text")
    private String plot;

    @Column(name = "movie_length")
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


    public void addActorSet(Actor actor) {
        actorSet.add(actor);
    }

    public void addDirector(Director director) {
        this.director = director;
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasYear() {
        return releasYear;
    }

    public void setReleasYear(Date releasYear) {
        this.releasYear = releasYear;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Date getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Date movieLength) {
        this.movieLength = movieLength;
    }

    public Set<Actor> getActorSet() {
        return actorSet;
    }

    public void setActorSet(Set<Actor> actorSet) {
        this.actorSet = actorSet;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Genre> getGenreSet() {
        return genreSet;
    }

    public void setGenreSet(Set<Genre> genreSet) {
        this.genreSet = genreSet;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", releasYear=" + releasYear +
                ", rating=" + rating +
                ", plot='" + plot + '\'' +
                ", movieLength=" + movieLength +
                ", actorSet=" + actorSet +
                ", director=" + director +
                ", genreSet=" + genreSet +
                '}';
    }
}

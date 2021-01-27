package org.example.domain.composite;

import org.example.domain.dao.ActorDao;
import org.example.domain.dao.DirectorDao;
import org.example.domain.dao.GenreDao;
import org.example.domain.dao.MovieDao;
import org.example.domain.entity.Actor;
import org.example.domain.entity.Director;
import org.example.domain.entity.Genre;
import org.example.domain.entity.Movie;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.*;
import java.time.LocalDate;
import java.util.*;

public class Methods {
    private Scanner scanner;
    private ActorDao actorDao;
    private DirectorDao directorDao;
    private GenreDao genreDao;
    private MovieDao movieDao;
    private String genreName;
    private String directorFirstName;
    private String directorLastName;
    private String directorNationality;
    private String directorBirth;
    private String actorFirstName;
    private String actorLastName;
    private String actorNationality;
    private String actorBirth;
    private String movieTitle;
    private String moviePlot;
    private String mavieReleasYear;
    private String movieRating;
    private String movieLength;
    private String actorFullName;
    private String directorFullName;

    public Methods() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        actorDao = context.getBean(ActorDao.class);
        directorDao = context.getBean(DirectorDao.class);
        genreDao = context.getBean(GenreDao.class);
        movieDao = context.getBean(MovieDao.class);
        scanner = new Scanner(System.in);
    }

    public void addGenres() {
        System.out.println("Введите название нового жанра");
        genreName = scanner.nextLine();
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genreDao.save(genre);
    }

    public void editGenre() {
        boolean genrePresent = false;
        System.out.println("Список добавленных жанров");
        List<Genre> genres = genreDao.findAll();
        for (Genre genre : genres) {
            System.out.println(genre.getGenreName());
        }
        System.out.println("Введите название жанра который нужно редактировать");
        genreName = scanner.nextLine();
        for (Genre genre : genres) {
            if (genre.getGenreName().equals(genreName)) {
                System.out.println("Введите новое название жанра");
                String newGenreName = scanner.nextLine();
                genre.setGenreName(newGenreName);
                genreDao.update(genre);
                genrePresent = true;
                break;
            }
        }
        if (!genrePresent) {
            System.out.println("Такого жанра не существует !!!");
        }
    }

    public void findAllGenres() {
        System.out.println("Список добавленных жанров");
        List<Genre> genres = genreDao.findAll();
        for (Genre genre : genres) {
            System.out.println(genre.getGenreName());
        }
    }

    public void addNewDirector() throws ParseException {
        Date date = null;
        System.out.println("Введите имя режисера");
        directorFirstName = scanner.nextLine();
        System.out.println("Введите фамилию режисера");
        directorLastName = scanner.nextLine();
        System.out.println("Введите национальность директора");
        directorNationality = scanner.nextLine();
        System.out.println("Введите дату рождения режисера ( в формате год-месяц-день )");
        directorBirth = scanner.nextLine();
        if (directorBirth != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(directorBirth);
        }
        Director director = new Director();
        director.setFirstName(directorFirstName);
        director.setLastName(directorLastName);
        director.setNationality(directorNationality);
        director.setBirth(date);
        directorDao.save(director);
    }

    public void editDirectorData() throws ParseException {
        Date date = null;
        System.out.println("Список режисеров");
        List<Director> directors = directorDao.findAll();
        for (Director director : directors) {
            System.out.println(director.getFirstName() + " " + director.getLastName());
        }
        System.out.println("Введите имя и фамилию режисера данные которого нужно редактировть");
        directorFullName = scanner.nextLine();
        int index = directorFullName.indexOf(" ");
        directorFirstName = directorFullName.substring(0, index);
        directorLastName = directorFullName.substring(index + 1);
        for (Director director : directors) {
            if (director.getFirstName().equals(directorFirstName) && director.getLastName().equals(directorLastName)) {
                System.out.println("Введите новое имя режисера");
                directorFirstName = scanner.nextLine();
                System.out.println("Введите новую фамилию режисера");
                directorLastName = scanner.nextLine();
                System.out.println("Введите новую национальность директора");
                directorNationality = scanner.nextLine();
                System.out.println("Введите новую дату рождения режисера ( в формате год-месяц-день )");
                directorBirth = scanner.nextLine();
                if (directorBirth != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf.parse(directorBirth);
                }
                director.setFirstName(directorFirstName);
                director.setLastName(directorLastName);
                director.setNationality(directorNationality);
                director.setBirth(date);
                directorDao.update(director);
            }
        }
    }

    public void findAllDirector() {
        List<Director> directors = directorDao.findAll();
        System.out.println("Список всех режисеров");
        System.out.printf("%-15s| %-20s | %-15s | %-13s %n", "Имя", "Фамилия", "Нициональность", "Дата рождения");
        for (Director director : directors) {
            System.out.printf("%-15s| %-20s | %-15s | %-13s %n", director.getFirstName(), director.getLastName(), director.getNationality(), director.getBirth());
        }
    }

    public void addNewActor() throws ParseException {
        Date date = null;
        System.out.println("Введите имя актера");
        actorFirstName = scanner.nextLine();
        System.out.println("Введите фамилию актера");
        actorLastName = scanner.nextLine();
        System.out.println("Введите национальность актера");
        actorNationality = scanner.nextLine();
        System.out.println("Введите дату рождения актера ( в формате год-месяц-день )");
        actorBirth = scanner.nextLine();
        if (actorBirth != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(actorBirth);
        }
        Actor actor = new Actor();
        actor.setFirstName(actorFirstName);
        actor.setLastName(actorLastName);
        actor.setNationality(actorNationality);
        actor.setBirth(date);
        actorDao.save(actor);
    }

    public void editActorData() throws ParseException {
        Date date = null;
        System.out.println("Список актеров");
        List<Actor> actors = actorDao.findAll();
        for (Actor actor : actors) {
            System.out.println(actor.getFirstName() + " " + actor.getLastName());
        }
        System.out.println("Введите имя и фамилию актера данные которого нужно редактировть");
        actorFullName = scanner.nextLine();
        int index = actorFullName.indexOf(" ");
        actorFirstName = actorFullName.substring(0, index);
        actorLastName = actorFullName.substring(index + 1);
        for (Actor actor : actors) {
            if (actor.getFirstName().equals(actorFirstName) && actor.getLastName().equals(actorLastName)) {
                System.out.println("Введите новое имя актера");
                actorFirstName = scanner.nextLine();
                System.out.println("Введите новую фамилию актера");
                actorLastName = scanner.nextLine();
                System.out.println("Введите новую национальность актера");
                actorNationality = scanner.nextLine();
                System.out.println("Введите новую дату рождения актера ( в формате год-месяц-день )");
                actorBirth = scanner.nextLine();
                if (actorBirth != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf.parse(actorBirth);
                }
                actor.setFirstName(actorFirstName);
                actor.setLastName(actorLastName);
                actor.setNationality(actorNationality);
                actor.setBirth(date);
                actorDao.update(actor);
            }
        }
    }

    public void findAllActors() {
        List<Actor> actors = actorDao.findAll();
        System.out.println("Список всех актеров");
        System.out.printf("%-15s| %-20s | %-15s | %-13s %n", "Имя", "Фамилия", "Нициональность", "Дата рождения");
        for (Actor actor : actors) {
            System.out.printf("%-15s| %-20s | %-15s | %-13s %n", actor.getFirstName(), actor.getLastName(), actor.getNationality(), actor.getBirth());
        }
    }

    public void addNewMovie() throws ParseException {
        Date date1 = null;
        Date date2 = null;
        float rating = 0;
        System.out.println("Введите название фильма");
        movieTitle = scanner.nextLine();
        System.out.println("Введите дату премьеры (в формате год-месяц-день )");
        mavieReleasYear = scanner.nextLine();
        System.out.println("Введите рейтинг");
        movieRating = scanner.nextLine();
        System.out.println("Введите сюжет фильма");
        moviePlot = scanner.nextLine();
        System.out.println("Введите продолжительность фильма");
        movieLength = scanner.nextLine();
        if (mavieReleasYear != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date1 = sdf.parse(mavieReleasYear);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Movie movie = new Movie();
        movie.setTitle(movieTitle);
        movie.setReleasYear(date1);
        try {
            rating = Float.parseFloat(movieRating);
        } catch (Exception e) {
            System.out.println("Не верный формат рейтинга");
            e.printStackTrace();
        }
        movie.setRating(rating);
        movie.setPlot(moviePlot);
        if (movieLength != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                date2 = sdf.parse(movieLength);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        movie.setMovieLength(date2);
        movieDao.save(movie);
    }

    public void addGenreToMovies() {
        List<Movie> movies = movieDao.findAll();
        List<Genre> genres = genreDao.findAll();
        System.out.println("Введите название фильма для которого нужно добавить жанр");
        movieTitle = scanner.nextLine();
        for (Movie movie : movies) {
            if (movie.getTitle().equals(movieTitle)) {
                System.out.println("Введите развание жанра для фильма");
                genreName = scanner.nextLine();
                for (Genre genre : genres) {
                    if (genre.getGenreName().equals(genreName)) {
                        Movie movie1 = new Movie();
                        movie1.setTitle(movie.getTitle());
                        movie1.setMovieLength(movie.getMovieLength());
                        movie1.setPlot(movie.getPlot());
                        movie1.setRating(movie.getRating());
                        movie1.setReleasYear(movie.getReleasYear());
                        movie1.setMovieId(movie.getMovieId());
                        Genre genre1 = new Genre();
                        genre1.setGenreId(genre.getGenreId());
                        genre1.setGenreName(genre.getGenreName());
                        movie1.addGenreSet(genre1);
                        movieDao.update(movie1);
                    }
                }
            }
        }
    }

    public void addActorsToMovies() {
        List<Actor> actors = actorDao.findAll();
        List<Movie> movies = movieDao.findAll();
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
        System.out.println("Введите название фильма для которого нужно добавить актеров");
        movieTitle = scanner.nextLine();
        for (Movie movie : movies) {
            if (movie.getTitle().equals(movieTitle)) {
                System.out.println("Введите имя и фамилию актера которого нужно добавить к фильму");
                actorFullName = scanner.nextLine();
                int index = actorFullName.indexOf(" ");
                try {
                    actorFirstName = actorFullName.substring(0, index);
                    actorLastName = actorFullName.substring(index + 1);
                } catch (Exception e){
                    System.out.println("Введите имя и фамилию правильно !!!");
                    break;
                }

                for (Actor actor : actors) {
                    if (actor.getFirstName().equals(actorFirstName) && actor.getLastName().equals(actorLastName)) {
                        Movie movie1 = new Movie();
                        movie1.setTitle(movie.getTitle());
                        movie1.setMovieLength(movie.getMovieLength());
                        movie1.setPlot(movie.getPlot());
                        movie1.setRating(movie.getRating());
                        movie1.setReleasYear(movie.getReleasYear());
                        movie1.setMovieId(movie.getMovieId());
                        Actor actor1 = new Actor();
                        actor1.setActorId(actor.getActorId());
                        actor1.setNationality(actor.getNationality());
                        actor1.setBirth(actor.getBirth());
                        actor1.setLastName(actor.getLastName());
                        actor1.setFirstName(actor.getFirstName());
                        movie1.addActorSet(actor1);
                        movieDao.update(movie1);
                    }
                }
            }
        }
    }

    public void addDirectorToMovies() {
        List<Movie> movies = movieDao.findAll();
        List<Director> directors = directorDao.findAll();
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
        System.out.println("Введите название фильма для которого нужно добавить режисера");
        movieTitle = scanner.nextLine();
        for (Movie movie : movies) {
            if (movie.getTitle().equals(movieTitle)) {
                System.out.println("Введите имя и фамилию режисера которого нужно добавить к фильму");
                directorFullName = scanner.nextLine();
                int index = directorFullName.indexOf(" ");
                try{
                    directorFirstName = directorFullName.substring(0, index);
                    directorLastName = directorFullName.substring(index + 1);
                } catch (Exception e){
                    System.out.println("Введите имя и фамилию правильно !!!");
                    break;
                }
                for (Director director : directors) {
                    if (director.getFirstName().equals(directorFirstName) && director.getLastName().equals(directorLastName)) {
                        Movie movie1 = new Movie();
                        movie1.setTitle(movie.getTitle());
                        movie1.setMovieLength(movie.getMovieLength());
                        movie1.setPlot(movie.getPlot());
                        movie1.setRating(movie.getRating());
                        movie1.setReleasYear(movie.getReleasYear());
                        movie1.setMovieId(movie.getMovieId());
                        Director director1 = new Director();
                        director1.setDirectorId(director.getDirectorId());
                        director1.setNationality(director.getNationality());
                        director1.setBirth(director.getBirth());
                        director1.setLastName(director.getLastName());
                        director1.setFirstName(director.getFirstName());
                        movie1.addDirector(director1);
                        movieDao.update(movie1);
                    }
                }
            }
        }
    }

    public void showAllMovies() {
        List<Movie> movies = movieDao.findAll();
        String genre1 = null;
        String actor1 = null;
        System.out.printf("%-30s|%-15s|%-25s|%-20s %n", "Название фильма", "Жанры", "Рижисер", "Актеры");

    }

}

package org.example;

import org.example.domain.composite.Menu;
import org.example.domain.composite.Methods;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AddMoviesDatabase {
    private Methods methods;


    public AddMoviesDatabase() {
        methods = new Methods();

    }

    public void addDatabase() throws IOException, SQLException, ParseException {
        Menu menu = new Menu("Главное меню");

        Menu menu1 = new Menu("Панель жанров", context -> {

            Menu menu1_1 = new Menu("Главное меню панели жанров");

            Menu menu1p1 = new Menu("Добавить новый жанр", context1 -> {
                methods.addGenres();
            });

            Menu menu1p2 = new Menu("Редактировать жанр", context1 -> {
                methods.editGenre();
            });
            Menu menu1p3 = new Menu("Посмотреть список все жанров", context1 -> {
                methods.findAllGenres();
            });


            menu1_1.addSubMenu(menu1p1);
            menu1_1.addSubMenu(menu1p2);
            menu1_1.addSubMenu(menu1p3);
            do {
                menu1_1.print();
            } while (!menu1_1.action());

        });
        Menu menu2 = new Menu("Панель актеров", context -> {
            Menu menu2_2 = new Menu("Главное меню панели актеров");

            Menu menu2p1 = new Menu("Добавить нового актера", context1 -> {
                methods.addNewActor();
            });
            Menu menu2p2 = new Menu("Редактировать данные актера", context1 -> {
                methods.editActorData();
            });
            Menu menu2p3 = new Menu("Показатть список всех актеров", context1 -> {
                methods.findAllActors();
            });


            menu2_2.addSubMenu(menu2p1);
            menu2_2.addSubMenu(menu2p2);
            menu2_2.addSubMenu(menu2p3);

            do {
                menu2_2.print();
            } while (!menu2_2.action());

        });
        Menu menu3 = new Menu("Панель режисеров", context -> {
            Menu menu3_3 = new Menu("Главное меню панели режисеров");


            Menu menu3p1 = new Menu("Добавить нового режисера", context1 -> {
                methods.addNewDirector();
            });

            Menu menu3p2 = new Menu("Редактировать данные режисера", context1 -> {
                methods.editDirectorData();
            });
            Menu menu3p3 = new Menu("Показатть список всех режисеров", context1 -> {
                methods.findAllDirector();
            });

            menu3_3.addSubMenu(menu3p1);
            menu3_3.addSubMenu(menu3p2);
            menu3_3.addSubMenu(menu3p3);

            do {
                menu3_3.print();
            } while (!menu3_3.action());


        });
        Menu menu4 = new Menu("Панель фильмов", context -> {
            Menu menu4_4 = new Menu("Главное меню панели фильмов");

            Menu menu4p1 = new Menu("Добавить фильм", context1 -> {
                methods.addNewMovie();
            });
            Menu menu4p2 = new Menu("Добавить режисера к фильму", context1 -> {
                methods.addDirectorToMovies();
            });
            Menu menu4p3 = new Menu("Добавить актеров к фильму", context1 -> {
                methods.addActorsToMovies();
            });
            Menu menu4p4 = new Menu("Добавить жанры к фильму", context1 -> {
                methods.addGenreToMovies();
            });
            Menu menu4p5 = new Menu("Посмотреть список всех фильмов", context1 -> {
                methods.showAllMovies();
            });

            menu4_4.addSubMenu(menu4p1);
            menu4_4.addSubMenu(menu4p2);
            menu4_4.addSubMenu(menu4p3);
            menu4_4.addSubMenu(menu4p4);
            menu4_4.addSubMenu(menu4p5);


            do {
                menu4_4.print();
            } while (!menu4_4.action());


        });


        menu.addSubMenu(menu1);
        menu.addSubMenu(menu2);
        menu.addSubMenu(menu3);
        menu.addSubMenu(menu4);
        do {
            menu.print();
        } while (!menu.action());


    }
}

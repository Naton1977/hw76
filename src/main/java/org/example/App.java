package org.example;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;

public class App {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);


        AddMoviesDatabase addMoviesDatabase = new AddMoviesDatabase();
        addMoviesDatabase.addDatabase();
    }
}

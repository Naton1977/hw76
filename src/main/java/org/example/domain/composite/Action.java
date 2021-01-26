package org.example.domain.composite;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface Action<T> {
    void doIt(T context) throws IOException, SQLException, ParseException;
}

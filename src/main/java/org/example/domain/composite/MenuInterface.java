package org.example.domain.composite;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface MenuInterface {
    void make(Context context) throws IOException, SQLException, ParseException;

    void remove(Menu menu);

    void addSubMenu(Menu menu);
}

package Core.book.service;
import  model.Libro;

import java.sql.SQLException;

public interface BookService {

    Libro findById(String id) throws SQLException;
    String create(Libro entity) throws SQLException;
    void update(Libro entity) throws SQLException;
    void remove(Libro entity)throws SQLException;

}

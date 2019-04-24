package Core.book.service;

import Core.book.dao.BookDao;
import Core.book.dao.JdbcBookDao;
import model.Libro;

import java.sql.SQLException;

public class BookServiceImpl implements BookService {
    BookDao bookDao;

    public  BookServiceImpl() {
        bookDao  = new JdbcBookDao();
    }

    public String create(Libro entity) throws SQLException {
        return bookDao.create(entity);
    }


    public Libro findById(String id) throws SQLException {
        return bookDao.findById(id);
    }


    public void update(Libro entity) throws SQLException {
        bookDao.update(entity);
    }


    public void remove(Libro entity) throws SQLException {
        bookDao.remove(entity);
    }


}

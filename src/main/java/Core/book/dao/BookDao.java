package Core.book.dao;

import model.Libro;

import java.sql.SQLException;

public interface BookDao {

    /*
    * Este método recibe una string con el id el cual representa la primary key de la tabla Libros
    * de la base de datos y con base en esta recupera toda la información  de esa tupla y la
    * retorna en un objeto de tipo libro
    * */
    Libro findById(String id) throws SQLException;

    /*
    * Este método recibe un objeto de tipo Libro y con base a la información de este
    * inserta esos valores en la tabla Libros y retorna un string con el id de la tabla creada
    * */
    String create(Libro entity);

    /*
    * Este método recibe un objeto de tipo Libro y con base en este actualiza los atributos
    * de la tabla Libros de acuerdo con la primary key
    * */
    void update(Libro entity);

    /*
    * elimina de la base de datos la tupla que corresponda con la primary key del objeto Libro
    * es decir el ISBN
    * */
    void remove(Libro entity);


}

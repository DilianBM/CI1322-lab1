package Core.student.dao;
import model.Estudiante;

import java.sql.SQLException;

public interface StudentDao {
    /*
     * Este método recibe una string con el id el cual representa la primary key de la tabla Estudiantes
     * de la base de datos y con base en esta recupera toda la información  de esa tupla y la
     * retorna en un objeto de tipo Estudiante
     * */
    Estudiante findById(String id);

    /*
     * Este método recibe un objeto de tipo Estudiante y con base a la información de este
     * inserta esos valores en la tabla Estudiantes y retorna un string con el id de la tabla creada
     * */
    String create(Estudiante entity) throws SQLException;
    /*
     * Este método recibe un objeto de tipo Estudiante y con base en este actualiza los atributos
     * de la tabla Estudiantes, de acuerdo con la primary key.
     * */
    void update(Estudiante entity) throws SQLException;

    /*
     * elimina de la base de datos la tupla que corresponda con la primary key del objeto Estudiante
     * es decir el Carné
     * */
    void remove(Estudiante entity);
}

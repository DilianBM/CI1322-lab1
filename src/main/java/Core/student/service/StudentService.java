package Core.student.service;

import model.Estudiante;

import java.sql.SQLException;

public interface StudentService {

    Estudiante findById(String id);
    String create(Estudiante entity) throws SQLException;
    void update(Estudiante entity) throws SQLException;
    void remove(Estudiante entity);

}

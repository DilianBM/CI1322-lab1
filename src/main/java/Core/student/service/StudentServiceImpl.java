package Core.student.service;

import Core.student.dao.JdbcStudentDao;
import Core.student.dao.StudentDao;
import model.Estudiante;

import java.sql.SQLException;

public class StudentServiceImpl implements StudentService{
    StudentDao studentDao;

    public StudentServiceImpl ()  {
        studentDao= new JdbcStudentDao();

    }

    public Estudiante findById(String id) {
        return studentDao.findById(id);
    }


    public String create(Estudiante entity) throws SQLException {

        return studentDao.create(entity);
    }


    public void update(Estudiante entity) throws SQLException {
        studentDao.update(entity);
    }

    public void remove(Estudiante entity) {
        studentDao.remove(entity);
    }
}

package Core.student.dao;

import model.Estudiante;

import javax.swing.*;
import java.sql.*;

public class JdbcStudentDao implements StudentDao {

    Connection c;

    public JdbcStudentDao() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab1?autoReconnect=true&amp;useSSL=false&allowPublicKeyRetrieval=true", "root", "Ian24/02/95");//Crea la conexión con la BD
            System.out.println("Conexión realizada");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

    public Estudiante findById(String id) {
        Estudiante est = new Estudiante();//instancia que guardara los datos de la consulta
        try {
            Statement stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Estudiante WHERE Carne = " + "'" + id + "'" + ";");//ejecuta la consulta donde el Carne es igual al id recibido por parámetro
            if (rs.next()) {
                est.setNombre(rs.getString("Nombre"));//obtiene los valores de la consulta y los guarda en la instancia de Estudiante creada anteriormente
                est.setApellido(rs.getString("Apellido"));
                est.setCarne(rs.getString("Carne"));
                est.setCorreo(rs.getString("Correo"));
            } else {

                System.out.println("ERROR no existe información en la BD con ese ID:  " + id);

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return est;//devuelve instancia de Estudiante con los valores leídos en la consulta
    }

    public String create(Estudiante entity) {
        Statement st;
        String ID = "";
        try {
            String insert = "insert into Estudiante values " + "('" + entity.getNombre() + "','" +
                    entity.getApellido() + "','" + entity.getCarne() + "','" + entity.getCorreo() + "')";
            st = c.createStatement();
            st.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                ID = generatedKeys.getString(1);
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error", "KEY Duplicada", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        return ID;
    }


    public void update(Estudiante entity) {
        String update = "UPDATE Estudiante SET " + "Nombre" + "=" + "'" + entity.getNombre() + "'" + "," + "Apellido =" + "'" + entity.getApellido() + "'," + "Correo =" + "'" + entity.getCorreo() + "'" + " WHERE Carne=" + "'" + entity.getCarne() + "'" + ";";
        System.out.println(update);
        Statement st;
        try {
            st = c.createStatement();
            st.executeUpdate(update);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error", "KEY Duplicada", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void remove(Estudiante entity) {
        Statement st;
        String update = "Delete From Estudiante Where " + "Carne " + "=" + " '" + entity.getCarne() + "'" + ";";
        try {
            st = c.createStatement();
            st.executeUpdate(update);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar el elemento\n" + "Error: ", "Error en la operación", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}

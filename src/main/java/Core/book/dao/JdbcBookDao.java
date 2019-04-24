package Core.book.dao;

import model.Libro;

import javax.swing.*;
import java.sql.*;

public class JdbcBookDao implements BookDao {
    Connection con;

    public JdbcBookDao() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/lab1?useSSL=false&allowPublicKeyRetrieval=true", "root", "Ian24/02/95");//REALIZA LA CONEXIÓN
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


    public String create(Libro entity) {
        ResultSet generatedKeys;
        String ID = "";
        try {
            String insert = "INSERT INTO Libros VALUES" + "('" + entity.getISBN() + "','" +
                    entity.getEditorial() + "'," + entity.getAnno() + ",'" + entity.getTitulo() + "')" + ";";//define lo que se va a insertar con los valores de la instancia libro que se recibe como parametro
            PreparedStatement st = null;
            st = con.prepareStatement(insert);
            st.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);//ejecuta la insercion de la tupla en la tabla Libros
            generatedKeys = st.getGeneratedKeys();//Genera el ID
            if (generatedKeys.next()) {
                ID = generatedKeys.getString(1);//Se extrae el ID
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "ERROR", "KEY Duplicada", JOptionPane.ERROR_MESSAGE);//maneja la excepción de tuplas con la misma primary key para evitar tuplas con la misma primary key
            } else
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ID;
    }


    public Libro findById(String id) throws SQLException {
        Libro lib = new Libro();//instancia que guardara los datos de la consulta
        ResultSet rs;
        String consulta = "SELECT * FROM Libros WHERE ISBN = " + "'" + id + "'" + ";";//Genera la consulta con el id recibido por parametro
        try {
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(consulta);
            rs = stmt.executeQuery(consulta);//ejecuta la consulta
            if (rs.next()) {
                if (rs.getRow() > 0) {


                    lib.setISBN(rs.getString("ISBN"));//obtiene los datos que genera la consulta anterior y los guarda en una instancia de libro
                    lib.setEditorial(rs.getString("Editorial"));
                    lib.setTitulo(rs.getString("Titulo"));
                    lib.setAnno(rs.getInt("Anno"));
                }
            }else{

                System.out.println("ERROR no existe información en la BD con ese ID:  "+id);

            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return lib;//retorna en esta instancia todos los valores consultados de acuerdo con el id
    }


    public void update(Libro entity) {
        Statement st;
        String update = "UPDATE Libros SET " + "Editorial" + "=" + "'" + entity.getEditorial() + "'" + "," + "Anno =" + entity.getAnno() + "," + "Titulo =" + "'" + entity.getTitulo() + "'" + " WHERE ISBN=" + "'" + entity.getISBN() + "'" + ";";//Prepara la actualización de la tabla de donde la primary key corresponda al valor del ISBN que contiene la instancia de libro que recibe como argumento
        try {
            st = con.createStatement();
            st.executeUpdate(update);//ejecuta la actualización en la BD
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "ERROR", "KEY Duplicada", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void remove(Libro entity) {
        Statement st;
        String update = "Delete From Libros Where " + "ISBN " + "=" + " '" + entity.getISBN() + "'" + ";";//Elimina de la base de datos la tupla cuya primary key corresponda al ISBN del atributo del objeto que se recibe como parámetro
        try {
            st = con.createStatement();
            st.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }


}


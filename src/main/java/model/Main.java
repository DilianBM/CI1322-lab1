package model;

import Core.book.dao.JdbcBookDao;
import build.DataLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        //System.out.println("hola mundo");
       /* DataLoader dataLoader = new DataLoader();
        JdbcBookDao objeto = new JdbcBookDao();
        Libro lbr = new Libro();
        Estudiante est = new Estudiante();
        lbr.setISBN("978-84-693-0146-3");
        lbr.setAnno(2007);
        lbr.setTitulo("Nuevo");
        lbr.setEditorial("NEW EDIT");
        objeto.remove(lbr);
        //objeto.update(lbr);
        // lbr=objeto.findById("978-84-245-0118-3");
        // System.out.println(lbr.anno+" "+ lbr.getISBN()+" "+ lbr.getTitulo()+ " "+ lbr.getEditorial());



        est.setCorreo("Dilian@");
        est.setCarne("B50800");
        est.setNombre("Dil");
        est.setApellido("Mora");
        JdbcStudentDao studentDao = new JdbcStudentDao();
        //  est=studentDao.findById("B46912");
        //System.out.println(est.getNombre()+" "+est.getApellido()+" "+est.getCorreo()+" "+est.getCarne());
        studentDao.remove(est);
        */
        DataLoader dataLoader = new DataLoader();
        try {
            dataLoader.cargarXLS();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JdbcBookDao objeto = new JdbcBookDao();
        Libro lbr = new Libro();
        String nombre;
        System.out.println("Ingrese el id(ISBN) de un libro");
        Scanner teclado = new Scanner(System.in);
        nombre = teclado.nextLine();

        lbr = objeto.findById(nombre);
        System.out.println(lbr.getISBN() + " " + lbr.getEditorial() + " " + lbr.getTitulo() + " " + lbr.getAnno());

    }
}

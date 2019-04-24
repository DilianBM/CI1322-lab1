package build;

import Core.book.service.BookServiceImpl;
import Core.student.service.StudentServiceImpl;
import model.Estudiante;
import model.Libro;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;


public class DataLoader {
    Estudiante student = new Estudiante();
    Libro lib = new Libro();

    BookServiceImpl bookService;
    StudentServiceImpl studentService;

    public DataLoader() {
        bookService = new BookServiceImpl();
        studentService = new StudentServiceImpl();

    }


    public void cargarLibros(HSSFSheet libros) throws SQLException {
        Iterator<Row> rowIterator = libros.iterator();//iterador sobre las filas de la hoja
        Row row;
        int numfila = -1;
        row = rowIterator.next();
        while (rowIterator.hasNext()) {
            numfila++;
            row = rowIterator.next();
            // Obtenemos el iterator que permite recorrer todas las celdas de una fila
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell celda;

            int contador = 0;

            while (cellIterator.hasNext()) {

                celda = cellIterator.next();

                if (contador == 0) {
                    lib.setISBN(celda.getStringCellValue());//Tomamos el valor de la celda y lo guardamos en un atributo de la instancia lib
                } else {
                    if (contador == 1) {
                        lib.setEditorial(celda.getStringCellValue());
                    } else {
                        if (contador == 2) {
                            lib.setAnno((int) celda.getNumericCellValue());
                        } else {
                            lib.setTitulo(celda.getStringCellValue());
                        }
                    }

                }

                contador++;

            }
            bookService.create(lib);
        }
    }


    public void cargarEstudiantes(HSSFSheet estudiantes) throws SQLException {
        Iterator<Row> rowIterator = estudiantes.iterator();//iterador sobre la filas de la hoja
        Row row;
        int numfila = -1;
        row = rowIterator.next();
        while (rowIterator.hasNext()) {
            numfila++;
            row = rowIterator.next();
            // Obtenemos el iterator que permite recorrer todas las celdas de una fila
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell celda;

            int contador = 0;

            while (cellIterator.hasNext()) {

                celda = cellIterator.next();

                if (contador == 0) {
                    student.setNombre(celda.getStringCellValue());//Tomamos el valor de la celda y lo guardamos en un atributo de la instancia student
                } else {
                    if (contador == 1) {
                        student.setApellido(celda.getStringCellValue());
                    } else {
                        if (contador == 2) {
                            student.setCarne(celda.getStringCellValue());

                        } else {
                            student.setCorreo(celda.getStringCellValue());
                        }
                    }

                }

                contador++;

            }
            studentService.create(student);
        }
    }


    public void cargarXLS() throws IOException {
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream("C:\\Tools\\maven\\compiladores-laboratorio1\\src\\main\\Resources\\Datos.xls");//ruta que contiene la dirección del archivo xls
            POIFSFileSystem fs = new POIFSFileSystem(fileIn);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);//obtiene la primera hoja del archivo  .xls
            cargarLibros(sheet);
            sheet = wb.getSheetAt(1);//obtiene la segunda hoja del archivo  .xls
            cargarEstudiantes(sheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
    }
}

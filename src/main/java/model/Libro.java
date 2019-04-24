package model;

public class Libro {
    String ISBN=new String();
    String editorial=new String();
    int anno;
    String titulo=new String();


    public int getAnno() {
        return anno;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setEditorial(String editorial) {

        this.editorial = editorial;
    }

    public void setISBN(String ISBN) {

        this.ISBN = ISBN;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

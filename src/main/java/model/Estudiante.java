package model;

public class Estudiante {
    String nombre;
    String apellido;
    String correo;
    String carne;

    public String getApellido() {
        return apellido;
    }

    public String getCarne() {
        return carne;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


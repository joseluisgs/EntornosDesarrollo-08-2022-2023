package mijava;

import org.jetbrains.annotations.NotNull;

public class Persona {
    private final int id;
    private String nombre;
    private String localidad;

    private Estudios estudios;

    public Persona(int id, String nombre, String localidad, Estudios estudios) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estudios = estudios;
    }

    public int getId() {
        return id;
    }

    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Estudios getEstudios() {
        return estudios;
    }

    public void setEstudios(Estudios estudios) {
        this.estudios = estudios;
    }

    @Override
    public String toString() {
        return "mijava.Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", estudios=" + estudios +
                '}';
    }
}

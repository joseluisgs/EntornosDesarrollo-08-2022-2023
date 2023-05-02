package es.joseluisgs.dam.models;

import es.joseluisgs.dam.utils.Formatter;

import java.util.Objects;

/**
 * Modelo. Clase que representa un país. POJO.
 * Se hace lo más limpia posible para que sirva a numerosos casos. No se implementa excepciones ni comparables
 * Es un POJO de "libro"
 */
public class Pais {
    private int id = 0;
    private String nombre;
    private String codigo;
    private String idioma;
    private String moneda;
    private String capital;
    private double presupuesto;

    public Pais() {

    }

    public Pais(String nombre, String codigo, String idioma, String moneda, String capital) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.idioma = idioma;
        this.moneda = moneda;
        this.capital = capital;
        // Voy a dejar el presupuesto aleatorio
        this.presupuesto = Math.random() * 1000000;
    }

    public Pais(int id, String nombre, String codigo, String idioma, String moneda, String capital, double presupuesto) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.idioma = idioma;
        this.moneda = moneda;
        this.capital = capital;
        this.presupuesto = presupuesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", idioma=" + idioma + ", moneda=" + moneda + ", capital=" + capital + ", presupuesto=" + Formatter.moneyParser(presupuesto) + '}';
    }


    public Pais nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Pais codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public Pais idioma(String idioma) {
        this.idioma = idioma;
        return this;
    }

    public Pais moneda(String moneda) {
        this.moneda = moneda;
        return this;
    }

    public Pais capital(String capital) {
        this.capital = capital;
        return this;
    }

    public Pais presupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais pais = (Pais) o;
        return id == pais.id && nombre.equals(pais.nombre) && codigo.equals(pais.codigo) && idioma.equals(pais.idioma) && moneda.equals(pais.moneda) && capital.equals(pais.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, codigo, idioma, moneda, capital);
    }

    @Override
    public Pais clone() {
        return new Pais(this.id, this.nombre, this.codigo, this.idioma, this.moneda, this.capital, this.presupuesto);
    }

    public String toString(String separator) {
        return id + separator + nombre + separator + codigo + separator + idioma + separator + moneda + separator + capital + separator + presupuesto;
    }
}


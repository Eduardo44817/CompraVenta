package org.example;

public class Propiedad {

    private int id;
    private String direccion;
    private double precio;
    private String propietario;
    private String estado;

    public Propiedad(int id, String direccion, double precio, String propietario, String estado) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
        this.propietario = propietario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Propiedad [id=" + id + ", direccion=" + direccion + ", precio=" + precio +
                ", propietario=" + propietario + ", estado=" + estado + "]";
    }
}

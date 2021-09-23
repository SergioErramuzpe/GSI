/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

/**
 *
 * @author Usuario
 */
public class Restaurante extends Local {

    private int precio;
    private int capacidadTotal;
    private int capacidadMesa;

    /**
     *
     * @param precio
     * @param capacidadTotal
     * @param capacidadMesa
     * @param nombreLocal
     * @param mDireccion
     * @param descripcion
     */
    public Restaurante(int precio, int capacidadTotal, int capacidadMesa, String nombreLocal, Direccion mDireccion, String descripcion) {
        super(nombreLocal, mDireccion, descripcion);
        this.precio = precio;
        this.capacidadTotal = capacidadTotal;
        this.capacidadMesa = capacidadMesa;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Restaurante{" + "precio=" + precio + ", capacidadTotal=" + capacidadTotal + ", capacidadMesa=" + capacidadMesa + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.precio;
        hash = 41 * hash + this.capacidadTotal;
        hash = 41 * hash + this.capacidadMesa;
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurante other = (Restaurante) obj;
        if (this.precio != other.precio) {
            return false;
        }
        if (this.capacidadTotal != other.capacidadTotal) {
            return false;
        }
        if (this.capacidadMesa != other.capacidadMesa) {
            return false;
        }
        return true;
    }

}

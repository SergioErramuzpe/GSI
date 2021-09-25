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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public int getCapacidadMesa() {
        return capacidadMesa;
    }

    public void setCapacidadMesa(int capacidadMesa) {
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

}

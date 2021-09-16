/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.Objects;

/**
 * Clase Dirección
 *
 * @author Usuario
 */
public class Direccion {

    private String localidad;
    private String provincia;
    private String calle;
    private int numero;

    /**
     * Constructor de la clase Dirección.
     *
     * @param localidad
     * @param provincia
     * @param calle
     * @param numero
     */
    public Direccion(String localidad, String provincia, String calle, int numero) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Método para imprimir por pantalla la clase Dirección.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Direccion{" + "Localidad=" + localidad + ", Provincia=" + provincia + ", Calle=" + calle + ", Numero=" + numero + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.localidad);
        hash = 97 * hash + Objects.hashCode(this.provincia);
        hash = 97 * hash + Objects.hashCode(this.calle);
        hash = 97 * hash + this.numero;
        return hash;
    }

    /**
     * Método para comparar dos objetos Direccion.
     *
     * @param obj
     * @return true si ambos objetos son iguales.
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
        final Direccion other = (Direccion) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        return true;
    }

}

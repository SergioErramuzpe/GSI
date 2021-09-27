/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BModel;

/**
 *
 * @author Erra
 */
public class ProgramException extends Exception {

    private int codigoError;
    
    public ProgramException(int codigoError) {
        super();
        this.codigoError = codigoError;
    }
    
    public int getCode() {
        return this.codigoError;
    }

    @Override
    public String getMessage () {
        
        switch (this.codigoError) {
            case 1: //Descripcion Local < 300 char
                return "Error: La descripción supera los 300 carcateres";
            case 2: //Nick Usuario < 3 char
                return "Error: El nick es menor a tres caracteres";
            case 3: //Edad Usuario > 14 int
                return "Error: La edad del usuario es menor a 14 años";
            case 4: //Estrellas Review < 0 y > 5 int
                return "Error: El nº de estrellas no se comprende entre los valores 0 y 5";    
            case 5: //Comentario Review o Contestacion > 500 char
                return "Error: El comentario supera los 500 caracteres";    
            case 6: //Descuento Reserva not between 0 y 1    
                return "Error: El descuento no es un real comprendido entre 0 y 1";
            case 7: //Password Usuario vacia  
                return "Error: La contraseña esta vacia";
            case 8: //Usuario no existe 
                return "Error: El usuario no existe";
            case 9: //Usuario existe
                return "Error: El usuario ya existe";
            case 10: //Review no existe
                return "Error: La review no existe";
            case 11: //Review contestada 
                return "Error: Review ya contestada";
            case 12: //Limite de propietarios 
                return "Error: Ya hay tres propietarios";
            case 13: 
                return "Error: No se ha podido eliminar el Usuario";
            case 14:
                return "Error: La Review ya existe";
            case 15:
                return "Error: No se ha podido añadir el Usuario";
            case 16:
                return "Error: No se ha podido añadir la Review";
            case 17:
                return "Error: No se ha podido eliminar la Review";
            case 18: 
                return "Error: No se ha podido verificar el Nick";
            default:
                return null; 
        }
       
        
    }
}

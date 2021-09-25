/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BSystem;

import BModel.Bar;
import BModel.Cliente;
import BModel.Contestacion;
import BModel.Direccion;
import BModel.Local;
import BModel.ProgramException;
import BModel.Propietario;
import BModel.Pub;
import BModel.Reserva;
import BModel.Reservable;
import BModel.Restaurante;
import BModel.Review;
import BModel.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Usuario
 */
public class BusinessSystem implements LeisureOffice {
    
    private BusinessSystem () {}
    
    public static BusinessSystem getBusinessSystem () {
        return new BusinessSystem();
    }
    
    public void generarBBDD () {
        
    }
    
    
    @Override
    public boolean nuevoUsuario(Usuario u) {
        
        //Podría faltar la excepción de usuario existente
        int edad = LocalDate.now().getYear() - u.getFechaNacimiento().getYear();
        
        try {
            if (edad < 14) {
                throw new ProgramException(3);
            } else if (u.getNick().length() < 3) {
                throw new ProgramException(2);
            } else if (u.getPassword().isEmpty()) {
                throw new ProgramException(7);
            }
            
            //Add a la lista
            return true;
            
        } catch (Exception ex) {
            
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminaUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        //Mismo try catch de nuevo usuario
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existeNick(String nick) {
        //Excepcion usuario inexistente
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario obtenerUsuario(String nick) {
        //Excepcion usuario inexistente
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevaReview(Review r) {
        //Excepción de 500 char y de estrellas --> se pueden añadir nuevas excepciones como la de que existe ya una review al mismo local/usuario/fecha
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminaReview(Review r) {
        //Excepción review no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        //Excepción review no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        //Excepción de 500 char --> se pueden añadir nuevas excepciones como la de que existe ya una contestación a la misma review por el mismo dueño y puede ser que no exista el dueño     
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tieneContestacion(Review r) {
        //Excepción contestación no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        //Excepción contestación no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        //Excepción contestación no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevoLocal(Local l) {
        //Excepciones por no cumplimiento de introducción correcta de datos y el local ==> hay un local en la misma dirección
        //Se realiza un equals con Bar y restaurante --> en el caso de que se cumpla, se añade un new Reservable a la lista, ya que se pueden reservar
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarLocal(Local l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local obtenerLocal(Direccion d) {
        //Excepción local no esiste
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        //mas de 3 propietarios
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        //Excepción propietario no dueño del local ***
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        //Excepción de que el nuevo local no cumple nombre, dirección....
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Review[] verReviews(Local l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        //Excepcion reservable no existe/la fecha es anterior a ahora/cliente no es cliente o no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva[] obtenerReservas(Cliente c) {
        //Excepcion cliente no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva[] obtenerReservas(Reservable r) {
        //Excepcion Reservable no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
        //Excepcion no existe fecha
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarReserva(Reserva r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminaContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

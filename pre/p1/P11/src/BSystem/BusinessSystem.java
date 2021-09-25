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
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Usuario
 */
public class BusinessSystem implements LeisureOffice, LookupService {

    private HashSet<Usuario> listaUsuarios;
    private ArrayList<Review> listaReviews;
    private ArrayList<Reserva> listaReserva;
    private ArrayList<Bar> listaBar;
    private ArrayList<Restaurante> listaRestaurante;
    private ArrayList<Local> listaLocal;
    private ArrayList<Pub> listaPub;

    private BusinessSystem() {
        listaUsuarios = new HashSet<Usuario>();
        listaReviews = new ArrayList<Review>();
        listaReserva = new ArrayList<Reserva>();
        listaBar = new ArrayList<Bar>();
        listaLocal = new ArrayList<Local>();
        listaRestaurante = new ArrayList<Restaurante>();
        listaPub = new ArrayList<Pub>();
    }

    public static BusinessSystem getBusinessSystem() {
        return new BusinessSystem();
    }

    public void generarBBDD() {

    }

    @Override
    public boolean nuevoUsuario(Usuario u) {
        int edad = LocalDate.now().getYear() - u.getFechaNacimiento().getYear();
        try {
            if (edad < 18) {
                throw new ProgramException(3);
            } else if (u.getNick().length() < 3) {
                throw new ProgramException(2);
            } else if (existeNick(u.getNick())) {
                throw new ProgramException(7);
            } else if (u.getPassword().isEmpty()) {
                throw new ProgramException(7);
            } else if (!listaUsuarios.add(u)) {
                throw new ProgramException(7);
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminaUsuario(Usuario u) {
        try {
            if (existeNick(u.getNick())) {
                if (listaUsuarios.remove(u)) {
                    return true;
                } else {
                    throw new Exception("No se ha podido eliminar.");
                }
            } else {
                throw new Exception("No se puede eliminar un usuario que no existe.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        boolean exito = false;
        int edad = LocalDate.now().getYear() - nuevoU.getFechaNacimiento().getYear();
        try {
            if (existeNick(u.getNick())) {
                if (edad < 18) {
                    throw new Exception("El usuario no puede ser menor de edad.");
                } else {
                    if (nuevoU.getNick().length() < 3) {
                        throw new Exception("El nuevo nick tiene menos de 3 carácteres.");
                    } else {
                        if (existeNick(nuevoU.getNick())) {
                            throw new Exception("El nuevo nick ya existe.");
                        } else {
                            u = nuevoU;
                            return true;
                        }
                    }
                }
            } else {
                throw new Exception("El usuario a modificar no existe.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean existeNick(String nick) {
        try {
            for (Usuario userIter : listaUsuarios) {
                if (userIter.getNick().equals(nick)) {
                    return true;
                }
            }
            throw new Exception("El nombre del usuario no existe");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String nick) {
        try {
            if (!existeNick(nick)) {
                throw new Exception("El nombre del usuario no existe.");
            } else {
                for (Usuario userIter : listaUsuarios) {
                    if (userIter.getNick().equals(nick)) {
                        return userIter;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
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
        //Excepcion No existe reserva
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
        //Excepcion No existe la contestacion
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float obtenerValoracionMedia(Local l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float obtenerValoracionMedia(Propietario p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

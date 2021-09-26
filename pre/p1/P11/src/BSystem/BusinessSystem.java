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
    private ArrayList<Contestacion> listaContestacion;
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
        listaContestacion = new ArrayList<>();
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
                throw new Exception("No se ha podido encontrar el usuario.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean nuevaReview(Review r) {
        try {
            if (r.getComentario().length() > 500) {
                throw new Exception("El comentario de la review supera el límite de caracteres.");
            }
            if (existeRewiew(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                throw new Exception("La review ya existe.");
            } else {
                if (listaReviews.add(r)) {
                    return true;
                } else {
                    throw new Exception("Error al añadir la review.");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminaReview(Review r) {
        //Excepción review no existe
        try {
            if (existeRewiew(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                if (tieneContestacion(r)) {
                    throw new Exception("No se puede eliminar una review con contestación.");
                } else {
                    if (listaReviews.remove(r)) {
                        return true;
                    } else {
                        throw new Exception("Error al eliminar.");
                    }
                }
            }
            throw new Exception("La review no existe.");
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        try {
            if (!existeNick(u.getNick())) {
                throw new Exception("El usuario de la review no existe.");
            }
            for (Review iterReview : listaReviews) {
                if (iterReview.getCliente().equals(u) && iterReview.getLocal().equals(l) && iterReview.getFechaReview().equals(ld)) {
                    return true;
                }
            }
            throw new Exception("La review no existe.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        try {
            if (c.getComentario().length() > 500) {
                throw new Exception("La contestación supera el límite de carácteres.");
            }
            if (!tieneContestacion(r)) {
                for (Review itemReview : listaReviews) {
                    if (itemReview.equals(r)) {
                        if (listaContestacion.add(c)) {
                            return true;
                        } else {
                            throw new Exception("Error al añadir la contestación.");
                        }
                    }
                }
            }
            throw new Exception("No se ha encontrado la review.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tieneContestacion(Review r) {
        try {
            for (Contestacion contestacion : listaContestacion) {
                if (contestacion.getReview().equals(r)) {
                    throw new Exception("Esta review ya tiene una contestación.");
                }

            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        try {
            for (Contestacion contestacion : listaContestacion) {
                if (contestacion.getReview().equals(r)) {
                    return contestacion;
                }
            }
            throw new Exception("No existe la review.");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        try {
            if (listaContestacion.remove(c)) {
                return true;
            } else {
                throw new Exception("Error al eliminar la contestación-");
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean nuevoLocal(Local l) {
        try {
            if (listaLocal.contains(l)) {
                throw new Exception("El local ya existe");
            } else {
                if (!listaLocal.add(l)) {
                    throw new Exception("Error al añadir el local");
                } else {
                    return true;
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarLocal(Local l) {
        try {
            if (!listaLocal.contains(l)) {
                throw new Exception("No se puede eliminar un local que no existe.");
            } else {
                if (!listaLocal.remove(l)) {
                    throw new Exception("Error al eliminar el local.");
                } else {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Local obtenerLocal(Direccion d) {
        try {
            for (Local local : listaLocal) {
                if (local.getmDireccion().equals(d)) {
                    return local;
                }
            }
            throw new Exception("No se ha podido encontrar el local.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        try {
            if(!l.propietariosLleno()){
                if(listaLocal.contains(l)){
                    if(existeNick(p.getNick())){
                        l.addPropietario(p);
                        return true;
                    }else{
                        throw new Exception("El usuario no existe.");
                    }
                }else{
                    throw new Exception("El local no existe.");
                }
            }
            throw new Exception("No se puede añadir más propietarios al local.");
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        try {
            if(listaLocal.contains(l)){
                    if(existeNick(p.getNick())){
                        //TODO
                        return true;
                    }else{
                        throw new Exception("El usuario no existe.");
                    }
                }else{
                    throw new Exception("El local no existe.");
                }
        } catch (Exception e) {
            return false;
        }
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

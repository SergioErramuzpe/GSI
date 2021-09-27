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
import java.util.List;

/**
 *
 * @author Usuario
 */
public class BusinessSystem implements LeisureOffice, LookupService {

    private HashSet<Usuario> listaUsuarios;
    private HashSet<Contestacion> listaContestacion;
    private HashSet<Review> listaReviews;
    private HashSet<Reserva> listaReserva;
    private HashSet<Local> listaLocal;

    private BusinessSystem() {
        listaUsuarios = new HashSet<>();
        listaReviews = new HashSet<>();
        listaReserva = new HashSet<>();
        listaLocal = new HashSet<>();
        listaContestacion = new HashSet<>();
    }

    public static BusinessSystem getBusinessSystem() {
        return new BusinessSystem();
    }

    @Override
    public boolean nuevoUsuario(Usuario u) {
        
        int edad = LocalDate.now().getYear() - u.getFechaNacimiento().getYear();
        
        try {
            
            if (edad < 14) {
                throw new ProgramException(3);
            } else if (u.getNick().length() < 3) {
                throw new ProgramException(2);
            } else if (existeNick(u.getNick())) {
                throw new ProgramException(8);
            } else if (u.getPassword().isEmpty()) {
                throw new ProgramException(7);
            } else if (!listaUsuarios.add(u)) {
                throw new ProgramException(15);
            }
                
            return true;
            
        } catch (ProgramException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminaUsuario(Usuario u) {
        
        try {
            
            if (existeNick(u.getNick())) 
                if (!listaUsuarios.remove(u)) 
                    throw new ProgramException(13);
                
            return true;
                        
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
            
        }

    }

    @Override
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        
        /*Si no se elimina usuario, falla, si no se añade el nuevo, falla, y 
        en el caso de que se haya borrado el antiguo usuario, pero no se haya
        podido introducir el nuevo, en la clausula catch se vuelve a añadir al
        hash el antiguo usuario*/
        try {
            if (!eliminaUsuario(u))
                throw new ProgramException(13);
            else if (!nuevoUsuario(nuevoU))
                throw new ProgramException(15);

            return true;
        
        } catch (ProgramException ex) {
                
            nuevoUsuario(u); 
            return false;
        }

    }

    @Override
    public boolean existeNick(String nick) {
        
        /*En el caso de no encontrar el nick salta la excepcion*/
        
        try {
            
            if (listaUsuarios.stream().anyMatch((user) -> (nick.equals(user.getNick())))) {
                return true;
            }
            
            throw new ProgramException(18);
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String nick) {
        try {
            if (!existeNick(nick)) {
                throw new ProgramException(18);
                
            } else {
                
                for (Usuario user : listaUsuarios) {
                    if (user.getNick().equals(nick)) 
                        return user;
                }
                
                throw new ProgramException(19);
            }
        } catch (ProgramException ex) {
            
            if (ex.getCode() == 19)
                System.out.println(ex.getMessage());
            
            return null;
        }
    }

    @Override
    public boolean nuevaReview(Review r) {
        try {
            if (r.getComentario().length() > 500) 
                throw new ProgramException(5);
                
            else if (existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) 
                throw new ProgramException(14);
                
            else if (!listaReviews.add(r)) 
                throw new ProgramException(16);

            return true;
           
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminaReview(Review r) {
        try {
            
            if (!existeReview(r.getCliente(), r.getLocal(), r.getFechaReview()))
                throw new ProgramException(10);
            
            else if (!listaReviews.remove(r))
                throw new ProgramException(17);
                
            return true;
             
        } catch (ProgramException ex) {
            
            if (ex.getCode() == 17)
                System.out.println(ex.getMessage());
            
            return false;
        }
    }

    @Override
    public boolean existeReview(Usuario u, Local l, LocalDate ld) {
        
        try {
            
            if (!existeNick(u.getNick())) 
                throw new ProgramException(18);
            
            if (listaReviews.stream().anyMatch((review) -> (review.getCliente().equals(u) && review.getLocal().equals(l) && review.getFechaReview().equals(ld)))) 
                return true;
            
            throw new ProgramException(10);
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() == 10)
                System.out.println(ex.getMessage());
            
            return false;
        }
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        
        try {
            
            if (c.getComentario().length() > 500) 
                throw new ProgramException(5);
            else if (tieneContestacion(r))
                throw new ProgramException(11);
            
            for (Review review : listaReviews) 
                if (review.equals(r) && listaContestacion.add(c)) 
                    return true;
                    
            throw new ProgramException(20);
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() != 11)
                System.out.println(ex.getMessage());
            
            return false;
        }
        
    }

    @Override
    public boolean tieneContestacion(Review r) {
        try {
            
            if (!existeReview(r.getCliente(),r.getLocal(),r.getFechaReview()))
                throw new ProgramException(10);
          
            for (Contestacion contestacion : listaContestacion) 
                if (contestacion.getReview().equals(r))     
                    throw new ProgramException(11);
   
            return false;
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() == 11)
                System.out.println(ex.getMessage());
            
            return true;
        }
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        try {
            
            if (!tieneContestacion(r))
                throw new ProgramException(21);
            
            for (Contestacion contestacion : listaContestacion) 
                if (contestacion.getReview().equals(r)) 
                    return contestacion;
          
            throw new ProgramException(22);
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
            
        }
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        
        try {
            
            if (!listaContestacion.contains(c))
                throw new ProgramException(23);
            else if (!listaContestacion.remove(c))
                throw new ProgramException(24);
            
            return true;
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
            
        }
        
    }

    @Override
    public boolean nuevoLocal(Local l) {
        
        try {
            
            /*Se hace una busqueda con for y no contains, porque lo interesante es
            saber si hay un local en la misma direccion*/
          
            for (Local local:listaLocal) 
                if (local.equals(l))
                    throw new ProgramException(25);
            
            if (!listaLocal.add(l))
                throw new ProgramException(26);
            
            return true;
            

        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean eliminarLocal(Local l) {
        
        try {
            
            if (!listaLocal.contains(l)) 
                throw new ProgramException(27);
            else if (!listaLocal.remove(l))
                throw new ProgramException(29);
             
            return true;
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
        }
        
    }

    @Override
    public Local obtenerLocal(Direccion d) {
        
        try {
            
            for (Local local : listaLocal) 
                if (local.getmDireccion().equals(d)) 
                    return local;
                
            throw new ProgramException(27);
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        
        try {
            
            if (!listaLocal.contains(l))
                throw new ProgramException(27);
            else if (l.propietariosLleno()) 
                throw new ProgramException(12);
            else if (!existeNick(p.getNick()))
                throw new ProgramException(8);
            else if (!l.addPropietario(p))
                throw new ProgramException(30);
            
            return true;

        } catch (ProgramException ex) {
            
            if (ex.getCode() != 8)
                System.out.println(ex.getMessage());
            
            return false;
        }
        
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        try {
            
            if (!listaLocal.contains(l))
                throw new ProgramException(27);
            else if (l.propietariosLleno()) 
                throw new ProgramException(12);
            else if (!existeNick(p.getNick()))
                throw new ProgramException(8);
            else if (!l.deletePropietario(p))
                throw new ProgramException(31);
            
            return true;
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() != 8)
                System.out.println(ex.getMessage());

            return false;
        }
    }

    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        
        try {
            
            if (!eliminarLocal(viejoL))
                throw new ProgramException(29);
            else if (!nuevoLocal(nuevoL)) 
                throw new ProgramException(26);
            
            return true;
            
        } catch (ProgramException ex) {
            
            nuevoLocal(viejoL);
            return false;
        }
        
    }
    
    @Override
    public Review[] verReviews(Local l) {
        
        try {
            
            if (!listaLocal.contains(l)) 
                throw new ProgramException(27);
            
            List<Review> localReviews = new ArrayList<>();
            
            for (Review review: listaReviews) 
                if (review.getLocal().equals(l))
                    localReviews.add(review);
            
            return localReviews.toArray(new Review[localReviews.size()]);
          
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {

        try {
            
            if (!existeNick(c.getNick())) 
                throw new ProgramException(8);
            else if (!listaLocal.contains((Local) r))
                throw new ProgramException(27);
            else if (ld.isBefore(LocalDate.now()) || (ld.equals(LocalDate.now()) && lt.isBefore(LocalTime.now())))
                throw new ProgramException(32);
            else if (!listaReserva.add(new Reserva(ld,c,r,lt)))
                throw new ProgramException(33);
            
            return true;
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() != 8)
                System.out.println(ex.getMessage());
            
            return false;
        }
    }

    @Override
    public Reserva[] obtenerReservas(Cliente c) {
        
        try {
            
            if (!existeNick(c.getNick())) 
                throw new ProgramException(8);
            
            List<Reserva> reservasCliente = new ArrayList();
            
            for (Reserva reserva: listaReserva) 
                if (reserva.getCliente().equals(c))
                    reservasCliente.add(reserva);
            
            return reservasCliente.toArray(new Reserva[reservasCliente.size()]);
          
        
        } catch (ProgramException ex) {
            
            return null;
        }

    }

    @Override
    public Reserva[] obtenerReservas(Reservable r) {

        try {
            
            if (!listaLocal.contains((Local)r)) 
                throw new ProgramException(27);
            
            List<Reserva> reservasLocal = new ArrayList();
            
            for (Reserva reserva: listaReserva) 
                if (reserva.getReservable().equals(r))
                    reservasLocal.add(reserva);
            
            return reservasLocal.toArray(new Reserva[reservasLocal.size()]);
          
        
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
    
    }

    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
    
        List<Reserva> reservasFecha = new ArrayList();
            
        for (Reserva reserva: listaReserva) 
            if (reserva.getLd().equals(ld))
                reservasFecha.add(reserva);
            
        return reservasFecha.toArray(new Reserva[reservasFecha.size()]);
        
    }

    @Override
    public boolean eliminarReserva(Reserva r) {
        
        try {
            if (!listaReserva.contains(r))
                throw new ProgramException(35);
            else if (!listaReserva.remove(r))
                throw new ProgramException(36);
            
            return true;
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return false;
        }     
    }

    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        try {
            ArrayList<Local> pLocal = new ArrayList<>();
            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)) {
                    pLocal.add(local);
                }
            }
            if (pLocal.isEmpty()) {
                throw new Exception("No se han encontrado locales.");
            } else {
                return pLocal.toArray(new Local[pLocal.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        try {
            ArrayList<Bar> pBar = new ArrayList<>();
            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)) {
                    if (local.getClass() == Bar.class) {
                        pBar.add(Bar.class.cast(local));
                    }
                }
            }
            if (pBar.isEmpty()) {
                throw new Exception("No se han encontrado locales.");
            } else {
                return pBar.toArray(new Bar[pBar.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        try {
            ArrayList<Restaurante> pRestaurante = new ArrayList<>();
            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)) {
                    if (local.getClass() == Restaurante.class) {
                        pRestaurante.add(Restaurante.class.cast(local));
                    }
                }
            }
            if (pRestaurante.isEmpty()) {
                throw new Exception("No se han encontrado locales.");
            } else {
                return pRestaurante.toArray(new Restaurante[pRestaurante.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        try {
            ArrayList<Pub> pPub = new ArrayList<>();
            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)) {
                    if (local.getClass() == Pub.class) {
                        pPub.add(Pub.class.cast(local));
                    }
                }
            }
            if (pPub.isEmpty()) {
                throw new Exception("No se han encontrado locales.");
            } else {
                return pPub.toArray(new Pub[pPub.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminaContestacion(Review r) {
        try {
            if (existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                for (Contestacion contestacion : listaContestacion) {
                    if (contestacion.getReview().equals(r)) {
                        if (listaContestacion.remove(contestacion)) {
                            return true;
                        } else {
                            throw new Exception("Error al eliminar.");
                        }
                    }
                }
                throw new Exception("No se ha encontado la con...");
            } else {
                throw new Exception("No se ha encontrado la review.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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

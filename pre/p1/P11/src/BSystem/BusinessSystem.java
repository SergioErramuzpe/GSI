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
                
            else if (existeRewiew(r.getCliente(), r.getLocal(), r.getFechaReview())) 
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
            
            if (!existeRewiew(r.getCliente(), r.getLocal(), r.getFechaReview()))
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
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        try {
            if (listaContestacion.contains(c)) {
                if (listaContestacion.remove(c)) {
                    return true;
                } else {
                    throw new Exception("Error al eliminar la contestación.");
                }
            } else {
                throw new Exception("No se ha encontrado la contestación a eliminar.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            if (!l.propietariosLleno()) {
                if (listaLocal.contains(l)) {
                    if (existeNick(p.getNick())) {
                        l.addPropietario(p);
                        return true;
                    } else {
                        throw new Exception("El usuario no existe.");
                    }
                } else {
                    throw new Exception("El local no existe.");
                }
            }
            throw new Exception("No se puede añadir más propietarios al local.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        try {
            if (listaLocal.contains(l)) {
                if (existeNick(p.getNick())) {
                    if (l.getListaPropietarios().remove(p)) {
                        return true;
                    } else {
                        throw new Exception("Error al eliminar el propietario");
                    }
                } else {
                    throw new Exception("El usuario no existe.");
                }
            } else {
                throw new Exception("El local no existe.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        //Excepción de que el nuevo local no cumple nombre, dirección....
        try {
            if (nuevoL.getDescripcion().length() <= 500) {
                if (listaLocal.contains(viejoL)) {
                    viejoL = nuevoL;
                    return true;
                } else {
                    throw new Exception("El viejo local no existe.");
                }
            } else {
                throw new Exception("La descripción del nuevo local...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public Review[] verReviews(Local l) {
        try {
            ArrayList<Review> pReviews = new ArrayList<>();
            if (listaLocal.contains(l)) {
                for (Review itemReview : listaReviews) {
                    if (itemReview.getLocal().equals(l)) {
                        pReviews.add(itemReview);
                    }
                }
                if (pReviews.isEmpty()) {
                    throw new Exception("Lista vacía...");
                } else {
                    return pReviews.toArray(new Review[pReviews.size()]);
                }
            } else {
                throw new Exception("El local...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        //Excepcion reservable no existe/la fecha es anterior a ahora/cliente no es cliente o no existe
        try {
            if (existeNick(c.getNick())) {
                //TODO
                return true;
            } else {
                throw new Exception("El cliente no existe");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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
        ArrayList<Reserva> pReservas = new ArrayList<>();
        try {
            for (Reserva reserva : listaReserva) {
                if (reserva.getLd().equals(ld)) {
                    pReservas.add(reserva);
                }
            }
            if (pReservas.isEmpty()) {
                throw new Exception("No se han podido encontrar reservas.");
            } else {
                return pReservas.toArray(new Reserva[pReservas.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminarReserva(Reserva r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            if (existeRewiew(r.getCliente(), r.getLocal(), r.getFechaReview())) {
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

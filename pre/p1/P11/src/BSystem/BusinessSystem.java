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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Clase BusinessSystem
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

    /**
     * Getter de la clase BussinessSystem
     * @return
     */

    public static BusinessSystem getBusinessSystem() {
        return new BusinessSystem();
    }
    
    // Función para crear un nuevoUsuario
    @Override
    public boolean nuevoUsuario(Usuario u) {
        
        int edad = u.obtenerEdad();
        
        try {
            
            /*Para eso se creo la clase ProgramException. Para simplificar esta
            clase además de que es más estético
            */
            
            if (edad < 14) {
                throw new ProgramException(3); //Error si menor de 14 años 
            } else if (u.getNick().length() < 3) {
                throw new ProgramException(2); //Error si nick con menos de 3 caracteres
            } else if (listaUsuarios.stream().anyMatch((user) -> (u.getNick().equals(user.getNick())))) {
                throw new ProgramException(9);
            } else if (u.getPassword().isEmpty()) {
                throw new ProgramException(7); //Error si la contaseña esta vacía
            } else if (!listaUsuarios.add(u)) {
                throw new ProgramException(15); //Error si no se ha podido añadir el usuario a la lista
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
                    throw new ProgramException(13);//Si no se ha podido eliminar el usuario
                
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
    public boolean existeNick (String nick) {
        
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
                throw new ProgramException(18); //Si no se ha podido verificar el nick
                
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
                throw new ProgramException(5); //Si supera los 500 caracteres da error
                
            else if (existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) 
                throw new ProgramException(14); //Salta el error si existe la review
                
            else if (!listaReviews.add(r)) 
                throw new ProgramException(16); //Error si no se puede añadir la review

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
                throw new ProgramException(10); //Error si no existe la review
            
            else if (!listaReviews.remove(r))
                throw new ProgramException(17); //Si no se ha podido eliminar la review
                
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
                throw new ProgramException(18);//Salta si no se puede verificar el Nick
            
            if (listaReviews.stream().anyMatch((review) -> (review.getCliente().equals(u) && review.getLocal().equals(l) && review.getFechaReview().equals(ld)))) 
                return true;
            
            throw new ProgramException(10); // Si no existe la review da error
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() == 10)
                System.out.println(ex.getMessage());
            
            return false;
        }
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        
        try {
            
            if (tieneContestacion(r))
                throw new ProgramException(11);
            else if (c.getComentario().length() > 500) 
                throw new ProgramException(5); 
            
            for (Review review : listaReviews) 
                if (review.equals(r) && listaContestacion.add(c)) 
                    return true;
                    
            throw new ProgramException(20); //Error si no se ha podido añadir la contestacion
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() != 11)
                System.out.println(ex.getMessage()); //Se crea la contestacion si la review no esta contestada
            
            return false;
        }
        
    }

    @Override
    public boolean tieneContestacion(Review r) {
        try {
            
            if (!existeReview(r.getCliente(),r.getLocal(),r.getFechaReview()))
                throw new ProgramException(10);//Error si no existe la review
          
            for (Contestacion contestacion : listaContestacion) 
                if (contestacion.getReview().equals(r))     
                    throw new ProgramException(11);//Error de que ya esta contestada
   
            return false;
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() == 11)
                System.out.println(ex.getMessage());//Solo si sí tiene una contestacion
            
            return true;
        }
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        try {
            
            if (!tieneContestacion(r))
                throw new ProgramException(21); //Review no contestada
            
            for (Contestacion contestacion : listaContestacion) 
                if (contestacion.getReview().equals(r)) 
                    return contestacion;
          
            throw new ProgramException(22);//No se puede obtener la contestacion de la review
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
            
        }
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        
        try {
            
            if (!listaContestacion.contains(c))
                throw new ProgramException(23);//Error de que no se encuentra la contestacion
            else if (!listaContestacion.remove(c))
                throw new ProgramException(24);//Si no se ha podido eliminar la contestacion, salta error
            
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
          
            for (Local local : listaLocal) 
                if (local.equalsInList(l))
                    throw new ProgramException(25);//Error de que ya existe un local en la misma direccion
            
            if (!listaLocal.add(l))
                throw new ProgramException(26); //No se puede añadir el local
            
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
                throw new ProgramException(27);//No existe el local
            else if (!listaLocal.remove(l))
                throw new ProgramException(29);//Error si no se puede eliminar el local
             
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
                
            throw new ProgramException(27); //El local no existe
            
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
            else if (!listaLocal.contains((Reservable) r)) {
                throw new ProgramException(27);
            } else if (ld.isBefore(LocalDate.now()) || (ld.equals(LocalDate.now()) && lt.isBefore(LocalTime.now())))
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
    public Local[] listarLocales (String ciudad, String provincia) {
        
        try {
            
            List<Local> localesFiltrados = new ArrayList<>();
            
            for (Local local : listaLocal) 
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)) 
                    localesFiltrados.add(local);

            if (localesFiltrados.isEmpty()) 
                throw new ProgramException(37);

                
            return localesFiltrados.toArray(new Local[localesFiltrados.size()]);
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        
        try {
            
            List<Bar> bares = new ArrayList<>();
            
            for (Local local : listaLocal) 
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad) &&
                        local.getClass().equals(Bar.class)) 
                   
                    bares.add((Bar)local);
            
            if (bares.isEmpty()) 
                throw new ProgramException(37);
            
            return bares.toArray(new Bar[bares.size()]);
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        
        try {
            
            List<Restaurante> restaurantes = new ArrayList<>();
            
            for (Local local : listaLocal) 
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad) &&
                        local.getClass().equals(Restaurante.class)) 
                    restaurantes.add((Restaurante)local);

            if (restaurantes.isEmpty())
                throw new ProgramException(37);

            return restaurantes.toArray(new Restaurante[restaurantes.size()]);

        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
        
    }

    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        
        try {
            
            List<Pub> pubs = new ArrayList<>();
            
            for (Local local : listaLocal) 
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad) &&
                        local.getClass().equals(Pub.class)) 
                    pubs.add((Pub)local);

            if (pubs.isEmpty())
                throw new ProgramException(37);

            return pubs.toArray(new Pub[pubs.size()]);
            
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminaContestacion(Review r) {
        
        try {
            
            if (!existeReview(r.getCliente(), r.getLocal(), r.getFechaReview()))
                throw new ProgramException(10);
            
            for (Contestacion contestacion : listaContestacion) 
                if (contestacion.getReview().equals(r)) {
                    if (!listaContestacion.remove(contestacion))
                        throw new ProgramException(38);
                    return true;
                }
           
            throw new ProgramException(22);
            
        } catch (ProgramException ex) {
            
            if (ex.getCode() != 10)
                System.out.println(ex.getMessage());
            return false;
        }
        
    }

    @Override
    public float obtenerValoracionMedia(Local l) {
        
        try {
            
            float media=0;
            
            ArrayList<Review> reviews = new ArrayList<>();
            ArrayList<Local>  locales = new ArrayList<>();
            
            if (!listaLocal.contains(l))
                throw new ProgramException(27);

            for (Review review : listaReviews) 
                if (l.equals(review.getLocal()))
                    media=media+review.getEstrellas();
  
            if (reviews.isEmpty()) 
                throw new ProgramException(39);

            return (float)(media/reviews.size());
                
        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            
            if (ex.getCode() == 27)
                return -1;
            
            return 0;
        }
    }

    @Override
    public float obtenerValoracionMedia(Propietario p) {
        
        try {
            
            if (!existeNick(p.getNick()))
                throw new ProgramException(8);
            
            float media=0;
            
            ArrayList<Local> locales = new ArrayList<>();

            for (Local local : listaLocal) 
                if (local.getListaPropietarios().contains(p))
                    media=obtenerValoracionMedia(local);

            return media/locales.size();
                
        } catch (ProgramException ex) {
            
            return -1;
        }
        
    }

    @Override
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta) {
        
        try {
            
            float media=0;
            
            ArrayList<Review> reviews = new ArrayList<>();
            ArrayList<Local>  locales = new ArrayList<>();
            
            if (!listaLocal.contains(l))
                throw new ProgramException(27);
 

            for (Review review : listaReviews) 
                if (l.equals(review.getLocal()))
                    if(review.getCliente().obtenerEdad() > edadEntre && review.getCliente().obtenerEdad() < edadHasta)
                        media=media+review.getEstrellas();

                
            if (reviews.isEmpty()) 
                throw new ProgramException(39);

            return media/reviews.size();

        } catch (ProgramException ex) {
            
            System.out.println(ex.getMessage());
            
            if (ex.getCode() == 27)
                return -1;
            
            return 0;
        }
        
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia) {
        
        List<Local> locales = new ArrayList<>();
        
        for (Local local : listaLocal) 
            if (local.getmDireccion().getProvincia().equals(provincia) &&
                    local.getmDireccion().getLocalidad().equals(ciudad))
                locales.add(local);
        
        class OrdenAscendenteValoracion implements Comparator<Local> {
            @Override
            public int compare(Local L1, Local L2) {
                return (int) (obtenerValoracionMedia(L2)-obtenerValoracionMedia(L1));
            }
        }

        Collections.sort(locales, new OrdenAscendenteValoracion());
        
        return locales.toArray(new Local[locales.size()]);
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String provincia) {
        
        List<Local> locales = new ArrayList<>();
        
        for (Local local : listaLocal) 
            if (local.getmDireccion().getProvincia().equals(provincia))
                locales.add(local);
        
        class OrdenAscendenteValoracion implements Comparator<Local> {
            @Override
            public int compare(Local L1, Local L2) {
                return (int) (obtenerValoracionMedia(L2)-obtenerValoracionMedia(L1));
            }
        }

        Collections.sort(locales, new OrdenAscendenteValoracion());
        
        return locales.toArray(new Local[locales.size()]);
    }

    @Override
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia) {
        
        List<Bar> bares = new ArrayList<>();
        
        for (Local local : listaLocal) 
            if (local.getmDireccion().getProvincia().equals(provincia) && 
                    local.getmDireccion().getLocalidad().equals(ciudad) &&
                    local.getClass().equals(Bar.class))
                bares.add((Bar)local);
        
        class OrdenAscendenteValoracion implements Comparator<Bar> {
            @Override
            public int compare(Bar b1, Bar b2) {
                return (int) (obtenerValoracionMedia(b2)-obtenerValoracionMedia(b1));
            }
            
            /*
            public Float compareFloat (Bar b1, Bar b2) {
                return (Float) (obtenerValoracionMedia(b2)-obtenerValoracionMedia(b1));
            }*/
        }

        Collections.sort(bares, new OrdenAscendenteValoracion());
        
        return bares.toArray(new Bar[bares.size()]);
        
    }

    @Override
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia) {
        
        List<Restaurante> restaurantes = new ArrayList<>();
        
        for (Local local : listaLocal) 
            if (local.getmDireccion().getProvincia().equals(provincia) && 
                    local.getmDireccion().getLocalidad().equals(ciudad) &&
                    local.getClass().equals(Restaurante.class))
                restaurantes.add((Restaurante)local);
        
        class OrdenAscendenteValoracion implements Comparator<Restaurante> {
            @Override
            public int compare(Restaurante r1, Restaurante r2) {
                return (int) (obtenerValoracionMedia(r2)-obtenerValoracionMedia(r1));
            }
        }

        Collections.sort(restaurantes, new OrdenAscendenteValoracion());
        
        return restaurantes.toArray(new Restaurante[restaurantes.size()]);
        
    }

    @Override
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia) {
        
        List<Pub> pubs = new ArrayList<>();
        
        for (Local local : listaLocal) 
            if (local.getmDireccion().getProvincia().equals(provincia) && 
                    local.getmDireccion().getLocalidad().equals(ciudad) &&
                    local.getClass().equals(Pub.class))
                pubs.add((Pub)local);
        
        class OrdenAscendenteValoracion implements Comparator<Pub> {
            @Override
            public int compare(Pub p1, Pub p2) {
                return (int) (obtenerValoracionMedia(p2)-obtenerValoracionMedia(p1));
            }
        }

        Collections.sort(pubs, new OrdenAscendenteValoracion());
        
        return pubs.toArray(new Pub[pubs.size()]);
    }
    
    
    //FLOAT EN COMPARATORS, TOSTRING EN CLASES, PROBAR TODO
}

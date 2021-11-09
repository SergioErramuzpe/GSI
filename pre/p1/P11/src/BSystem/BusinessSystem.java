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
import GSILabs.Serializable.XMLRepresentable;
import GSILabs.persistence.ODSPersistente;
import GSILabs.persistence.XMLParsingException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Clase BusinessSystem
 *
 * @author Usuario
 */
public class BusinessSystem implements LeisureOffice, LookupService, ODSPersistente, XMLRepresentable {

    private HashSet<Usuario> listaUsuarios;
    private HashSet<Contestacion> listaContestacion;
    private HashSet<Review> listaReviews;
    private HashSet<Reserva> listaReserva;
    private HashSet<Local> listaLocal;
    private SpreadSheet sp;

    private BusinessSystem() {
        
        /*carga de datos*/
        
        listaUsuarios = new HashSet<>();
        listaReviews = new HashSet<>();
        listaReserva = new HashSet<>();
        listaLocal = new HashSet<>();
        listaContestacion = new HashSet<>();
        
    }

    /**
     * Getter de la clase BussinessSystem por ser Singleton
     * @return
     */
    public static BusinessSystem getBusinessSystem() {
        return new BusinessSystem();
    }
    
    
    /**
     * Método que crea una BBDD instantánea para que la clase BusinessSystem se
     * pruebe en cualquier test
     */
    public void crearBBDD () {
         
        Direccion dir1 = new Direccion ("rueblo 1", "Provincia 1", "Calle 1", 1);
        Direccion dir2 = new Direccion ("Pueblo 2", "Provincia 2", "Calle 2", 2);
        Direccion dir3 = new Direccion ("Pueblo 3", "Provincia 3", "Calle 3", 3);
        Direccion dir4 = new Direccion ("Pueblo 4", "Provincia 4", "Calle 4", 4);
        Direccion dir5 = new Direccion ("Pueblo 5", "Provincia 5", "Calle 5", 5);
        Direccion dir6 = new Direccion ("Pueblo 6", "Provincia 6", "Calle 6", 6);
        
        String[] tag1 = {"español","tapas","carnes"};
        String[] tag2 = {"asiático","ramen"};
        
        Propietario propietario1 = new Propietario("nick4", "pw4", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(propietario1);
        Propietario propietario2 = new Propietario("nick5", "pw5", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(propietario2);
        Propietario propietario3 = new Propietario("nick6", "pw6", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(propietario3);
        Propietario propietario4 = new Propietario("nick7", "pw7", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(propietario4);      
        
        Local local1 = new Bar(tag1,"Local 1", dir1, "Bar español clásico");
        local1.addPropietario(propietario4);
        this.nuevoLocal(local1);
        Local local2 = new Bar(tag2,"Local 2", dir2, "Bar informal para comer ramen");
        local2.addPropietario(propietario1);
        this.nuevoLocal(local2);
        Local local3 = new Pub("Local 3", dir3, "Pub Irlandés");
        local3.addPropietario(propietario2);
        this.nuevoLocal(local3);
        Local local4 = new Pub("Local 4", dir4, "Disco-Pub");
        local4.addPropietario(propietario3);
        this.nuevoLocal(local4);
        Local local5 = new Restaurante(20,40,6,"Local 5", dir5, "Restaurante francés");
        local5.addPropietario(propietario1);
        this.nuevoLocal(local5);
        Local local6 = new Restaurante(15,80,10,"Local 6", dir6, "Restaurante Italiano");
        local6.addPropietario(propietario2);
        this.nuevoLocal(local6);
        
        Cliente cliente1 = new Cliente("nick8", "pw8", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(cliente1);
        Cliente cliente2 = new Cliente("nick9", "pw9", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(cliente2);
        Cliente cliente3 = new Cliente("nick10", "pw10", LocalDate.parse("2000-01-01"));
        this.nuevoUsuario(cliente3);
    }
    
    
    /**
     * Getter de el HashSet de locales (necesario para la práctica 2)
     * @return 
     */
    public HashSet<Local> getLocales () {
        return (HashSet<Local>) listaLocal;
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

            if (existeNick(u.getNick())) {
                if (!listaUsuarios.remove(u)) {
                    throw new ProgramException(13);//Si no se ha podido eliminar el usuario
                }
            }
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
            if (!eliminaUsuario(u)) {
                throw new ProgramException(13);
            } else if (!nuevoUsuario(nuevoU)) {
                throw new ProgramException(15);
            }

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
                throw new ProgramException(18); //Si no se ha podido verificar el nick

            } else {

                for (Usuario user : listaUsuarios) {
                    if (user.getNick().equals(nick)) {
                        return user;
                    }
                }

                throw new ProgramException(19);
            }
        } catch (ProgramException ex) {

            if (ex.getCode() == 19) {
                System.out.println(ex.getMessage());
            }

            return null;
        }
    }

    @Override
    public boolean nuevaReview(Review r) {
        try {
            if (r.getComentario().length() > 500) {
                throw new ProgramException(5); //Si supera los 500 caracteres da error
            } else if (existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                throw new ProgramException(14); //Salta el error si existe la review
            } else if (!listaReviews.add(r)) {
                throw new ProgramException(16); //Error si no se puede añadir la review
            }
            return true;

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminaReview(Review r) {
        try {

            if (!existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                throw new ProgramException(10); //Error si no existe la review
            } else if (!listaReviews.remove(r)) {
                throw new ProgramException(17); //Si no se ha podido eliminar la review
            }
            return true;

        } catch (ProgramException ex) {

            if (ex.getCode() == 17) {
                System.out.println(ex.getMessage());
            }

            return false;
        }
    }

    @Override
    public boolean existeReview(Usuario u, Local l, LocalDate ld) {

        try {

            if (!existeNick(u.getNick())) {
                throw new ProgramException(18);//Salta si no se puede verificar el Nick
            }
            if (listaReviews.stream().anyMatch((review) -> (review.getCliente().equals(u) && review.getLocal().equals(l) && review.getFechaReview().equals(ld)))) {
                return true;
            }

            throw new ProgramException(10); // Si no existe la review da error

        } catch (ProgramException ex) {

            if (ex.getCode() == 10) {
                System.out.println(ex.getMessage());
            }

            return false;
        }
    }
    
    public Review obtenerReview(Usuario u, Local l, LocalDate ld) {
        try {

            if (!existeReview(u,l,ld)) {
                throw new ProgramException(18);//Salta si no se puede verificar el Nick
            }
            for (Review r : listaReviews) {
                if (r.getCliente().equals(u) && r.getLocal().equals(l) && r.getFechaReview().equals(ld)) {
                    return (Review)r;
                }
            }
            return null;
        } catch (ProgramException ex) {

            if (ex.getCode() == 10) {
                System.out.println(ex.getMessage());
            }

            return null;
        }
        
    }

    public boolean nuevaContestacion(Contestacion c) {

        try {

            if (tieneContestacion(c.getReview())) {
                throw new ProgramException(11);
            } else if (c.getComentario().length() > 500) {
                throw new ProgramException(5);
            }

            for (Review review : listaReviews) {
                if (review.equals(c.getReview()) && listaContestacion.add(c)) {
                    return true;
                }
            }

            throw new ProgramException(20); //Error si no se ha podido añadir la contestacion

        } catch (ProgramException ex) {

            if (ex.getCode() != 11) {
                System.out.println(ex.getMessage()); //Se crea la contestacion si la review no esta contestada
            }
            return false;
        }

    }

    @Override
    public boolean tieneContestacion(Review r) {
        try {

            if (!existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                throw new ProgramException(10);//Error si no existe la review
            }
            for (Contestacion contestacion : listaContestacion) {
                if (contestacion.getReview().equals(r)) {
                    throw new ProgramException(11);//Error de que ya esta contestada
                }
            }
            return false;

        } catch (ProgramException ex) {

            if (ex.getCode() == 11) {
                System.out.println(ex.getMessage());//Solo si sí tiene una contestacion
            }
            return true;
        }
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        try {

            if (!tieneContestacion(r)) {
                throw new ProgramException(21); //Review no contestada
            }
            for (Contestacion contestacion : listaContestacion) {
                if (contestacion.getReview().equals(r)) {
                    return contestacion;
                }
            }

            throw new ProgramException(22);//No se puede obtener la contestacion de la review

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return null;

        }
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {

        try {

            if (!listaContestacion.contains(c)) {
                throw new ProgramException(23);//Error de que no se encuentra la contestacion
            } else if (!listaContestacion.remove(c)) {
                throw new ProgramException(24);//Si no se ha podido eliminar la contestacion, salta error
            }
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
            for (Local local : listaLocal) {
                if (local.equalsInList(l)) {
                    throw new ProgramException(25);//Error de que ya existe un local en la misma direccion
                }
            }
            if (!listaLocal.add(l)) {
                throw new ProgramException(26); //No se puede añadir el local
            }
            return true;

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminarLocal(Local l) {

        try {

            if (!listaLocal.contains(l)) {
                throw new ProgramException(27);//No existe el local
            } else if (!listaLocal.remove(l)) {
                throw new ProgramException(29);//Error si no se puede eliminar el local
            }
            return true;

        } catch (ProgramException ex) {

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

            throw new ProgramException(27); //El local no existe

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public Local obtenerLocal(String nombreLocal) {

        try {

            for (Local local : listaLocal) {
                if (local.getNombreLocal().equals(nombreLocal)) {
                    return local;
                }
            }

            throw new ProgramException(27); //El local no existe

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean asociarLocal(Local l, Propietario p) {

        try {

            if (!listaLocal.contains(l)) {
                throw new ProgramException(27);//Error si no existe el local
            } else if (l.propietariosLleno()) {
                throw new ProgramException(12);//Si ya hay 3 propietarios da error
            } else if (!existeNick(p.getNick())) {
                throw new ProgramException(8);//Error si no existe el usuario
            } else if (!l.addPropietario(p)) {
                throw new ProgramException(30);//Error si no se puede añadir propietario
            }
            return true;

        } catch (ProgramException ex) {

            if (ex.getCode() != 8) {
                System.out.println(ex.getMessage());
            }

            return false;
        }

    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        try {

            if (!listaLocal.contains(l)) {
                throw new ProgramException(27);//Error si no existe el local
            } else if (l.propietariosLleno()) {
                throw new ProgramException(12);//Si ya hay 3 propietarios da error
            } else if (!existeNick(p.getNick())) {
                throw new ProgramException(8);//Error si no existe el usuario
            } else if (!l.deletePropietario(p)) {
                throw new ProgramException(31);//Error si no se ha podido eliminar a un propietario
            }
            return true;

        } catch (ProgramException ex) {

            if (ex.getCode() != 8) {
                System.out.println(ex.getMessage());
            }

            return false;
        }
    }

    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {

        try {

            if (!eliminarLocal(viejoL)) {
                throw new ProgramException(29);//Error si no se ha podido eliminar el local viejo
            } else if (!nuevoLocal(nuevoL)) {
                throw new ProgramException(26);//Error si no se pude añadir el nuevo local
            }
            return true;

        } catch (ProgramException ex) {

            nuevoLocal(viejoL);
            return false;
        }

    }

    @Override
    public Review[] verReviews(Local l) {

        try {

            if (!listaLocal.contains(l)) {
                throw new ProgramException(27);//Error si no existe el local
            }
            List<Review> localReviews = new ArrayList<>();

            for (Review review : listaReviews) {
                if (review.getLocal().equals(l)) {
                    localReviews.add(review);
                }
            }

            return localReviews.toArray(new Review[localReviews.size()]);

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean nuevaReserva(Reserva r) {

        try {

            if (!existeNick(r.getCliente().getNick())) {
                throw new ProgramException(8);//Error si no existe el nick
            } else if (!listaLocal.contains((Reservable) r.getReservable())) {
                throw new ProgramException(27);//Error si no existe el local
            } else if (r.getLd().isBefore(LocalDate.now()) || (r.getLd().equals(LocalDate.now()) && r.getLt().isBefore(LocalTime.now()))) {
                throw new ProgramException(32);//Error si fecha y horario anterior a la actual
            } else if (!listaReserva.add(r)) {
                throw new ProgramException(33);//Error si no se puede añadir una reserva
            }
            return true;

        } catch (ProgramException ex) {

            if (ex.getCode() != 8) {
                System.out.println(ex.getMessage());
            }

            return false;
        }
    }

    @Override
    public Reserva[] obtenerReservas(Cliente c) {

        try {

            if (!existeNick(c.getNick())) {
                throw new ProgramException(8);//Error si no existe el nick
            }
            List<Reserva> reservasCliente = new ArrayList();

            for (Reserva reserva : listaReserva) {
                if (reserva.getCliente().equals(c)) {
                    reservasCliente.add(reserva);
                }
            }

            return reservasCliente.toArray(new Reserva[reservasCliente.size()]);

        } catch (ProgramException ex) {

            return null;
        }

    }

    @Override
    public Reserva[] obtenerReservas(Reservable r) {

        try {

            if (!listaLocal.contains((Local) r)) {
                throw new ProgramException(27);//Error si no existe el local
            }
            List<Reserva> reservasLocal = new ArrayList();

            for (Reserva reserva : listaReserva) {
                if (reserva.getReservable().equals(r)) {
                    reservasLocal.add(reserva);
                }
            }

            return reservasLocal.toArray(new Reserva[reservasLocal.size()]);

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {

        List<Reserva> reservasFecha = new ArrayList();

        for (Reserva reserva : listaReserva) {
            if (reserva.getLd().equals(ld)) {
                reservasFecha.add(reserva);
            }
        }

        return reservasFecha.toArray(new Reserva[reservasFecha.size()]);

    }

    @Override
    public boolean eliminarReserva(Reserva r) {

        try {

            if (!listaReserva.contains(r)) {
                throw new ProgramException(35);//Error si no existe la reserva
            } else if (!listaReserva.remove(r)) {
                throw new ProgramException(36);//Error si no se ha podido eliminar una reserva
            }
            return true;

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Local[] listarLocales(String ciudad, String provincia) {

        try {

            List<Local> localesFiltrados = new ArrayList<>();

            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)) {
                    localesFiltrados.add(local);
                }
            }

            if (localesFiltrados.isEmpty()) {
                throw new ProgramException(37);//Error si no existen locales con esos datos
            }

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

            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)
                        && local.getClass().equals(Bar.class)) {
                    bares.add((Bar) local);
                }
            }

            if (bares.isEmpty()) {
                throw new ProgramException(37);//Error si no existen bares con esos datos
            }
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

            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)
                        && local.getClass().equals(Restaurante.class)) {
                    restaurantes.add((Restaurante) local);
                }
            }

            if (restaurantes.isEmpty()) {
                throw new ProgramException(37);//Error si no existen restaurantes con esos datos
            }
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

            for (Local local : listaLocal) {
                if (local.getmDireccion().getProvincia().equals(provincia)
                        && local.getmDireccion().getLocalidad().equals(ciudad)
                        && local.getClass().equals(Pub.class)) {
                    pubs.add((Pub) local);
                }
            }

            if (pubs.isEmpty()) {
                throw new ProgramException(37);//Error si no existen pubs con esos datos
            }
            return pubs.toArray(new Pub[pubs.size()]);

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminaContestacion(Review r) {

        try {

            if (!existeReview(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                throw new ProgramException(10);//Error si no existe la review
            }
            for (Contestacion contestacion : listaContestacion) {
                if (contestacion.getReview().equals(r)) {
                    if (!listaContestacion.remove(contestacion)) {
                        throw new ProgramException(38);//Error si no se ha podido eliminar la contestacion
                    }
                    return true;
                }
            }

            throw new ProgramException(22);//Error si no se puede obtener la contestacion de la review

        } catch (ProgramException ex) {

            if (ex.getCode() != 10) {
                System.out.println(ex.getMessage());
            }
            return false;
        }

    }

    @Override
    public float obtenerValoracionMedia(Local l) {

        try {

            float media = 0;

            ArrayList<Review> reviews = new ArrayList<>();
            ArrayList<Local> locales = new ArrayList<>();

            if (!listaLocal.contains(l)) {
                throw new ProgramException(27);//Error si no existe el local
            }
            for (Review review : listaReviews) {
                if (l.equals(review.getLocal())) {
                    media = media + review.getEstrellas();
                }
            }

            if (reviews.isEmpty()) {
                throw new ProgramException(39);//Error si no hay reviews para un local
            }
            return (float) (media / reviews.size());

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());

            if (ex.getCode() == 27) {
                return -1;
            }

            return 0;
        }
    }

    @Override
    public float obtenerValoracionMedia(Propietario p) {

        try {

            if (!existeNick(p.getNick())) {
                throw new ProgramException(8);//Error si no existe el usuario
            }
            float media = 0;

            ArrayList<Local> locales = new ArrayList<>();

            for (Local local : listaLocal) {
                if (local.getListaPropietarios().contains(p)) {
                    media = obtenerValoracionMedia(local);
                }
            }

            return media / locales.size();

        } catch (ProgramException ex) {

            return -1;
        }

    }

    @Override
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta) {

        try {

            float media = 0;

            ArrayList<Review> reviews = new ArrayList<>();
            ArrayList<Local> locales = new ArrayList<>();

            if (!listaLocal.contains(l)) {
                throw new ProgramException(27);//Error si no existe el local
            }

            for (Review review : listaReviews) {
                if (l.equals(review.getLocal())) {
                    if (review.getCliente().obtenerEdad() > edadEntre && review.getCliente().obtenerEdad() < edadHasta) {
                        media = media + review.getEstrellas();
                    }
                }
            }

            if (reviews.isEmpty()) {
                throw new ProgramException(39);//Error si no hay reviews para un local
            }
            return media / reviews.size();

        } catch (ProgramException ex) {

            System.out.println(ex.getMessage());

            if (ex.getCode() == 27) {
                return -1;
            }

            return 0;
        }

    }

    @Override
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia) {

        List<Local> locales = new ArrayList<>();

        for (Local local : listaLocal) {
            if (local.getmDireccion().getProvincia().equals(provincia)
                    && local.getmDireccion().getLocalidad().equals(ciudad)) {
                locales.add(local);
            }
        }

        class OrdenAscendenteValoracion implements Comparator<Local> {

            @Override
            public int compare(Local L1, Local L2) {
                return (int) (obtenerValoracionMedia(L2) - obtenerValoracionMedia(L1));
            }
        }

        Collections.sort(locales, new OrdenAscendenteValoracion());

        return locales.toArray(new Local[locales.size()]);
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String provincia) {

        List<Local> locales = new ArrayList<>();

        for (Local local : listaLocal) {
            if (local.getmDireccion().getProvincia().equals(provincia)) {
                locales.add(local);
            }
        }

        class OrdenAscendenteValoracion implements Comparator<Local> {

            @Override
            public int compare(Local L1, Local L2) {
                return (int) (obtenerValoracionMedia(L2) - obtenerValoracionMedia(L1));
            }
        }

        Collections.sort(locales, new OrdenAscendenteValoracion());

        return locales.toArray(new Local[locales.size()]);
    }

    @Override
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia) {

        List<Bar> bares = new ArrayList<>();

        for (Local local : listaLocal) {
            if (local.getmDireccion().getProvincia().equals(provincia)
                    && local.getmDireccion().getLocalidad().equals(ciudad)
                    && local.getClass().equals(Bar.class)) {
                bares.add((Bar) local);
            }
        }

        class OrdenAscendenteValoracion implements Comparator<Bar> {

            @Override
            public int compare(Bar b1, Bar b2) {
                return (int) (obtenerValoracionMedia(b2) - obtenerValoracionMedia(b1));
            }

        }

        Collections.sort(bares, new OrdenAscendenteValoracion());

        return bares.toArray(new Bar[bares.size()]);

    }

    @Override
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia) {

        List<Restaurante> restaurantes = new ArrayList<>();

        for (Local local : listaLocal) {
            if (local.getmDireccion().getProvincia().equals(provincia)
                    && local.getmDireccion().getLocalidad().equals(ciudad)
                    && local.getClass().equals(Restaurante.class)) {
                restaurantes.add((Restaurante) local);
            }
        }

        class OrdenAscendenteValoracion implements Comparator<Restaurante> {

            @Override
            public int compare(Restaurante r1, Restaurante r2) {
                return (int) (obtenerValoracionMedia(r2) - obtenerValoracionMedia(r1));
            }
        }

        Collections.sort(restaurantes, new OrdenAscendenteValoracion());

        return restaurantes.toArray(new Restaurante[restaurantes.size()]);

    }

    @Override
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia) {

        List<Pub> pubs = new ArrayList<>();

        for (Local local : listaLocal) {
            if (local.getmDireccion().getProvincia().equals(provincia)
                    && local.getmDireccion().getLocalidad().equals(ciudad)
                    && local.getClass().equals(Pub.class)) {
                pubs.add((Pub) local);
            }
        }

        class OrdenAscendenteValoracion implements Comparator<Pub> {

            @Override
            public int compare(Pub p1, Pub p2) {
                return (int) (obtenerValoracionMedia(p2) - obtenerValoracionMedia(p1));
            }
        }

        Collections.sort(pubs, new OrdenAscendenteValoracion());

        return pubs.toArray(new Pub[pubs.size()]);
    }
    /*Metodo que abre un fichero ods e introduce su contenido 
    en la lista de pubs. 
    Entrada: fichero f de una única página
    Salida: número de pubs introducidos con éxito, entero.
    */
    public int importaPubs(File f) {
        int contPubs = 0;
        SpreadSheet hojaCalculo;
        try {
            //Obtiene la hoja de calculo del fichero f
            hojaCalculo = SpreadSheet.createFromFile(f);
            //Numero de filas
            int filaMax = hojaCalculo.getSheet(0).getRowCount();
            //Declaracion de variables
            String nombreLocal, localidad, provincia, calle, nickPropietario,  desc;
            String[] calleSplit;
            int numero;
            Direccion dir;
            Pub nuevoPub;
            
            for (int fila = 0; fila < filaMax; fila++) {
                if(!hojaCalculo.getSheet(0).getCellAt(fila,0).getTextValue().equals("")){
                    //Se introduce los valores guardados en cada columna en su correspondiente campo
                    nombreLocal = hojaCalculo.getSheet(0).getCellAt(0, fila).getTextValue();
                    calleSplit = hojaCalculo.getSheet(0).getCellAt(1, fila).getTextValue().split(" ");
                    localidad = hojaCalculo.getSheet(0).getCellAt(2, fila).getTextValue();
                    provincia = hojaCalculo.getSheet(0).getCellAt(3, fila).getTextValue();
                    nickPropietario = hojaCalculo.getSheet(0).getCellAt(4, fila).getTextValue();
                    calle = calleSplit[0] + " " + calleSplit[1];
                    numero = Integer.parseInt(calleSplit[2]);
                    //Crea la direccion
                    dir = new Direccion(localidad, provincia, calle, numero);
                    //Almacena la descripcion del pub en la variable desc
                    desc = hojaCalculo.getSheet(0).getCellAt(5, fila).getTextValue();
                    //Crea pub
                    nuevoPub = new Pub(nombreLocal, dir, desc);
                    //Asociar pub con propietario
                    asociarLocal(nuevoPub, (Propietario)obtenerUsuario(nickPropietario));
                    //Si la creación es correcta contar
                    if (nuevoLocal(nuevoPub)) {
                        contPubs++;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return contPubs;
    }
    /*Metodo que abre un fichero ods e introduce su contenido 
    en la lista de bares. 
    Entrada: fichero f de una única página
    Salida: número de bares introducidos con éxito, entero.
    */
    public int importaBares(File f) {
        int contBares = 0;
        SpreadSheet hojaCalculo;
        try {
            //Obtiene la hoja de calculo del fichero f
            hojaCalculo = SpreadSheet.createFromFile(f);
            //Numero de columnas
            int columnaMax = hojaCalculo.getSheet(0).getColumnCount();
            //Numero de filas
            int filaMax = hojaCalculo.getSheet(0).getRowCount();
            //Declaracion de variables
            String nombreLocal, localidad, provincia, calle, nickPropietario, desc;
            String[] calleSplit;
            String[] tags;
            int numero;
            Direccion dir;
            Bar nuevoBar;
            
            for (int fila = 0; fila < filaMax; fila++) {
                desc = "";
                tags = null;
                if(!hojaCalculo.getSheet(0).getCellAt(fila,0).getTextValue().equals("")){
                    //Se introduce los valores guardados en cada columna en su correspondiente campo
                    nombreLocal = hojaCalculo.getSheet(0).getCellAt(0, fila).getTextValue();
                    calleSplit = hojaCalculo.getSheet(0).getCellAt(1, fila).getTextValue().split(" ");
                    localidad = hojaCalculo.getSheet(0).getCellAt(2, fila).getTextValue();
                    provincia = hojaCalculo.getSheet(0).getCellAt(3, fila).getTextValue();
                    nickPropietario = hojaCalculo.getSheet(0).getCellAt(4, fila).getTextValue();
                    calle = calleSplit[0] + " " + calleSplit[1];
                    numero = Integer.parseInt(calleSplit[2]);
                    dir = new Direccion(localidad, provincia, calle, numero);
                    /*Almacena los tags del bar. Todo lo escrito a partir de 
                    columna 5 se considera un tag
                    */
                    for (int columna = 5; columna < columnaMax; columna++) {
                        if(!hojaCalculo.getSheet(0).getCellAt(columna, fila).getTextValue().equals(""))
                            tags[columna - 5] = hojaCalculo.getSheet(0).getCellAt(columna, fila).getTextValue();
                        else
                            break;
                    }
                    //Crea bar
                    nuevoBar = new Bar(tags, nombreLocal, dir, desc);
                    asociarLocal(nuevoBar, (Propietario)obtenerUsuario(nickPropietario));
                    if (nuevoLocal(nuevoBar)) {
                        contBares++;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return contBares;
    }

    @Override
    public boolean loadFromFile(File f) {
        try {
            //TODO
            this.sp.createFromFile(f);
            return true;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(BusinessSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean saveToFile(File f) {
        try {
            //TODO
            this.sp.saveAs(f);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(BusinessSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static BusinessSystem parseXMLFile(File f){
        BusinessSystem bs = new BusinessSystem();
        
        return bs;
    }
    public boolean loadXMLFile(File file){
        String fromXML = "";
        NodeList list;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            // get <Cliente>
            list = doc.getElementsByTagName("Cliente");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    
                    fromXML = fromXML + element.getElementsByTagName("nick").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("password").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("fechaNacimiento").item(0).getTextContent();
                    nuevoUsuario(new Cliente(fromXML));
                    fromXML = "";
                }
            }
            
            // get <Propietario>
            list = doc.getElementsByTagName("Propietario");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    fromXML = fromXML + element.getElementsByTagName("nick").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("password").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("fechaNacimiento").item(0).getTextContent();
                    nuevoUsuario(new Propietario(fromXML));
                    fromXML = "";
                }
            }
        
            // get <Bar>
            list = doc.getElementsByTagName("Bar");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    fromXML = fromXML + element.getElementsByTagName("nombreLocal").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("localidad").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("provincia").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("calle").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("numero").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("descripcion").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("tags").item(0).getTextContent();
                    nuevoLocal(new Bar(fromXML));
                    fromXML = "";
                }
            }
            
            // get <Restaurante>
            list = doc.getElementsByTagName("Restaurante");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    fromXML = fromXML + element.getElementsByTagName("nombreLocal").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("localidad").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("provincia").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("calle").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("numero").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("descripcion").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("precio").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("capacidadTotal").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("capacidadMesa").item(0).getTextContent();
                    nuevoLocal(new Restaurante(fromXML));
                    fromXML = "";
                }
            }
            
            // get <Pub>
            list = doc.getElementsByTagName("Pub");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    fromXML = fromXML + element.getElementsByTagName("nombreLocal").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("localidad").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("provincia").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("calle").item(0).getTextContent()+"$";
                    fromXML = fromXML + element.getElementsByTagName("numero").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("descripcion").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("apertura").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("clausura").item(0).getTextContent();
                    nuevoLocal(new Pub(fromXML));
                    fromXML = "";
                }
            }
            
            // get <Reserva>
            list = doc.getElementsByTagName("Reserva");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                   
                    
                    fromXML = fromXML + element.getElementsByTagName("descuento").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("ld").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("lt").item(0).getTextContent();
                            
                    Reserva reserva = new Reserva(fromXML);
                    reserva.setCliente(obtenerUsuario(element.getElementsByTagName("nick").item(0).getTextContent()));
                    reserva.setReservable((Reservable)obtenerLocal(element.getElementsByTagName("nombreLocal").item(0).getTextContent()));
                                    
                    nuevaReserva(reserva);
                    fromXML = "";
                }
            }
            
            // get <Review>
            list = doc.getElementsByTagName("Review");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    fromXML = fromXML + element.getElementsByTagName("comentario").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("estrellas").item(0).getTextContent()+";";
                    fromXML = fromXML + element.getElementsByTagName("fechaReview").item(0).getTextContent();
                    
                    
                    Review review = new Review(fromXML);
                    review.setCliente(obtenerUsuario(element.getElementsByTagName("nick").item(0).getTextContent()));
                    review.setLocal(obtenerLocal(element.getElementsByTagName("nombreLocal").item(0).getTextContent()));

                    nuevaReview(review);
                    fromXML = "";
                }
            }
            
            // get <Contestacion>
            list = doc.getElementsByTagName("Contestacion");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    
                    fromXML = fromXML + element.getElementsByTagName("comentario").item(0).getTextContent()+";";                   
                    fromXML = fromXML + element.getElementsByTagName("fechaContestacion").item(0).getTextContent();
                    
                    Contestacion contestacion = new Contestacion(fromXML);
                    
                    contestacion.setPropietario(obtenerUsuario(element.getElementsByTagName("propietario").item(0).getTextContent()));
                    Usuario user = obtenerUsuario(element.getElementsByTagName("nick").item(0).getTextContent());
                    Local local = obtenerLocal(element.getElementsByTagName("nombreLocal").item(0).getTextContent());
                    LocalDate date = LocalDate.parse(element.getElementsByTagName("fechaReview").item(0).getTextContent());
                    contestacion.setReview(obtenerReview(user,local,date));
                        
                    
                    nuevaContestacion(contestacion);
                    fromXML = "";
                }
            }
            
        }
        catch(ParserConfigurationException | SAXException | IOException ex){
            return false;
        }
        return true;

    } 

    @Override
    public String toXML() {
        String xmlString = "<list>\n";
        for (Usuario usuario: listaUsuarios) {
            if (usuario instanceof Cliente) {
                xmlString = xmlString + usuario.toXML();
            } else if (usuario instanceof Propietario) {
                xmlString = xmlString + usuario.toXML();
            } else {
                xmlString = xmlString + usuario.toXML();
            }
        }
        for (Local local : listaLocal) {       
            if (local instanceof Bar) {
                xmlString = xmlString + ((Bar)local).toXML();
            } else if (local instanceof Restaurante) {
                xmlString = xmlString + ((Restaurante)local).toXML();
            } else if (local instanceof Pub){
                System.out.println(((Pub)local).getNombreLocal());
                xmlString = xmlString + ((Pub)local).toXML();
            }
        }
        for (Review review: listaReviews) {
            xmlString = xmlString + review.toXML();
        }
        for (Contestacion contestacion : listaContestacion) {       
            xmlString = xmlString + contestacion.toXML();
        }
        for (Reserva reserva: listaReserva) {
            xmlString = xmlString + reserva.toXML();
        }
        return xmlString + "</list>";
    }

    @Override
    public boolean saveToXML(File f) {
        BufferedWriter br;
        try (FileWriter myWriter = new FileWriter(f,true)) {
            br = new BufferedWriter(myWriter);
            br.write(this.toXML());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean saveToXML(String filePath) {
        BufferedWriter br;
        try (FileWriter myWriter = new FileWriter(new File(filePath),true)) {
            br = new BufferedWriter(myWriter);
            br.write(this.toXML());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        
        try {
            if (tieneContestacion(r)) { 
                throw new ProgramException(11);
            } else if (c.getComentario().length() > 500) {
                throw new ProgramException(5);
            }
            
            for (Review review : listaReviews) {
                if (review.equals(r) && listaContestacion.add(c)) {
                    return true;
                }
            }
            throw new ProgramException(20); //Error si no se ha podido añadir la contestacion

        } catch (ProgramException ex) {

            if (ex.getCode() != 11) {
                System.out.println(ex.getMessage()); //Se crea la contestacion si la review no esta contestada
            }
            return false;
        }

    }

    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        
        try {
            if (!existeNick(c.getNick())) {
                throw new ProgramException(8);//Error si no existe el nick
            } else if (!listaLocal.contains((Reservable) r)) {
                throw new ProgramException(27);//Error si no existe el local
            } else if (ld.isBefore(LocalDate.now()) || (ld.equals(LocalDate.now()) && lt.isBefore(LocalTime.now()))) {
                throw new ProgramException(32);//Error si fecha y horario anterior a la actual
            } else if (!listaReserva.add(new Reserva(ld, c, r, lt))) {
                throw new ProgramException(33);//Error si no se puede añadir una reserva
            }
            
            return true;
            
        } catch (ProgramException ex) {

            if (ex.getCode() != 8) {
                System.out.println(ex.getMessage());
            }

            return false;
        }
    }

        
}


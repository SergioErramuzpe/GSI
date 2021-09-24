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

    public BusinessSystem() {
        listaUsuarios = new HashSet<Usuario>();
        listaReviews = new ArrayList<Review>();
        listaReserva = new ArrayList<Reserva>();
        listaBar = new ArrayList<Bar>();
        listaLocal = new ArrayList<Local>();
        listaRestaurante = new ArrayList<Restaurante>();
        listaPub = new ArrayList<Pub>();
    }

    @Override
    public boolean nuevoUsuario(Usuario u) {
        int edad = LocalDate.now().getYear() - u.getFechaNacimiento().getYear();
        boolean creado = false;
        try {
            if (edad < 18) {
                throw new Exception("El usuario debe ser mayor de edad.");
            } else if (u.getNick().length() < 3) {
                throw new Exception("El nick debe tener más de tres caŕacteres.");
            } else if (existeNick(u.getNick())) {
                throw new Exception("El nick del usuario ya existe.");
            } else if (u.getPassword().isEmpty()) {
                throw new Exception("La contraseña está en blanco.");
            } else if (!listaUsuarios.add(u)) {
                throw new Exception("Error al añadir el usuario.");
            } else {
                creado = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return creado;

    }

    @Override
    public boolean eliminaUsuario(Usuario u) {
        boolean eliminado = false;
        try {
            if (existeNick(u.getNick())) {
                if (listaUsuarios.remove(u)) {
                    eliminado = true;
                } else {
                    throw new Exception("No se ha podido eliminar.");
                }
            } else {
                throw new Exception("No se puede eliminar un usuario que no existe.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return eliminado;

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
                            exito = true;
                        }
                    }
                }
            } else {
                throw new Exception("El usuario a modificar no existe.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return exito;

    }

    @Override
    public boolean existeNick(String nick) {
        boolean existe = false;
        try {
            for (Usuario userIter : listaUsuarios) {
                if (userIter.getNick().equals(nick)) {
                    existe = true;

                }
            }
            if (!existe) {
                throw new Exception("El nombre del usuario ya existe");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return existe;

    }

    @Override
    public Usuario obtenerUsuario(String nick) {
        Usuario retorno = null;
        try {
            if (!existeNick(nick)) {
                throw new Exception("El usuario no existe.");
            } else {
                for (Usuario userIter : listaUsuarios) {
                    if (userIter.getNick().equals(nick)) {
                        retorno = userIter;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return retorno;

    }

    @Override
    public boolean nuevaReview(Review r) {
        boolean creada = false;
        try {
            if (existeRewiew(r.getCliente(), r.getLocal(), r.getFechaReview())) {
                throw new Exception("La review ya existe.");
            } else {
                if (listaReviews.add(r)) {
                    creada = true;
                } else {
                    throw new Exception("Error al añadir la review.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return creada;
    }

    @Override
    public boolean eliminaReview(Review r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        boolean existe = false;
        try {
            if (!existeNick(u.getNick())) {
                throw new Exception("El usuario de la review no existe.");
            }
            for (Review iterReview : listaReviews) {
                if (iterReview.getCliente().equals(u) && iterReview.getLocal().equals(l) && iterReview.getFechaReview().equals(ld)) {
                    existe = true;
                }
            }
            if (!existe) {
                throw new Exception("La review no existe.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return existe;
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean tieneContestacion(Review r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean eliminaContestacion(Review r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean nuevoLocal(Local l) {
        boolean creado = false;
        try {
            if (listaLocal.contains(l)) {
                throw new Exception("El local ya existe");
            } else {
                if (!listaLocal.add(l)) {
                    throw new Exception("Error al añadir el local");
                } else {
                    creado = true;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return creado;
    }

    @Override
    public boolean eliminarLocal(Local l) {
        boolean eliminado = false;
        try {
            if (!listaLocal.contains(l)) {
                throw new Exception("No se puede eliminar un local que no existe.");
            } else {
                if (!listaLocal.remove(l)) {
                    throw new Exception("Error al eliminar el local.");
                } else {
                    eliminado = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public Local obtenerLocal(Direccion d) {
        Local pLocal = null;
        try {
            for (Local local : listaLocal) {
                if(local.getmDireccion().equals(d)){
                    pLocal = local;
                }
            }
            if(pLocal == null){
                throw new Exception("No se ha podido encontrar el local.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pLocal;
    }

    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        try {
            if(obtenerLocal(viejoL.getmDireccion()).equals(viejoL)){
                
            }else{
                throw new Exception("El local a modificar no existe.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public Review[] verReviews(Local l) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public Reserva[] obtenerReservas(Cliente c) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Reserva[] obtenerReservas(Reservable r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean eliminarReserva(Reserva r) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public float obtenerValoracionMedia(Local l) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public float obtenerValoracionMedia(Propietario p) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

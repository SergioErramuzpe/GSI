/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BSystem;


import BModel.Bar;
import BModel.Cliente;
import BModel.Local;
import BModel.Restaurante;
import BModel.Review;
import GSILabs.connect.AdminGateway;
import GSILabs.connect.ClientGateway;
import java.io.File;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erra
 */
public class PublicBusinessSystem implements AdminGateway, ClientGateway {

    BusinessSystem bs;

    
    public PublicBusinessSystem() {
        super();
        bs = BusinessSystem.getBusinessSystem();
        bs.loadXMLFile(new File("bbdd.xml"));
    }

    /**
     * Getter de la clase BussinessSystem por ser Singleton
     * @return
     */
    public static PublicBusinessSystem getPublicBusinessSystemUp() {
        return new PublicBusinessSystem();
    }
    
    /*
    * Método empleado para almacenar los datos de cache en xml desde acceso
    * remoto
    */
    public void getPublicBusinessSystemDown() {
        try {
            File file = new File("bbdd.xml");
            if (!bs.saveToXML(file))
                throw new Exception("Fallo en el guardado de los datos");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
    * Método insertaReview: inserta review
    * Entrada: r, Review
    * Salida: booleano éxito o fallo
    */
    @Override
    public boolean insertaReview(Review r) {
        try {
            if (r.equals(null))
                throw new Exception("La review no puede ser nula");
            bs.nuevaReview(r);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /*
    * Método quitaReview: elimina una review
    * Entrada: r, Review 
    * Salida: booleano éxito o fallo
    */
    @Override
    public boolean quitaReview(Review r) {
        try {
            if (r.equals(null))
                throw new Exception("La review no puede ser nula");
            bs.eliminaReview(r);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /*
    * Método mejorBar: obtiene el bar con mejor puntuación
    * Entrada:  String ciudad, lugar en que se desea buscar
    * Salida: objeto Bar, bar con mejor puntuación
    */
    @Override
    public Bar mejorBar(String ciudad) {
        try {
            if ("".equals(ciudad))
                throw new Exception("La ciudad no puede ser vacia");
            Bar[] listaAscendente = ((Bar[])bs.obtenerBaresOrdenados(ciudad,""));
            return listaAscendente[listaAscendente.length-1];
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
    * Método mejoresRestaurantes: obtiene los N mejores restaurantes del lugar
    * seleccionado
    * Entrada: String ciudad, Integer num, lugar de búsqueda y longitud de lista
    * Salida: Lista de objeto Restaurante
    */
    @Override
    public Restaurante[] mejoresRestaurantes(String ciudad, Integer num) {
        try {
            if ((num == 0) || (ciudad.equals("")))
                throw new Exception("La ciudad no puede ser nula o no se pueden devolver 0 restaurantes");
            
            Restaurante[] arrayRestaurantes = bs.obtenerRestaurantesOrdenados(ciudad, "");
            int limit = num;
            if (num > arrayRestaurantes.length)
                limit = arrayRestaurantes.length;
            List<Restaurante> mejoresRestaurantes = new ArrayList<>();
            for (int i = 0; i<limit; i++) 
                mejoresRestaurantes.add(arrayRestaurantes[i]);
            return mejoresRestaurantes.toArray(arrayRestaurantes);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
    * Método getLocal: devuelve local correspondiente al nombre de inserción 
    * Entrada: String name, nombre del local
    * Salida: Local objeto, local que corresponde a dicho nombre 
    */
    @Override
    public Local getLocal(String name) {
        try {
            if ("".equals(name))
                throw new Exception("El nombre no puede ser vacio");
            return bs.obtenerLocal(name);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
    * Método getLocals: devuelve una lista de locales cuyo nombre contenga la 
    * String introducida
    * Entrada: String name, palabra(s) que contenga el nombre de los locales a 
    * buscar
    * Salida: lista de Locales
    */
    @Override
    public Local[] getLocals(String name) {
        try {
            if ("".equals(name))
                throw new Exception("El nombre no puede ser vacio");
            Local[] locales = bs.obtenerLocalesOrdenados("");
            List<Local> listaLocales = new ArrayList<>();
            for (int i = 0; i<locales.length; i++) {
                if (locales[i].getNombreLocal().contains(name))
                    listaLocales.add(locales[i]);
            }
            return (Local[]) listaLocales.toArray();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
    * Método eliminaLocal: elimina Local l
    * Entrada: Local l
    * Salida: booleano, éxito o fallo
    */
    @Override
    public Boolean eliminaLocal(Local l) throws RemoteException {
        try {
            if (l == null)
                throw new Exception("El local no puede ser nulo");
            return bs.eliminarLocal(l);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /*
    * Método eliminaReviewsDeLocal: elimina todas las reviews del Local 
    * seleccionado
    * Entrada: Local l
    * Salida: booleano, éxito o fallo
    */
    @Override
    public Boolean eliminaReviewsDeLocal(Local l) throws RemoteException {
        try {
            if (l == null)
                throw new Exception("El local no puede ser nulo");
            return bs.eliminaReviewsDeLocal(l);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /*
    * Método eliminaReview: elimina una Review
    * Entrada: Review r
    * Salida: booleano, éxito o fallo
    */
    @Override
    public Boolean eliminaReview(Review r) throws RemoteException {
        try {
            if (r == null)
                throw new Exception("La review no puede ser nula");
            return bs.eliminaReview(r);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /*
    * Método eliminaReviewDeUsuario: elimina las Reviews creadas por un usuario
    * Entrada: Cliente c, usuario seleccionado
    * Salida: Integer, número de reviews eliminadas
    */
    @Override
    public Integer eliminaReviewsDeUsuario(Cliente c) throws RemoteException {
        try {
            if (c == null)
                throw new Exception("El cliente no puede ser nulo");
            
            if (bs.existeNick(c.getNick())) {
                return bs.eliminaReviewsDeCliente(c);
            } else {
                throw new Exception("No existe el cliente");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
    /*
    * Método insertaReviewFalsa: inserta na review falsa
    * Entrada: Local l, Integer puntuacion
    * Salida: booleano, éxito o fallo
    */
    @Override
    public Boolean insertaReviewFalsa(Local l, Integer puntuacion) throws RemoteException {
        try {
            if ((l==null)|| (puntuacion>5) || (puntuacion<0)) 
                throw new Exception("Error en el local o la puntuacion");
            
            Cliente clienteFalso = new Cliente("clienteFalso","passFalsa",LocalDate.MIN);
            bs.nuevaReview(new Review("",puntuacion,LocalDate.now(),clienteFalso,l));
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}

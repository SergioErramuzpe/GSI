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
<<<<<<< Updated upstream
=======
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======

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

    public int importaPubs(File f) {
        int contPubs = 0;
        SpreadSheet hojaCalculo;
        try {
            //Obtiene la hoja de calculo del fichero f
            hojaCalculo = SpreadSheet.createFromFile(f);
            //Numero de columnas
            int columnaMax = hojaCalculo.getSheet(0).getColumnCount();
            //Numero de filas
            int filaMax = hojaCalculo.getSheet(0).getRowCount();
            //Declaracion de variables
            String nombreLocal, localidad, provincia, calle, nickPropietario, otro, desc;
            String[] calleSplit;
            int numero;
            Direccion dir;
            Pub nuevoPub;
            
            for (int fila = 0; fila < filaMax; fila++) {
                desc = "";
                if(!hojaCalculo.getSheet(0).getCellAt(fila,0).getTextValue().equals("")){
                    //Se introduce los valores guardados en cada columna en su correspondiente campo
                    nombreLocal = hojaCalculo.getSheet(0).getCellAt(fila, 0).getTextValue();
                    calleSplit = hojaCalculo.getSheet(0).getCellAt(fila, 1).getTextValue().split(" ");
                    localidad = hojaCalculo.getSheet(0).getCellAt(fila, 2).getTextValue();
                    provincia = hojaCalculo.getSheet(0).getCellAt(fila, 3).getTextValue();
                    nickPropietario = hojaCalculo.getSheet(0).getCellAt(fila, 4).getTextValue();
                    calle = calleSplit[0] + " " + calleSplit[1];
                    numero = Integer.parseInt(calleSplit[2]);
                    //Crea la direccion
                    dir = new Direccion(localidad, provincia, calle, numero);
                    //Almacena la descripcion del pub en la variable desc
                    desc = hojaCalculo.getSheet(0).getCellAt(fila, 5).getTextValue();
                    //Crea pub
                    nuevoPub = new Pub(nombreLocal, dir, desc);
                    asociarLocal(nuevoPub, (Propietario)obtenerUsuario(nickPropietario));
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
            String nombreLocal, localidad, provincia, calle, nickPropietario, otro, desc;
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
                    nombreLocal = hojaCalculo.getSheet(0).getCellAt(fila, 0).getTextValue();
                    calleSplit = hojaCalculo.getSheet(0).getCellAt(fila, 1).getTextValue().split(" ");
                    localidad = hojaCalculo.getSheet(0).getCellAt(fila, 2).getTextValue();
                    provincia = hojaCalculo.getSheet(0).getCellAt(fila, 3).getTextValue();
                    nickPropietario = hojaCalculo.getSheet(0).getCellAt(fila, 4).getTextValue();
                    calle = calleSplit[0] + " " + calleSplit[1];
                    numero = Integer.parseInt(calleSplit[2]);
                    dir = new Direccion(localidad, provincia, calle, numero);
                    //Almacena los tags del bar
                    for (int columna = 5; columna < columnaMax; columna++) {
                        tags[columna - 5] = hojaCalculo.getSheet(0).getCellAt(fila, columna).getTextValue();
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
            SpreadSheet sp = SpreadSheet.createFromFile(f);

            
            
            return true;
            
        } catch (IOException ex) {
            Logger.getLogger(BusinessSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean saveToFile(File f) {
        try {
            
            SpreadSheet sp = SpreadSheet.create(5, 40, 40);
        
            Sheet usuarios = sp.getSheet("Usuarios");
            Sheet reviews = sp.getSheet("Reviews");
            Sheet reservas = sp.getSheet("Reservas");
            Sheet locales = sp.getSheet("Locales");
            Sheet constestaciones = sp.getSheet("Contestaciones");
            
            
            introSheet(listaUsuarios,usuarios);
       
            introSheet(listaReviews,reviews);
    
            introSheet(listaReserva,reservas);
            
            introSheet(listaLocal,locales);
             
            introSheet(listaContestacion,constestaciones);
            
                
            SpreadSheet.createEmpty(new DefaultTableModel()).saveAs(f);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(BusinessSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private void introSheet (HashSet hash,Sheet s) {
        int i = 0, j = 0;
        if (hash.getClass().isInstance(Local.class)) {
            for (Object local : hash) {
                s.setValueAt(((Local)local).getNombreLocal(), j++, i);
                s.setValueAt((((Local)local).getmDireccion().getCalle()+", "+((Local)local).getmDireccion().getNumero()), j++, i);
                s.setValueAt(((Local)local).getmDireccion().getLocalidad(), j++, i);
                s.setValueAt(((Local)local).getmDireccion().getProvincia(), j++, i);
                s.setValueAt(((Local)local).getListaPropietarios().iterator().next().getNick(), j++, i);
                if (local instanceof Bar) {
                    s.setValueAt("Bar", j++, i);
                    String[] tags = ((Bar) local).getTags();
                    for (String tag: tags)
                        s.setValueAt(tag, j++, i);
                } else if (local instanceof Pub) {
                    s.setValueAt("Pub", j++, i);
                    s.setValueAt(((Pub)local).getApertura(), j++, i);
                    s.setValueAt(((Pub)local).getClausura(), j++, i);
                } else if (local instanceof Restaurante) {
                    s.setValueAt("Restaurante", j++, i);
                    s.setValueAt(((Restaurante)local).getPrecio(), j++, i);
                    s.setValueAt(((Restaurante)local).getCapacidadTotal(), j++, i);
                    s.setValueAt(((Restaurante)local).getCapacidadMesa(), j++, i);
                }
                
                j = 0;
                i++;
            }
        } else if (hash.getClass().isInstance(Usuario.class)) {
            for (Object usuario : hash) {
                if (usuario instanceof Cliente) {
                    
                } else if (usuario instanceof Propietario) {

                }
            }
            
        } else if (o instanceof Review) {
            
        } else if (o instanceof Contestacion) {
            
        } else {
            
        }
        
    }

>>>>>>> Stashed changes
}

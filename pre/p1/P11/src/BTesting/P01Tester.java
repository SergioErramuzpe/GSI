/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTesting;

import BModel.Cliente;
import BModel.Contestacion;
import BModel.Direccion;
import BModel.Local;
import BModel.Propietario;
import BModel.Reservable;
import BModel.Review;
import BModel.Usuario;
import BSystem.BusinessSystem;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Manuel José Rodríguez Güiza
 */
public class P01Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Creacion
        BusinessSystem bs = BusinessSystem.getBusinessSystem();
        
        Direccion dir1 = new Direccion ("Prueblo 1", "Provincia 1", "Calle 1", 1);
        Direccion dir2 = new Direccion ("Prueblo 2", "Provincia 2", "Calle 2", 2);
        Direccion dir3 = new Direccion ("Prueblo 3", "Provincia 3", "Calle 3", 3);
        Direccion dir4 = new Direccion ("Prueblo 4", "Provincia 4", "Calle 4", 4);
        Direccion dir5 = new Direccion ("Prueblo 5", "Provincia 5", "Calle 5", 5);
        
        Local local1 = new Local("Local 1", dir1, "");
        bs.nuevoLocal(local1);
        Local local2 = new Local("Local 2", dir2, "");
        bs.nuevoLocal(local2);
        Local local3 = new Local("Local 3", dir3, "");
        bs.nuevoLocal(local3);
        
        Reservable reservable1 = new Reservable("Reservable 1", dir4, "");
        bs.nuevoLocal(reservable1);
        Reservable reservable2 = new Reservable("Reservable 2", dir5, "");
        bs.nuevoLocal(reservable2);
        
        Usuario usuario1 = new Usuario("nick1", "pw1", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(usuario1);
        Usuario usuario2 = new Usuario("nick2", "pw2", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(usuario2);
        Usuario usuario3 = new Usuario("nick3", "pw3", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(usuario3);
        Propietario propietario1 = new Propietario("nick4", "pw4", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario1);
        Propietario propietario2 = new Propietario("nick5", "pw5", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario2);
        Propietario propietario3 = new Propietario("nick6", "pw6", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario3);
        Propietario propietario4 = new Propietario("nick7", "pw7", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(propietario4);
        Cliente cliente1 = new Cliente("nick8", "pw8", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(cliente1);
        Cliente cliente2 = new Cliente("nick9", "pw9", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(cliente2);
        Cliente cliente3 = new Cliente("nick10", "pw10", LocalDate.parse("2000-01-01"));
        bs.nuevoUsuario(cliente3);
        
        //Prueba 1: obtener usuario mediante nick
        Usuario usuario = bs.obtenerUsuario("nick1");
        System.out.println("Nombre de usuario: " + usuario.getNick());
        
        //Prueba 2: usuario nulo
        System.out.println(bs.obtenerUsuario("Usuario null"));
        
        //Prueba 3: dirección repetida
        Local localNulo = new Local("localnulo", dir1, "");
        bs.nuevoLocal(localNulo);
        
        //Prueba 4: realojar dirección
        bs.eliminarLocal(local3);
        Local localPrueba = new Local("Local Prueba", dir3, "");
        bs.nuevoLocal(localPrueba);
        
        //Prueba 5: usuario menor de edad
        Usuario usuarioMenor = new Usuario("prueba 5", "prueba 5", LocalDate.parse("2021-01-01"));
        bs.nuevoUsuario(usuarioMenor);
        
        //Prueba 6 y 7:
        Reservable reservableNulo = new Reservable("Reservable nulo", dir5, "");
        Local local = bs.obtenerLocal(dir5);
        bs.nuevaReserva((Cliente) bs.obtenerUsuario("nick8"), reservableNulo, LocalDate.MIN, LocalTime.MIN);
        
        //Prueba 8:
        Review reviewPrueba = new Review("", 1, LocalDate.now(), cliente1, local1);
        bs.nuevaReview(reviewPrueba);
        Review reviewPrueba2 = new Review("", 1, LocalDate.now(), cliente2, local2);
        Contestacion contestacion = new Contestacion(propietario1, reviewPrueba, ".", LocalDate.now());
        bs.nuevaContestacion(contestacion, reviewPrueba2);
        
        //Prueba 9:
        bs.asociarLocal(local1, propietario1);
        bs.asociarLocal(local1, propietario2);
        bs.asociarLocal(local1, propietario3);
        bs.asociarLocal(local1, propietario4);
        
        //Prueba 10: 
        bs.nuevaReview(reviewPrueba);
    }
}

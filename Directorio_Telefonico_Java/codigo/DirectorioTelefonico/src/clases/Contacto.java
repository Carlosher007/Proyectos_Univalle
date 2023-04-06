package clases;

import java.util.ArrayList;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve como modelo de un Contacto con sus respectivos atributos
 */
public class Contacto {

    /*Atributos*/
    private String tipoContacto;
    private String fecha;
    private int identificacion;
    private String nombres;
    private String apellidos;
    private ArrayList<Direccion> direcciones;
    private ArrayList<Telefono> telefonos;

    /*Constructor*/
    public Contacto(String tipoContacto, String fecha, int idenficiacion, String nombres, String apellidos, ArrayList<Direccion> direcciones, ArrayList<Telefono> telefonos) {
        this.tipoContacto = tipoContacto;
        this.fecha = fecha;
        this.identificacion = idenficiacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direcciones = direcciones;
        this.telefonos = telefonos;
    }

    /*Metodos*/
    
    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdenficiacion() {
        return identificacion;
    }

    public void setIdenficiacion(int idenficiacion) {
        this.identificacion = idenficiacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

}

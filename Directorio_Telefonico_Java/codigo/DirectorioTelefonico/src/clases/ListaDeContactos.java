package clases;

import java.util.LinkedList;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que almacena objetos de tipo Contacto en un array, la cual sirve para modificarlos y que interactuen con la base de datos en archivo de texto
 */
public class ListaDeContactos {

    private LinkedList<Contacto> contactos;

    public void agregar(Contacto contacto) {
        contactos.add(contacto);
    }

    public void eliminar(int indice) {
        contactos.remove(indice);
    }

    public int total() {
        return contactos.size();
    }

    public Contacto obtener(int indice) {
        return contactos.get(indice);
    }

    public void cargarContactos() {
        BDContactos bd = new BDContactos();
        contactos = bd.obtener();
    }


    public void guardarEnArchivo() {
        BDContactos bd = new BDContactos();
        bd.borrarTodo();
        for (int i = 0; i < contactos.size(); i++) {
            bd.registrarContacto(contactos.get(i));
        }
    }
    
    public void exportar(){
       BDContactos bd = new BDContactos();
       bd.exportarEnArchivo();
    }
    
     public void restaurar(){
       BDContactos bd = new BDContactos();
       bd.restaurarEnArchivo();
    }
}

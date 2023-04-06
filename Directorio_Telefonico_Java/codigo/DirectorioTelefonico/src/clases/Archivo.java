package clases;

import funcionalidades.PanelDeOpciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import funcionalidades.PanelDeOpciones;
/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que funciona como intermediario entre los archivos txt y las demás clases, con el fin de poder modificarlos
 */
public class Archivo {

    /*Atributos*/
    private String nombre;
    private String carpeta;

    /*Constructor
        Pedimos un nombre como parametro que sera el nombre que tenga el archivo en la carpeta baseDeDatos
     */
    public Archivo(String nombre) {
        this.nombre = nombre;
        this.carpeta = "baseDatos/";
    }

    /*
    Metodo : obtenerArchivo
    Retorna un Objeto de tipo File con la ruta especificada en la propia clase
     */
    public File obtenerArchivo() {
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource(carpeta + nombre);
            if (url != null) {
                return new File(url.toURI());
            } else {
                File miArchivo = new File("src/" + carpeta + nombre);
                FileWriter fw;
                try {
                    fw = new FileWriter(miArchivo);
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                    return null;
                }
                return miArchivo;
            }
        } catch (URISyntaxException ex) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "No se pudo encontrar la ruta del archivo", "Error de ruta", null);
        }
        return null;
    }

    /*Metodo : obtenerTextoDelArchivo
    Retorna un LinkedList (Array) de String en el que cada elemento de este es una linea del archivo txt
     */
    public LinkedList<String> obtenerTextoDelArchivo() {
        LinkedList<String> lineasDeTexto = null;

        try {
            File archivo = obtenerArchivo();
            if (archivo.exists()) {
                lineasDeTexto = new LinkedList();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;

                while ((linea = br.readLine()) != null) {
                    lineasDeTexto.add(linea);
                }
                br.close();
            } else {
                PanelDeOpciones po = new PanelDeOpciones();
                po.mostrarMensajeDeDialogo("ERROR", null, "El archivo de texto no existe", "Error al encontrar el archivo", null);
            }
        } catch (Exception ex) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "Se produjo un error", "Error desconocido", null);
        }
        return lineasDeTexto;
    }

    /*Metodo: registrar
    Retorna true si se pudo añadir una linea pasada por parametro al archivo de texto, false en caso contrario
    El metodo no sobreescribe sobre el archivop de texto, si no quer añade la linea al final*/
    public boolean registrar(String linea) {

        File archivo = obtenerArchivo();
        try {

            if (archivo.exists()) {
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                //escribir texto
                pw.println(linea);
                //registramos linea
                pw.flush();
                pw.close();
                return true;
            }
        } catch (Exception ex) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "Se produjo un error", "Error al registrar", null);
        }
        return false;
    }

    /*Metodo: borrarContenido
    Retorna true si se pudo borrar todo el contenido del archivo txt con la ruta especificada, false si no se pudo borrar*/
    public boolean borrarContenido() {
        try {
//            File archivo = obtenerArchivo();
//            String directorio = archivo.getParent();
//            archivo.delete();
//            FileWriter fileWriter = new FileWriter(directorio+"/contactos.txt"+true);
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/" + carpeta + "/contactos.txt"));
            bw.write("");
            bw.close();
            return true;
        } catch (IOException ex) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "Se produjo un error", "Error al borrar", null);
        }
        return false;
    }

}

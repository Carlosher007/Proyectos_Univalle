package clases;

import funcionalidades.PanelDeOpciones;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que replica una base de datos de contactos, la cual puede crear
 * contactos tomando un archivo txt o viseversa, asi como tambien poder dar la
 * funcionalidad de registar, eliminar, restaurar y exportar
 */
public class BDContactos {

    /*Metodo: Obtener
    A taves de un archivo txt que guarda los contactos con cierto formato, toma esa información para crear un array de contactos, el cual sera utilizado en el modelo y la tabla de la interfaz*/
    public LinkedList<Contacto> obtener() {
        LinkedList<Contacto> contactos = null;

        Archivo archivo = new Archivo("contactos.txt");

        LinkedList<String> lineas = archivo.obtenerTextoDelArchivo();

        if (lineas != null) {
            contactos = new LinkedList();

            for (int i = 0; i < lineas.size(); i++) {

                ArrayList<Direccion> ArrayDireccionesFinal = new ArrayList<>();
                ArrayList<Telefono> ArrayTelefonoFinal = new ArrayList<>();

                LinkedList<String> LKDirecciones = new LinkedList();
                LinkedList<String> LKTelefonos = new LinkedList();

                String linea = lineas.get(i);
                StringTokenizer tokens = new StringTokenizer(linea, ";");

                String tipoContacto = tokens.nextToken();
                String fecha = tokens.nextToken();
                int identificacion = Integer.parseInt(tokens.nextToken());
                String nombres = tokens.nextToken();
                String apellidos = tokens.nextToken();

                LKDirecciones.add(tokens.nextToken());
                LKTelefonos.add(tokens.nextToken());

                //Ahora debemos sacar los datos de las direcciones
                LinkedList<String> ArrayDirecciones = new LinkedList();
                for (int j = 0; j < LKDirecciones.size(); j++) {
                    String linea2 = LKDirecciones.get(j);
                    StringTokenizer tokens2 = new StringTokenizer(linea2, ",");

                    while (tokens2.hasMoreTokens()) {
                        ArrayDirecciones.add(tokens2.nextToken());
                    }
                    for (int k = 0; k < ArrayDirecciones.size(); k++) {
                        String linea3 = ArrayDirecciones.get(k);
                        StringTokenizer tokens3 = new StringTokenizer(linea3, "-");
                        String barrio = tokens3.nextToken();
                        String ciudad = tokens3.nextToken();
                        Direccion miDireccion = new Direccion(barrio, ciudad);
                        ArrayDireccionesFinal.add(miDireccion);
                    }
                }

                //Ahora debemos sacar los datos de los telefonos
                LinkedList<String> ArrayTelefonos = new LinkedList();
                for (int j = 0; j < LKTelefonos.size(); j++) {
                    String linea2 = LKTelefonos.get(j);
                    StringTokenizer tokens2 = new StringTokenizer(linea2, ",");

                    while (tokens2.hasMoreTokens()) {
                        ArrayTelefonos.add(tokens2.nextToken());
                    }
                    for (int k = 0; k < ArrayTelefonos.size(); k++) {
                        String linea3 = ArrayTelefonos.get(k);
                        StringTokenizer tokens3 = new StringTokenizer(linea3, "-");
                        int numero = Integer.parseInt(tokens3.nextToken());
                        String tipo = tokens3.nextToken();
                        Telefono miTelefono = new Telefono(numero, tipo);
                        ArrayTelefonoFinal.add(miTelefono);
                    }
                }

                contactos.add(new Contacto(tipoContacto, fecha, identificacion, nombres, apellidos, ArrayDireccionesFinal, ArrayTelefonoFinal));

            }

        }

        return contactos;
    }

    /*Metodo: regstrarContacto
    A traves de un objeto de tipo Contacto obtiene cada uno de los atributos de la clase, con el formato correspondiente para poder añadirlo a la base de datos de archivo de texto que almacena los contactos*/
    public boolean registrarContacto(Contacto c) {
        String direcciones = "";
        for (int i = 0; i < c.getDirecciones().size(); i++) {
            Direccion auxDir = c.getDirecciones().get(i);
            if (i == c.getDirecciones().size() - 1) {
                direcciones += auxDir.getBarrio() + "-" + auxDir.getCiudad();
            } else {
                direcciones += auxDir.getBarrio() + "-" + auxDir.getCiudad() + ",";
            }
        }

        String telefonos = "";
        for (int j = 0; j < c.getTelefonos().size(); j++) {
            Telefono auxTel = c.getTelefonos().get(j);
            if (j == c.getTelefonos().size() - 1) {
                telefonos += auxTel.getNumero() + "-" + auxTel.getTipo();
            } else {
                telefonos += auxTel.getNumero() + "-" + auxTel.getTipo() + ",";
            }
        }

        String linea = c.getTipoContacto() + ";"
                + c.getFecha() + ";"
                + c.getIdenficiacion() + ";"
                + c.getNombres() + ";"
                + c.getApellidos() + ";"
                + direcciones + ";"
                + telefonos + "\n";

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File("src/baseDatos/contactos.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(linea);
        } catch (IOException e) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "Se produjo un error", "Error al encontrar archivo", null);
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                PanelDeOpciones po = new PanelDeOpciones();
                po.mostrarMensajeDeDialogo("ERROR", null, "Se produjo un error", "Error al registrar contacto", null);
            }
        }

        return true;

////        return archivo.registrar(c.getTipoContacto() + ";"
//                + c.getFecha() + ";"
//                + c.getIdenficiacion() + ";"
//                + c.getNombres() + ";"
//                + c.getApellidos() + ";"
//                + direcciones + ";"
//                + telefonos
//        );
    }

    /*Metodo: borrarTodo
    Llama al metodo de borrar contenido de la clase Archivo con el nombre correspondiente al archivo txt
     */
    public boolean borrarTodo() {
        Archivo archivo = new Archivo("contactos.txt");
        return archivo.borrarContenido();
    }

    /*Metodo: exportarInformacion
    Se encarga de copiar lo que hay en la base de datos en archivo txt que almacena los contactos en nuevo archivo txt
     */
   public void exportarEnArchivo() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            File archivoOriginal = new File("src/baseDatos/contactos.txt");
            File archivoCopia = new File("src/baseDatos/exportado.txt");
            if (!archivoCopia.exists()) {
                archivoCopia.createNewFile();
            }
            inputStream = new FileInputStream(archivoOriginal);
            outputStream = new FileOutputStream(archivoCopia);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "No se pudo exportar los contactos", "Error al exportar", null);
        }
    }

    /*Metodo: restaurarEnArchvio
    Copia lo que hay en el archivo txt donde se exporto los contactos y restauras la base de datos de contactos con ese archivo*/
    public void restaurarEnArchivo() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            File archivoCopia = new File("src/baseDatos/contactos.txt");
            File archivoOriginal = new File("src/baseDatos/exportado.txt");
            if (!archivoCopia.exists()) {
                archivoCopia.createNewFile();
            }
            inputStream = new FileInputStream(archivoOriginal);
            outputStream = new FileOutputStream(archivoCopia);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("ERROR", null, "Aun no has exportado ningun contacto, por ende no puedes restaurar", "Error al restaurar", null);
        }
    }
}

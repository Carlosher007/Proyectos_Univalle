
package funcionalidadesAparte;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * MINIPROYECTO 2 - Adosa2
 * @author Juan Sebastian Getial <getial.juan@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Clase que contiene un metodo para estbalcer las imagenes
 */
public class metodosUtiles {
    public static ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            {
        
        String rutaAbsoluta = new File("").getAbsolutePath();
        ImageIcon imagen = new ImageIcon(rutaAbsoluta.concat(rutaArchivo));
        Image image = (imagen).getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        
        return new ImageIcon(image);
    }
}

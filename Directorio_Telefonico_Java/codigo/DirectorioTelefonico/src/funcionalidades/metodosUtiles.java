
package funcionalidades;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve para realizar metodo que se utilizan en las clases de vista como establecer icono a cierto elemento
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

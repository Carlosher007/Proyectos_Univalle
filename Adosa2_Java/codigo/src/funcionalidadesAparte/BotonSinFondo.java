
package funcionalidadesAparte;

import javax.swing.JButton;

/**
 * MINIPROYECTO 2 - Adosa2
 * @author Juan Sebastian Getial <getial.juan@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Clase que representa un boton sin fondo y bordes
 */
public class BotonSinFondo extends JButton {
    
    public BotonSinFondo() {
            inicializar();
        }

    private void inicializar() {
        setRolloverEnabled(true);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
}

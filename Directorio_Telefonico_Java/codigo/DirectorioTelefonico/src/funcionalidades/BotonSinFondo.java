package funcionalidades;

import javax.swing.JButton;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve para crear un boton sin fondo
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

package funcionalidades;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve para realizar mensajes con paneles de forma mas comoda
 */
public class PanelDeOpciones {

    public PanelDeOpciones() {
    }

    public void mostrarMensajeDeDialogo(String tipoDeMensaje, JPanel panelPadre, String mensaje, String tituloVentana, Icon icono) {
        switch (tipoDeMensaje) {
            case "INFORMACION":
                JOptionPane.showMessageDialog(panelPadre, mensaje, tituloVentana,
                        JOptionPane.INFORMATION_MESSAGE, icono);
                break;
            case "ERROR":
                JOptionPane.showMessageDialog(panelPadre, mensaje, tituloVentana,
                        JOptionPane.ERROR_MESSAGE, icono);
                break;
            case "QUESTION":
                JOptionPane.showMessageDialog(panelPadre, mensaje, tituloVentana,
                        JOptionPane.QUESTION_MESSAGE, icono);
                break;
            case "PLAIN":
                JOptionPane.showMessageDialog(panelPadre, mensaje, tituloVentana,
                        JOptionPane.PLAIN_MESSAGE, icono);
                break;
            default:
                JOptionPane.showMessageDialog(panelPadre, mensaje, tituloVentana,
                        JOptionPane.PLAIN_MESSAGE, icono);
        }
    }

//    public String mostrarInput(JPanel ventanaPadre, String mensaje, String valorInicial) {
//        String cadenaRecibida = JOptionPane.showInputDialog(ventanaPadre, mensaje, valorInicial);
//        return cadenaRecibida;
//    }
//
//    public Object mostrarOpciones(String opciones[], JPanel panelPadre, String mensaje, String tituloVentana, String tipoDeMensaje, Icon icono) {
//        Object opcionSeleccionada;
//        switch (tipoDeMensaje) {
//            case "INFORMACION":
//                opcionSeleccionada = JOptionPane.showInputDialog(panelPadre, mensaje, tituloVentana,
//                        JOptionPane.INFORMATION_MESSAGE, icono, opciones, opciones[0]);
//                break;
//            case "ERROR":
//                opcionSeleccionada = JOptionPane.showInputDialog(panelPadre, mensaje, tituloVentana,
//                        JOptionPane.ERROR_MESSAGE, icono, opciones, opciones[0]);
//                break;
//            case "QUESTION":
//                opcionSeleccionada = JOptionPane.showInputDialog(panelPadre, mensaje, tituloVentana,
//                        JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);
//                break;
//            case "PLAIN":
//                opcionSeleccionada = JOptionPane.showInputDialog(panelPadre, mensaje, tituloVentana,
//                        JOptionPane.PLAIN_MESSAGE, icono, opciones, opciones[0]);
//                break;
//            default:
//                opcionSeleccionada = JOptionPane.showInputDialog(panelPadre, mensaje, tituloVentana,
//                        JOptionPane.PLAIN_MESSAGE, icono, opciones, opciones[0]);
//        }
//        return opcionSeleccionada;
//    }
//
    public int confirmarOpcion(JPanel panelPadre, String mensaje, String tituloVentana, String tipoDeMensaje) {
        int opcion;
        switch (tipoDeMensaje) {
            case "INFORMACION":
                opcion = JOptionPane.showConfirmDialog(panelPadre, mensaje, tituloVentana, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                break;
            case "ERROR":
                opcion = JOptionPane.showConfirmDialog(panelPadre, mensaje, tituloVentana, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                break;
            case "QUESTION":
                opcion = JOptionPane.showConfirmDialog(panelPadre, mensaje, tituloVentana, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                break;
            case "PLAIN":
                opcion = JOptionPane.showConfirmDialog(panelPadre, mensaje, tituloVentana, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                break;
            default:
                opcion = JOptionPane.showConfirmDialog(panelPadre, mensaje, tituloVentana, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
        return opcion;
    }

}

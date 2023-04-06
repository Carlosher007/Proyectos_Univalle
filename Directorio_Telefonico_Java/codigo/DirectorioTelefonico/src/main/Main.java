package main;

import java.util.ArrayList;
import vista.Carga;
import vista.Principal;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase Principal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Carga ob = new Carga();

        ArrayList<String> mensajes = new ArrayList<>();
        mensajes.add("Cargando contactos...");
        mensajes.add("Finalizando...");

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(35);
                ob.lblPorcentaje.setText(Integer.toString(i) + "%");
                ob.progreso.setValue(i);

                switch (i) {
                    case 45:
                        ob.lblMensaje.setText(mensajes.get(0));
                        break;
                    case 70:
                        ob.lblMensaje.setText(mensajes.get(1));
                        break;
                    case 100:
                        ob.dispose();
                        Principal v = new Principal();
                        break;
                    default:
                        
                }

            }
        } catch (Exception ex) {

        }
    }

}

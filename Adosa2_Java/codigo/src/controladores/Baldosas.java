
package controladores;

import funcionalidadesAparte.metodosUtiles;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * MINIPROYECTO 2 - Adosa2
 * @author Juan Sebastian Getial <getial.juan@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Clase que funciona como controlador de las imagenes de las baldosas
 */
public class Baldosas {

    private ArrayList<ImageIcon> listaDeBaldosas;
    private final int altoBaldosa = 100;
    private final int anchoBaldosa = 100;

    //constructor
    public Baldosas() {
        listaDeBaldosas = new ArrayList<>();
        inicializarBaldosas();
    }

    private void inicializarBaldosas() {
        ImageIcon baldosa;
        String rutaAux = "\\src\\imagenes\\baldosas\\numero.png";

        for (int i = 1; i <= 14; i++) {
            
            String nuevaRuta = rutaAux.replace("numero",i+"");
            
            baldosa = metodosUtiles.establecerIcon(nuevaRuta, anchoBaldosa, altoBaldosa);
            listaDeBaldosas.add(baldosa);
        }

    }
    
    public ImageIcon getImgBaldosa(int cualBaldosa){
        return listaDeBaldosas.get(cualBaldosa);
    }
    
    public ImageIcon getImgBaldosaAleatoria(){
        return listaDeBaldosas.get((int) (Math.random() * 
                listaDeBaldosas.size()));
    }

}

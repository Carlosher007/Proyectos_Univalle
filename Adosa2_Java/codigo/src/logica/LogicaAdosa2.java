
package logica;

import java.util.ArrayList;

/**
 * MINIPROYECTO 2 - Adosa2
 * @author Juan Sebastian Getial <getial.juan@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Clase que contiene la logica del juego Adosa2
 */
public class LogicaAdosa2 {
    private int numeroDeBaldosasAMostrar;//Mientras mayor el numero, mas dificil
    private ArrayList<Integer> baldosasSinMostrar;
    private ArrayList<Integer> baldosasAMostrar;
    private int vidas;
    private int puntaje;
    private int puntajeASumar;
    private double tiempoDeCambio;
    private int aciertos;
    private int errores;
    //constructor
    public LogicaAdosa2(){
        numeroDeBaldosasAMostrar = 3;
        vidas = 3;
        puntaje = 0;
        puntajeASumar = 5;
        tiempoDeCambio = 2;
        aciertos = 0;
        errores = 0;
        
        baldosasSinMostrar = new ArrayList<>();
        for(int i = 0; i<8; i++){
            baldosasSinMostrar.add(i);
        }
        
        baldosasAMostrar = new ArrayList<>();
        for(int i = 0; i<numeroDeBaldosasAMostrar; i++){
            int cualBaldosa = (int) (Math.random() * baldosasSinMostrar.size());
            baldosasAMostrar.add(baldosasSinMostrar.get(cualBaldosa));
            baldosasSinMostrar.remove(cualBaldosa);
        }
        
    }
    
    //indica si la baldosa si se esta mostrando
    public boolean baldosaMostrandose(int cualNumero){
        return baldosasAMostrar.indexOf(cualNumero) != -1;
    }
    
    //retorna una baldosa aleatoria en pantalla para cambiarla
    public int baldosaACambiar(){
        return baldosasAMostrar.get((int) (Math.random() *
                baldosasAMostrar.size()));
    }
    
    public ArrayList<Integer> getBaldosasAMostrar(){
        return baldosasAMostrar;
    }
    
    public void errorCometido(){
        vidas--;
        if(numeroDeBaldosasAMostrar > 3) {
            numeroDeBaldosasAMostrar--;
        }
        if(puntajeASumar > 5) {
            puntajeASumar /= 2;
        }
    }
    
    public int getVidas() {
        return vidas;
    }
    
    public void reiniciarBaldosasSinMostrar(){
        baldosasSinMostrar.clear();
        for(int i = 0; i<8; i++){
            baldosasSinMostrar.add(i);
        }
    }
    
    public void nuevasBaldosasAMostrar() {
        reiniciarBaldosasSinMostrar();
        
        baldosasAMostrar.clear();
        for(int i = 0; i<numeroDeBaldosasAMostrar; i++){
            int cualBaldosa = (int) (Math.random() * baldosasSinMostrar.size());
            baldosasAMostrar.add(baldosasSinMostrar.get(cualBaldosa));
            baldosasSinMostrar.remove(cualBaldosa);
        }
        
    }
    
    public void aumentarBaldosasAMostrar(){
        if(numeroDeBaldosasAMostrar < 8) {
            numeroDeBaldosasAMostrar++;
        }
        
    }
    
    public void aumentarPuntaje(){
        puntaje += puntajeASumar;
    }
    
    public void aumentarPuntajeASumar(){
        puntajeASumar *= 2;
    }
    
    public int getPuntaje() {
        return puntaje;
    }
    
    public void disminuirTiempoDeCambio() {
        if(tiempoDeCambio > 1){
            tiempoDeCambio -= 0.2;
        }
        
    }
    
    public void aumentarTiempoDeCambio() {
        if(tiempoDeCambio < 2){
            tiempoDeCambio += 0.2;
        }
        
    }
    
    public double getTiempoDeCambio() {
        return tiempoDeCambio;
    }
    
    public int getAciertos() {
        return aciertos;
    }
    
    public int getErrores() {
        return errores;
    }
    
    public void aumentarAciertos() {
        aciertos++;
    }
    
    public void aumentarErrores() {
        errores++;
    }
}

package clases;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve como modelo de un telefono, con atributos como el numero y el tipo
 */
public class Telefono {
    private int numero;
    private String tipo;

    public Telefono(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public Telefono() {
    }
    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}

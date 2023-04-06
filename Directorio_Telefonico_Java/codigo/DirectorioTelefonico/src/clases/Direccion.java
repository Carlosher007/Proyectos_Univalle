package clases;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve como modelo de una direccion, con un barrio y una ciudad
 */
public class Direccion {

    /*Atributos*/
    private String barrio;
    private String ciudad;

    /*Constructor*/
    public Direccion(String barrio, String ciudad) {
        this.barrio = barrio;
        this.ciudad = ciudad;
    }

    public Direccion() {
    }
    
    /*Metodos*/

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
}

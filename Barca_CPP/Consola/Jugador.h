/*
Archivo: Jugador.h
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/

/**
Clase: Jugador
Responsabilidad: Representa al jugador humano en el juego, es el objeto que tiene acceso a la consola, muestra el estado del juego y ejecuta las órdenes que recibe desde afuera
Relaciones:
- Contiene a Lugar : Por medio de un vector a punteros de la clase Lugar
- Conoce a barca : Con el fin de llamar al metodo propio de la barca (moverBarca)
*/

/*
APORTES

@Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
- Idear la clase
- colocar la funcionalidad de los colores
- Todos lo metodos private los cuales seran utilizados para el metodo personalizado
- metodo personalizado
- getters y setters
- metodo inicio
- recibir Entrada
- modificar recibirCaracteres para que tambien funcione con el modo personalizado
- comprobarEstadoDelJuego
- menuDespuesDeGanarOPerder

@Susana Valencia Bravo
<susana.valencia@correounivalle.edu.co>
- el metodo jugar el cual será como un menu del juego
- metodo reiniciar

@Jose Luis Hincapie Bucheli
<jose.bucheli@correounivalle.edu.co>
- agregar el atributo estaJuegoIniciado que luego servira para saber cuando se pueden usar las teclas de salir y reiniciar
- metodo mostrarJuego
- recibirCaracteres como mejora ayuda para recibirEntrada
*/

#ifndef _JUGADOR_H_
#define _JUGADOR_H_

// Definimos los colores que se utilizaran para la consola
#define textoSubrayadoAzul "\e[4;34m"
#define textoSubrayadoPurpura "\e[4;35m"
#define textoSubrayadoVerde "\e[4;32m"
#define textoSubrayadoRojo "\e[4;31m"
#define textoVerde "\e[0;32m"
#define textoRojo "\e[0;31m"
#define textoMarron "\e[0;33m"
#define textoAzul "\e[0;34m"
#define textoPurpura "\e[0;35m"
#define textoGris "\e[0;3m"

#include "Personaje.h"
#include "Barca.h"
#include "Orilla.h"
#include "Lugar.h"

/*NOTA: Los metodos private y el metodo personalizado no son necesarios para la entrega base del juego, pero se hicieron con el fin de añadir una funcionalidad más al mismo*/

class Jugador
{
  // Atributos
protected:
  vector<Lugar *> mapa;
  Barca *barca;
  int totalPersonajes;
  bool estaJuegoIniciado = true;

private:
  /*
  Estos metodos serán utilizados a su vez por el metodo personalizado, es por eso que estan como privados pues no son necesarios para ser llamados en el main directamente
  */
  /*
    borrarPersonajes : borra y elimina los elementos del vector de personajes de cada lugar del mapa
   */
  void borrarPersonajes();
  /*
    esUnNumero : verifica si el string dado por parametro es un numero
  */
  bool esUnNumero(const string &str);
  /*
    recibirNumero : Este metodo sera utilizado para ingresar el numero de  *parametro* en el juego, sabiendo que el usuario debe ingresar un numero, pues si ingresa otro tipo de dato no lo dejara seguir
  */
  int recibirNumero(string nombre);
  /*
    existePersonajeConComando : Verifica si existe un personaje con el comando pasado por parametro, pasando por cada lugar del mapa
  */
  bool existePersonajeConComando(string comando);
  /*
    existePersonajeConNombr : Verifica si existe un personaje con el nombre pasado por parametro, pasando por cada lugar del mapa
  */
  bool existePersonajeConNombre(string nombre);
  /*
    agregarGuardianes : sirve para agregar un determinado numero de guardianes al juego, con verificaciones como que no existan dos guardianes con el mismo nombre o comando
  */
  void agregarGuardianes();
  /*
    agregarPersonajes : sirve para agregar un determinado numero de personajes al juego, con verificaciones como que no existan dos personajes con el mismo nombre o comando, y que estos no sean iguales a uno de los guardianes
 */
  void agregarPersonajes();
  /*
    crearRelaciones: sirve para poder añadir un personaje que come a cada personaje si el usuario quiere, con verificaciones como que el persoanje que come no puede ser el mismo personaje o un guardian
 */
  void crearRelaciones();
  /*
    Leer por consola el nombre del personaje, teniendo en cuenta que no puede ser el mismo que otro que ya exista. Retorna el nombre.
  */
  string agregarNombrePersonaje();
  /*
    Leer por consola el comando del personaje, teniendo en cuenta que no puede ser el mismo que otro que ya exista. Retorna el comando.
  */
  string agregarComandoPersonaje();
  /*
    verififca si el personaje es o no un guardian, si lo es retorna true, de lo contrario retorna false
  */
  bool verficiarEsGuardian(Personaje *personaje);
  /*
    recibir opcion para agregar saber si se agrega o no un personaje. Retorna el string que el usuario digito
  */
  string recibirOpcionAgregarPersonaje(Personaje *personaje);
  /*
  Imprimir los personajes que no sean guardianes ni que sea el personaje pasado por parametro
  */
  void imprimirPosiblesPersonajes(Personaje *personaje);
  /*
    Preguntar por el nombre de la presa y retornarlo, sabiendo que no puede ser un guardian ni tampoco el personaje pasado por parametro. Retorna el nombre de la presa;
  */
  string preguntarPorElNombrePresa(Personaje *personaje);

public:
  /*
    Constructor
  */
  Jugador(Barca *barca, Orilla *orillaIzquierda, Orilla *orillaDerecha);
  /*
    Destructor
  */
  virtual ~Jugador();
  /*
    Obtener el valor del atributo estaJuegoIniciado
  */
  virtual bool getEstaJuegoIniciado();
  /*
    Darle un valor al atibuto estaJuegoIniciado
  */
  virtual void setEstaJuegoIniciado(bool estado);
  /*
    Es la funcion que llama a casi todos los demás metodos con el fin de inicar el juego, comprobar el estado del mismo y leer las entradas
  */
  virtual void jugar();
  /*
    Mostrar la mini interfaz de inicio para que el jugador escoga entre las opciones
  */
  virtual void inicio();
  /*
    Hacer que el jugador decida que personajes integrar, donde, sus comandos y demás
  */
  virtual void personalizado();
  /*
    Imprimir en consola un mensaje inicial del juego, los lugares y los personajes
  */
  virtual void mostrarJuego();
  /*
    recibir la entrada por teclado del usuario, sabiendo que solo puede digitar de una a dos letras, y pasando lo recibido a mayuscula para que el usuario pueda digitar el nombre del personaje tanto en mayuscula como minuscula
  */
  virtual string recibirCaracteres(string pregunta);
  /*
    Determinar que se hace con lo ingresado por el usuario, para mover la barca, reiniciar el juego, salir o mover el personaje, sabiendo que las opciones de salir y reiniciar no se pueden dar mientras el juego este iniciado.
  */
  virtual void recibirEntrada();
  /*
    reiniciar vuelve todo a su estado incial, con las relaciones iniciales, todos los personajes al lado izquierdo y vivos, a demás de que vuelve a setear el estado del juego en true pues si se llego a este punto es porque se perdio y estaba en false
  */
  virtual void reiniciar();

  /*
    comprueba que ningun personaje este muerto (haya saltado al agua), que ningun personaje ha comido a alguno, o que todos los personajes hayan llegado a la derecha, si alguno de esto sucede muestra el menu de ganar o perder correspondiente para salir o reiniciar el juego.
  */
  virtual bool comprobarEstadoDelJuego();
  /*
    Nos muestra las opciones que tenemos de salir o reiniciar el juego, además de recibir lo que el usuario digite por teclado para realizar la accion correspondiente.
  */
  virtual void menuDespuesDeGanarOPerder();
};

#endif
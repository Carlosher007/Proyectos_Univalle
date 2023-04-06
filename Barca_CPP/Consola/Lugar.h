/*
Archivo: Lugar.h
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
Clase: Lugar
Responsabilidad: Ser la clase padre de otras como Lugar y Barca. Además permite modelar un lugar, el cual contiene personajes por medio de un vector de punteros a un objeto de clase Personaje y un vecino de la clase Lugar (misma clase), con la cual podrá manipular los personajes que llegan y sale del lugar
Relaciones:
- Contiene a Personaje : Por medio de un vector de punteros a Personaje para almacenar los personajes que estan en el lugar y los personajes que son guardianes
- Conoce a lugar: Por medio de un puntero a Lugar, que servira para transportar personajes al vecino actual
*/

/*
APORTES

@Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
- Idear la clase con la mayoria de los metodos existentes
- Getters y Setters
- borrarVectorPersonajes
- numeroDePersonajes
- agregarPersonaje
- agregarPersonajes
- existePersonajeConNombre
- verificarGuardian
- moverPersonaje

@Susana Valencia Bravo
<susana.valencia@correounivalle.edu.co>
- agregar un vector de guardianes en vez de un solo guardian
- agregarGuardian
- moverATodos
- buscarPorComando
- buscarPorNombre

@Jose Luis Hincapie Bucheli
<jose.bucheli@correounivalle.edu.co>
- agregar un puntero vecino para que sirva como el lugar donde se pasaran los personajes
- Añadir el atributo pos, por el cual se imprimira los lugares
- getPersonajePorIndice
- algunPersonajeHaSidoComido
- imprimirLugar por el cual se implementara polimorfismo
- imprimirPersonaje
- vincularVecino
- desvincularVecino
*/

#ifndef _LUGAR_H_
#define _LUGAR_H_

#include "Personaje.h"

class Lugar
{
  // Atributos
protected:
  // personajes : vector donde se almacenará los personajes que esten actualmente en el lugar
  vector<Personaje *> personajes;
  /*vecino ayudara a saber cual es el vecino del lugar, para transportar o recibir personajes de ese vecino, y saber si este es nulo, lo cual servira para cuando un personaje salte de la orilla a la barca sin haber barca (vecino = nullptr*/
  Lugar *vecino;
  // Saber si esta vinculado vecino
  bool estaVecino;
  // Nombre del lugar
  string nombre;
  // Capacidad del lugar, si la capacidad es -1 sería infinita
  int capacidad;
  // guardianes : vector donde se almacenará los personajes que sean guardianes
  vector<Personaje *> guardianes;
  // saber si existe un guardian en el lugar
  bool estaGuardian;
  // Esta variable nos ayudara especialmente para imprimir los personajes y los lugares dependiendo de la posicion en que se encuentren, pues de este modo imprimira al lugar con ciertos numeros de espacios
  int pos;

public:
  // Constructor
  Lugar(int pos, string nombre, int capacidad);
  // Destructor
  virtual ~Lugar();
  // Getters
  virtual string getNombre();
  virtual vector<Personaje *> getPersonajes();
  virtual int getCapacidad();
  virtual vector<Personaje *> getGuardianes();
  virtual bool getEstaGuardian();
  virtual int getPosicion();
  virtual bool getEstaVecino();
  // Setters
  virtual void setNombre(string nombre);
  virtual void setCapacidad(int capacidad);
  virtual void setEstaGuardian(bool estado);
  virtual void setPosicion(int pos);
  virtual void setEstaVecino(bool estado);
  // Funciones
  /*
    borrarVectorPersonajes: Limpia y elimina los personajes que existe en el vector de personajes y en el vector de guardianes
  */
  void borrarVectorPersonajes();
  /*
    agregarGuardian: Agrega un guardian al vector de guardiane
  */
  virtual void agregarGuardian(Personaje *guardian);
  /*
    numeroDePersonajes: Saber el numero de punteros a Personaje que hay en el vector personajes
  */
  virtual int numeroDePersonajes();
  /*
    agregarPersonaje: Agregar un puntero de Personaje al vector personajes, de tal modo de que si es posible (la capacidad no es excedida) retorna true, pero si no es posible (la capacidad es excedida) retorna true
  */
  virtual bool agregarPersonaje(Personaje *personaje);
  /*
    agregarPersonajes: Agregar varios personajes al vector personajes, si es posible (la capcidad no es excedida) retorna true, si no es posible (la capacidad es excedida) retorna false
  */
  virtual bool agregarPersonajes(vector<Personaje *> personajes);
  /*
    getPersonajePorIndice: Obtener el puntero a Personaje del vector personajes por medio del indice pasado por parametro : personajes[indice]
  */
  virtual Personaje *getPersonajePorIndice(int indice);
  /*
    existePersonajeConNombre: Saber si dentro del vector personajes existe un personaje con el nombre pasado por parametro
  */
  virtual bool existePersonajeConNombre(string nombre);
  /*
    moverATodos: Mover a todos los personajes del vector a otro vector del lugar pasado por parametro y limpiar el vector del objeto actual, así como setear a estaGuardian como falso debido a que el robot se paso a otro lugar.
  */
  virtual void moverATodos(Lugar *lugarDestino);
  /*
    verificarGuardian: Verifica si el guardian esta o no esta en el vector personajes, en caso de estarlo setea el atributo estGuardian a true, en caso contrario setea el mismo atributo a false
  */
  virtual void verificarGuardian();
  /*
    algunPersonajeHaSidoComido: Verificar si algun personaje dentro del vector Personajes ha sido comido, teniendo en cuenta que si el guardian esta en el Lugar nadie podrá ser comido ni podra comer. En caso de que el guardian no este se hace un for para recorrer a cada personaje, y dentro de este for se hacen a su vez otros dos ciclos for, uno para verificar los personajes por encima del personaje actual del vector, y otro para verificarlo por debajo.

    « : Por debajo
    » : Por encima
    £ : Actual

      Zorro     Conejo    Robot     Lechuga     Conejo    Zorro
      «           «        £          »           »         »

  */
  virtual Personaje *algunPersonajeHaSidoComido();
  /*
    buscarPorComando: Retornar -1 si no se encontro ningun Personaje dentro del vector cuyo atributo comando sea el pasado por parametro. Si lo encuentra, retorna el indice donde se encuentra el personaje dentro del vector.
  */
  virtual int buscarPorComando(string comando);

  /*
    Retorna al personaje con el nombre dado por parametro, si no lo encuentra retorna nullptr
  */
  virtual Personaje *buscarPorNombre(string nombre);

  /*
    moverPersonaje: Metodo para mover el personaje cuyo atributo comando sea el pasado por parametro, de modo que si se puede mover a otro lugar (atibuto vecino no es nullptr) pasa ese personaje al vector del otro lugar y lo elimina de su vector.
    - Si no se puede mover (no encuentra el personaje o el lugar esta lleno)
    - Si no existe vecino (vecino=nullptr) retorna true y setea el atributo estaVivo del personaje a false debido a que salto al agua
    - Si pudo moverlo (no paso nada de lo anterior) retorna true
  */
  virtual bool moverPersonaje(string comando);

  /*
    imprimirLugar: Imprimir el lugar dependiendo de la posicion de este. En este caso sera una funcion pura donde son las clases hijas que definen como se imprime lugar, esto debido a que la forma en que se imprime una barca es diferente a como se imprime una orilla
  */

  /*
  FORMA GRAFICA DE COMO SE IMPRIMIRAN LOS LUGARES EN LAS CLASES HIJAS
  IZQUIERDA             BARCA             |                 DERECHA
  0 Espacios            8 espacios                          16 espacios

  IZQUIEDA              |                 BARCA             DERECHA
  0 Espacios                              16 espacios       8 espacios

  Como ven si derecha no tiene vecino me daria 16 espacios para ese lugar, pero si por el contrario tiene vecino ahora seran 8 espacios debido a que barca aumento 8 espacios. Si dejamps a derecha con los mismos 16 espacios veremos como en la consola (al mover la barca) tambien se mueve derecha, y no queremos eso
  */

  virtual string imprimirLugar() = 0;

  /*
    imprimirPersonajes: Imprimir el personaje dependiendo de la posicion del lugar en donde esté, por lo que pondra un numero determinado de tabs antes de imprimir el nombre del personaje
  */

  /*
  FORMA GRAFICA DE COMO SE IMPRIMIRAN PERSONAJES

  Personaje en izquierda (posicion 0): 0 espacios

  Personaje en barca (posicion 1): 8 espacios + letras de izquierda

  Personaje en barca(posicion 2) : 16 espacios + letras de izquierda

  Personaje en derecha (posicion 3) : 24 espacios + letras de izquierda + letras de barca

  Debemos poner los espacios que vimos en el metodo anterior para cada respectivo lugar + las letras de los lugares anteriors (si hay) para que queden alineados con cada lugar en el que este

 */

  virtual string imprimirPersonaje(Personaje *personaje);

  /*
  vincularVecino: Permite agregar al atributo vecino un Lugar y setear estaVecino a true
  */
  virtual void vincularVecino(Lugar *vecino);
  /*
  desvincularVecino: Permite eliminar la relacion con vecino (si existe) y setear estaVecino a false
  */
  virtual void desvincularVecino();
};

#endif
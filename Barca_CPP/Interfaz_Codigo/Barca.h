/*
Archivo: Barca.h
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
Clase: Barca
Responsabilidad: Esta clase servira para implementar la barca en el juego, donde podran moverse los personajes entre orilla y orilla por medio de la barca, a través de verificaciones como que el robot debe estar en la barca para moverse.
Relaciones:
- Ninguna
*/

#ifndef BARCA_H
#define BARCA_H
#include "Personaje.h"

class Barca
{
public:
  /*
  Constructor
  */
  Barca();
  /*
  Destructor
  */
  ~Barca();
  /*
  agregarPersonaje : Si la barca puede almacenar personajes (hay 0 o 1 personaje en la barca), agrega el nombre del personaje pasado por parametro al vector de barca, de lo contrario no hace nada
*/
  void agregarPersonaje(const string &);
  /*
  Elimina el personaje pasado por parametro del vector barca
*/
  void elminarPersonaje(const string &);
  /*
  Verifica si hay espacio o no en la barca, de modo que si hay menos de dos personajes en la barca retorna true, de lo contrario retorna false
*/
  bool hayEspacio();
  /*
Verficia si esta el robot en la barca, si es así retorna true, de otro modo retorna false
*/
  bool sePuedeMover();
  /*
  Retorna el indice en el que se encuentra el nombre pasado por parametro en el vector de barca, si no lo encuentra retorna -1
*/
  int buscar(const string &);
  /*
  mueve la barca cambiando la posicion en la cual se encuentra, teniendo en cuenta que puede tener dos posiciones, 0 o 1, de modo que si la posicion es 0 pasa la variable a 1, y si es 1, pasa la variable a 0;
*/
  void moverBarca();
  /*
  nos retorna el valor del atributo posicion
*/
  int getPosicion();
  /*
  limpia el vector de string de barca y resetea la posicon de la barca a 0
*/
  void limpiarBarca();

private:
  vector<string> barca;
  int posicion = 0;
};

#endif // BARCA_H

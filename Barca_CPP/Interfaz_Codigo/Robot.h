/*
Archivo: Robot.h
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
Clase: Robot
Responsabilidad: Esta clase derivada de Personaje servira para representar el robot en el juego, con los mismos metodos y atributos de la clase padre pero con implementación diferentes en metodos propios del QT
Relaciones:
- Es parecido a un personaje
- Contiene a Personaje (heredado)
*/

#ifndef _ROBOT_H_
#define _ROBOT_H_
#include "Personaje.h"

using namespace std;

class Robot : public Personaje
{
  // Atributos
protected:
  /*
  atributo por el que sabremos que el personaje es un guardian
  */
  bool esGuardian=true;
public:
  // Constructor : Si el comando es mayor a dos letras pasara a valer la primera letra del nombre
  Robot(string nombre, string comando, QPushButton* boton, QLabel *etiqueta, string url);
  // Destructor
  virtual ~Robot();
  // Cosas de QT
  virtual void imprimirImagen();
  virtual void handleButton();
  /*
  metodo heredado de la clase padre pero con diferente implementación
*/
  bool getEsGuardian();

};

#endif

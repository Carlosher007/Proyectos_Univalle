/*
Archivo: Lechuga.h
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
Clase: Lechuga
Responsabilidad: Esta clase derivada de Personaje servira para representar la lechuga en el juego, con los mismos metodos y atributos de la clase padre pero con implementación diferentes en metodos propios del QT
Relaciones:
- Es un personaje
- Contiene a Personaje (heredado)
*/

#ifndef _LECHUGA_H_
#define _LECHUGA_H_
#include "Personaje.h"

using namespace std;

class Lechuga : public Personaje
{
  // Atributos
protected:

public:
  // Constructor : Si el comando es mayor a dos letras pasara a valer la primera letra del nombre
  Lechuga(string nombre, string comando, QPushButton* boton, QLabel *etiqueta, string url);
  // Destructor
  virtual ~Lechuga();
  // Cosas de QT
  virtual void imprimirImagen();
  virtual void handleButton();

};

#endif

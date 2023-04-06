/*
Archivo: Conejo.h
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
Clase: Conejo
Responsabilidad: Esta clase derivada de Personaje servira para representar el conejo en el juego, con los mismos metodos y atributos de la clase padre pero con implementación diferentes en metodos propios del QT
Relaciones:
- Es un personaje
- Contiene a Personaje (heredado)
*/

#ifndef _CONEJO_H_
#define _CONEJO_H_
#include "Personaje.h"

using namespace std;

class Conejo : public Personaje
{
  // Atributos
protected:

public:
  // Constructor : Si el comando es mayor a dos letras pasara a valer la primera letra del nombre
  Conejo(string nombre, string comando, QPushButton* boton, QLabel *etiqueta, string url);
  // Destructor
  virtual ~Conejo();
  // Cosas de QT
  virtual void imprimirImagen();
  virtual void handleButton();

};

#endif

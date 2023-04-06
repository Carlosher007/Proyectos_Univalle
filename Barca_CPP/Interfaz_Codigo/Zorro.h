/*
Archivo: Zorro.h
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
Clase: Zorro
Responsabilidad: Esta clase derivada de Personaje servira para representar el zorro en el juego, con los mismos metodos y atributos de la clase padre pero con implementación diferentes en metodos propios del QT
Relaciones:
- Es un personaje
- Contiene a Personaje (heredado)
*/

#ifndef _ZORRO_H_
#define _ZORRO_H_
#include "Personaje.h"

using namespace std;

class Zorro : public Personaje
{
  // Atributos
protected:

public:
  // Constructor : Si el comando es mayor a dos letras pasara a valer la primera letra del nombre
  Zorro(string nombre, string comando, QPushButton* boton, QLabel *etiqueta, string url);
  // Destructor
  virtual ~Zorro();
  // Cosas de QT
  virtual void imprimirImagen();
  virtual void handleButton();

};

#endif

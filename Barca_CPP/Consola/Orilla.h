/*
Archivo: Orilla.h
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
Clase: Orilla
Responsabilidad: Esta clase derivada de Lugar servira para hacer lo que hace su clase Padre (alamacenar personajes) y además poder hacer funciones con estos, como moverlos a la barca.
Relaciones:
- Es un Lugar
Relaciones que hereda:
- Contiene a Personaje : Por medio de un vector de punteros a Personaje
- Conoce a lugar: Por medio de un puntero a Lugar, que servira para transportar personajes al vecino actual
*/

/*
APORTES

@Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
- Idear la clase
- Agregar polimorfismo en imprimirLugar
*/

#ifndef _ORILLA_H_
#define _ORILLA_H_

#include "Lugar.h"

class Orilla : public Lugar
{
  // Atributos
private:
public:
  // Constructor
  Orilla(int pos, string nombre, int capacidad = -1);
  // Destructor
  virtual ~Orilla();

  /*
    imprimirLugar: Imprimir el nombre de la orilla en relacion a si es izquierda o derecha (debido a que una no necesita espacios y otra sí) y en relacion a la posicion de la barca.
  */
  virtual string imprimirLugar();
};

#endif
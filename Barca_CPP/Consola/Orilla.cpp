/**
Archivo: Orilla.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Orilla.h"

Orilla::Orilla(int pos, string nombre, int capacidad) : Lugar(pos, nombre, capacidad)
{
}

Orilla::~Orilla()
{
}

string Orilla::imprimirLugar()
{
  string auxImprimir;
  //Si el lugar es izquierda no necesita espacios , por ende solo retornamos el nombre
  if (getNombre() == "Izquierda" || getNombre() == "IZQUIERDA" || getNombre() == "izquierda")
  {
    auxImprimir += getNombre();
    return auxImprimir;
  }
  // Si el lugar es derecha si necesita espacios, entonces procedemos a ver si el vecino esta vinculado o no con derecha
  else
  {
    //Si no esta vinculado colocamos 16 espacios antes de imprimir derecha
    if (!getEstaVecino())
    {
      for (int i = 0; i < 16; i++)
      {
        auxImprimir += " ";
      }
      auxImprimir += getNombre();
      return auxImprimir;
    }
    // Si  esta vinculado colocamos 8espacios antes de imprimir derecha, pues los 8 espacios restantes los coloca barca al estar en la posicion 2.
    else
    {
      for (int i = 0; i < 8; i++)
      {
        auxImprimir += " ";
      }
      auxImprimir += getNombre();
      return auxImprimir;
    }
  }
}
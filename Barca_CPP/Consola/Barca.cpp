/**
Archivo: Barca.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Barca.h"

Barca::Barca(int pos, string nombre, int capacidad) : Lugar(pos, nombre, capacidad)
{
}

Barca::~Barca()
{
}

void Barca::moverBarca(vector<Lugar *> *mapa, bool verificarConductor)
{
  verificarGuardian();
  Lugar *orillaIzquierda = (*mapa)[0];
  Lugar *orillaDerecha = (*mapa)[2];
  // Se verifica que el conductor se encuentre en la barca a no ser que el parametro verificarConductor sea falso
  if (!getEstaGuardian() && verificarConductor)
  {
    string aux;
    for (Personaje *guardian : guardianes)
    {
      aux += guardian->getNombre() + ", ";
    }
    throw "No hay ninguno de estos guardianes subidos en la barca: " + aux + "recuerda que la barca no se mueve sola!!!";
  }

  // Se redefinen los vecinos dependiendo de donde este situado y se cambia la posicion de la barca
  if (vecino == orillaIzquierda)
  {
    orillaIzquierda->desvincularVecino();
    this->vincularVecino(orillaDerecha);
    orillaDerecha->vincularVecino(this);
    setPosicion(2);
  }
  else
  {
    orillaDerecha->desvincularVecino();
    this->vincularVecino(orillaIzquierda);
    orillaIzquierda->vincularVecino(this);
    setPosicion(1);
  }
}

string Barca::imprimirLugar()
{
  string aux = "";
  for (int i = 0; i < getPosicion() * 8; i++)
  {
    aux += " ";
  }
  aux += getNombre();
  return aux;
}

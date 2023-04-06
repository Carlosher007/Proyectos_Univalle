/**
Archivo: main.cpp
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
Historia: Empezamos con varios personajes a la Izquierda, entre ellos hay guardianes y personajes que se pueden comer a otros si los guardianes no estan presentes, admeás de que si no hay una barca e intenta saltar morirá. Tu mision es hacer que todos los personajes vayan a la derecha sanos y salvos

Izquierda              Barca          |           Derecha
Robot (Guardian)
Zorro (Come Conejo)
Conejo (Come Lechuga)
Lechuga
*/

#include "Personaje.h"
#include "Orilla.h"
#include "Jugador.h"
#include "Lugar.h"

// Funcion main
int main()
{
  /*
  @Carlos Andrés Hernández
  */
  Orilla *orillaIzquierda = new Orilla(0, "Izquierda");
  Barca *barca = new Barca;
  Orilla *orillaDerecha = new Orilla(3, "Derecha");

  barca->vincularVecino(orillaIzquierda);
  orillaIzquierda->vincularVecino(barca);

  Personaje *robot = new Personaje("Robot", "R");
  Personaje *zorro = new Personaje("Zorro", "Z");
  Personaje *conejo = new Personaje("Conejo", "C");
  Personaje *lechuga = new Personaje("Lechuga", "L");

  orillaIzquierda->agregarGuardian(robot);
  orillaDerecha->agregarGuardian(robot);
  barca->agregarGuardian(robot);

  zorro->agregarPersonajeQueCome(conejo);
  conejo->agregarPersonajeQueCome(lechuga);

  vector<Personaje *> personajes = {robot, zorro, conejo, lechuga};

  orillaIzquierda->agregarPersonajes(personajes);

  Jugador miJuego(barca, orillaIzquierda, orillaDerecha);

  miJuego.inicio();

  delete orillaIzquierda;
  orillaIzquierda = nullptr;
  delete orillaDerecha;
  orillaDerecha = nullptr;
  delete barca;
  barca = nullptr;

  delete zorro;
  zorro = nullptr;
  delete conejo;
  conejo = nullptr;
  delete robot;
  robot = nullptr;
  delete lechuga;
  lechuga = nullptr;

  return 0;
}

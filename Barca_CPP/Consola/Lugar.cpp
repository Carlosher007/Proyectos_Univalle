/**
Archivo: Lugar.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Lugar.h"

Lugar::Lugar(int pos, string nombre, int capacidad) : estaGuardian(false), nombre(nombre), capacidad(capacidad), pos(pos), vecino(nullptr), estaVecino(false)
{
}

Lugar::~Lugar()
{
  for (Personaje *personaje : guardianes)
  {
    if (personaje)
    {
      delete personaje;
      personaje = nullptr;
    }
  }
  guardianes.clear();

  for (Personaje *personaje : personajes)
  {
    if (personaje)
    {
      delete personaje;
      personaje = nullptr;
    }
  }
  personajes.clear();

  if (vecino)
  {
    delete vecino;
    vecino == nullptr;
  }
}

int Lugar::numeroDePersonajes()
{
  return personajes.size();
}

string Lugar::getNombre()
{
  return nombre;
}

vector<Personaje *> Lugar::getPersonajes()
{
  return personajes;
}

int Lugar::getCapacidad()
{
  return capacidad;
}

vector<Personaje *> Lugar::getGuardianes()
{
  return guardianes;
}

bool Lugar::getEstaGuardian()
{
  return estaGuardian;
}

int Lugar::getPosicion()
{
  return pos;
}

bool Lugar::getEstaVecino()
{
  return estaVecino;
}

void Lugar::setNombre(string nombre)
{
  this->nombre = nombre;
}

void Lugar::setCapacidad(int capacidad)
{
  this->capacidad = capacidad;
}

void Lugar::setEstaGuardian(bool estado)
{
  this->estaGuardian = estado;
}

void Lugar::setPosicion(int pos)
{
  this->pos = pos;
}

void Lugar::setEstaVecino(bool estado)
{
  this->estaVecino = estado;
}

void Lugar::borrarVectorPersonajes()
{
  for (Personaje *personaje : personajes)
  {
    personaje = nullptr;
  }
  personajes.clear();
  for (Personaje *guardian : guardianes)
  {
    guardian = nullptr;
  }
  guardianes.clear();
}

void Lugar::agregarGuardian(Personaje *guardian)
{
  guardianes.push_back(guardian);
}

bool Lugar::agregarPersonaje(Personaje *personaje)
{
  if (numeroDePersonajes() < getCapacidad() || getCapacidad() == -1)
  {
    personajes.push_back(personaje);
    // Una vez agregado el personaje verificamos si ese personaje es o no es guardian, para setearlo en el atributo estGuardian de la clase
    verificarGuardian();
    return true;
  }
  return false;
}

bool Lugar::agregarPersonajes(vector<Personaje *> personajes)
{
  if (numeroDePersonajes() + personajes.size() < getCapacidad() || getCapacidad() == -1)
  {
    // Agregamos el vector de individuos
    for (Personaje *personaje : personajes)
    {
      agregarPersonaje(personaje);
    }
    return true;
  }
  else
  {
    return false;
  }
}

Personaje *Lugar::getPersonajePorIndice(int indice)
{
  if (indice >= 0 && indice < numeroDePersonajes())
  {
    return personajes[indice];
  }
  else
  {
    return nullptr;
  }
}

bool Lugar::existePersonajeConNombre(string nombre)
{
  // Busca un nombre en el vector de personajes
  for (Personaje *personaje : personajes)
  {
    if (personaje->getNombre() == nombre)
    {
      return true;
    }
  }
  return false;
}

void Lugar::moverATodos(Lugar *lugarDestino)
{
  setEstaGuardian(false);
  lugarDestino->agregarPersonajes(personajes);
  // Limpiamos los personajes del vector actual
  for (Personaje *personaje : personajes)
  {
    if (personaje)
    {
      personaje = nullptr;
    }
  }
  //
  personajes.clear();
}

void Lugar::verificarGuardian()
{
  bool aux = false;
  for (Personaje *personaje : personajes)
  {
    for (Personaje *guardian : guardianes)
    {
      if (personaje->getNombre() == guardian->getNombre())
      {
        setEstaGuardian(true);
        aux = true;
      }
    }
  }
  if (aux == false)
  {
    setEstaGuardian(false);
  }
}

Personaje *Lugar::algunPersonajeHaSidoComido()
{
  verificarGuardian();
  // Verificar si esta el robot
  if (getEstaGuardian())
  {
    return nullptr;
  }

  // Si no esta el robot pasa por cada individuo a ver si pudo comer
  for (int i = 0; i < numeroDePersonajes(); i++)
  {
    // se realiza la verificacion para las individuos por encima en el vector
    for (int j = 0; j <= i - 1; j++)
    {
      if (personajes[i]->puedeComer(personajes[j]))
      {
        return personajes[j];
      }
    }
    // se realiza la verificacion para las individuos por debajo en el vector
    for (int j = i + 1; j < numeroDePersonajes(); j++)
    {
      if (personajes[i]->puedeComer(personajes[j]))
      {
        return personajes[j];
      }
    }
  }
  // Si nadie pudo comer...
  return nullptr;
}

void Lugar::vincularVecino(Lugar *vecino)
{
  this->vecino = vecino;
  setEstaVecino(true);
}

void Lugar::desvincularVecino()
{
  if (vecino)
  {
    vecino = nullptr;
  }
  setEstaVecino(false);
}

int Lugar::buscarPorComando(string comando)
{
  // busca un individuo y compara su letra inicial con primeraLetra
  int index = 0;
  for (Personaje *personaje : personajes)
  {
    if (personaje->getComando() == comando)
    {
      return index;
    }
    else
    {
      index += 1;
    }
  }
  return -1;
}

Personaje *Lugar::buscarPorNombre(string nombre)
{
  for (Personaje *personaje : personajes)
  {
    if (personaje->getNombre() == nombre)
    {
      return personaje;
    }
  }
  return nullptr;
}

bool Lugar::moverPersonaje(string comando)
{
  int indicePersonajeAMover = buscarPorComando(comando);

  // Si no encontro al personaje
  if (indicePersonajeAMover == -1)
  {
    return false;
  }

  // Si no hay ningun vecino significa que el personaje murio al tirarse al agua
  if (!getEstaVecino())
  {
    personajes[indicePersonajeAMover]->setEstaVivo(false);
    // Retornamos true para que el programa pueda seguir (debido a que se movio, pero al agua) y así nos aparezca que se muro el personaje
    return true;
  }
  else
  {
    // Agregamos un personaje al Lugar vecino
    if (vecino->agregarPersonaje(personajes[indicePersonajeAMover]))
    {
      // Borramos el personaje de este lugar
      personajes.erase(personajes.begin() + indicePersonajeAMover);
      return true;
    }
    else
    {
      // si la barca esta ocupada con 2 objetos
      throw(string) " El lugar " + vecino->getNombre() + " esta lleno... Intentalo de nuevo ";
    }
  }
}

string Lugar::imprimirPersonaje(Personaje *personaje)
{
  string auxImprimir = "";
  int auxPos = getPosicion();
  if (auxPos == 0)
  {
    return personaje->getNombre();
  }
  else if (auxPos == 1)
  {
    for (int i = 0; i < 8 + 9; i++) // 9 es el numero de letras de IZQUIERDA
    {
      auxImprimir += " ";
    }
    auxImprimir += personaje->getNombre();
  }
  else if (auxPos == 2)
  {
    for (int i = 0; i < 16 + 9; i++) // 9 es el numero de letras de IZQUIERDA
    {
      auxImprimir += " ";
    }
    auxImprimir += personaje->getNombre();
  }
  else
  {
    for (int i = 0; i < 24 + 9 + 5; i++) /// 9 es el numero de letras de IZQUIERDA y 5 es el numero de letras de BARCA
    {
      auxImprimir += " ";
    }
    auxImprimir += personaje->getNombre();
  }
  return auxImprimir;
}
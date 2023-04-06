/**
Archivo: Personaje.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Personaje.h"

/*
  Constructor
*/
Personaje::Personaje(string nombre, string comando) : nombre(nombre), comando(comando), estaVivo(true)
{
  if (comando.size() > 2)
  {
    string aux;
    aux.push_back(getNombre().at(0));
    setComando(aux);
  }
}

/*
  Destructor
*/
Personaje::~Personaje()
{
  for (Personaje *personaje : personajesQueCome)
  {
    if (personaje)
    {
      delete personaje;
      personaje = nullptr;
    }
  }
}

string Personaje::getNombre()
{
  return this->nombre;
}

vector<Personaje *> Personaje::getPersonajesQueCome()
{
  return this->personajesQueCome;
}

bool Personaje::getEstaVivo()
{
  return this->estaVivo;
}

string Personaje::getComando()
{
  return this->comando;
}

void Personaje::setNombre(string nombre)
{
  this->nombre = nombre;
}

void Personaje::agregarPersonajeQueCome(Personaje *personajeQueCome)
{
  personajesQueCome.push_back(personajeQueCome);
}

void Personaje::setEstaVivo(bool estado)
{
  this->estaVivo = estado;
}

void Personaje::setComando(string comando)
{
  this->comando = comando;
}

bool Personaje::puedeComer(Personaje *personaje)
{
  for (Personaje *personajeVector : personajesQueCome)
  {
    if (personaje->getNombre() == personajeVector->getNombre())
    {
      personajeVector->setEstaVivo(false);
      return true;
    }
  }
  return false;
}
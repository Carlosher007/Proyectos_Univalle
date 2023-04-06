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
#include <stdio.h>

/*
  Constructor
*/
Personaje::Personaje(string nombre, string comando, QPushButton *boton, QLabel *etiqueta, string url ) : nombre(nombre), comando(comando), estaVivo(true)
{
    this->url=url;
    this->etiqueta= &etiqueta;
    this->boton = &boton;
  if (comando.size() > 2)
  {

    string aux;
    //aux.push_back(getNombre().at(0));
    //setComando(aux);
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

bool Personaje::getEsGuardian()
{
    return false;
}


//QT things

void Personaje::handleButton()
{
    (*etiqueta)->move(500,500);
}

string Personaje::getNombre()
{
    return nombre;
}
void Personaje::imprimirImagen()
{
    QPixmap  pix(":/bunny.png");

    //Se conecta la imagen a la etiqueta
    (*etiqueta)->setPixmap(pix.scaled(100,100,Qt::KeepAspectRatio));
    //Se conecta el boton con un metodo

}

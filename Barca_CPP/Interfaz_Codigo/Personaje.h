/*
Archivo: Personaje.h
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
Clase: Personaje
Responsabilidad: Modelar un personaje en el juego, con atributos como el nombre, el personaje que come o si esta vivo. Con esta clase representaremos al Robot, al Conejo, la Lechuga y al Zorro, y realmente a cualquier otro personaje que se quiera implementar.
Relaciones:
- Contiene a Personaje : A través de un vector de punteros a la clase Personaje
*/

#ifndef _PERSONAJE_H_
#define _PERSONAJE_H_
#include <iostream>
#include <vector>
#include <QPushButton>
#include "ui_mainwindow.h"
#include <QPixmap>
#include <QMainWindow>

using namespace std;

class Personaje
{
  // Atributos
protected:
  /*
  nombre del personaje
  */
  string nombre;
  // el atributo comando determinará lo que debemos digitar por teclado para mover al personaje
  string comando;
  // Vector de personajes que el Personaje actual puede comer
  vector<Personaje *> personajesQueCome;
  // variable apra saber si el personaje esta vivo
  bool estaVivo;
  //boton al que esta asignado el personaje
  QPushButton **boton;
  //Direccion URL de la imagen
  string url;
  //Etiqueta dentro del ui
  QLabel **etiqueta;

public:
  // Constructor : Si el comando es mayor a dos letras pasara a valer la primera letra del nombre
  Personaje(string nombre, string comando, QPushButton* boton, QLabel *etiqueta, string url);
  // Destructor
  virtual ~Personaje();
  /*
  retorna el nombre del personaje
*/
  virtual string getNombre();
  /*
  metodo de QT para imprimir al personaje
*/
  virtual void imprimirImagen();
  /*
  metodo de QT para el boton del personaje
*/
  virtual void handleButton();
  /*
  Saber si el personaje es un guardian
  */
  bool getEsGuardian();

};

#endif

/**
Archivo: Robot.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Robot.h"
#include <stdio.h>

/*
  Constructor
*/
Robot::Robot(string nombre, string comando, QPushButton *boton, QLabel *etiqueta, string url ) :
    Personaje(nombre,comando,boton,etiqueta,url)
{
}

/*
  Destructor
*/
Robot::~Robot()
{
}

//QT
void Robot::handleButton()
{
    cout<<"Test Move Robot"<<endl;
    (*etiqueta)->move(250,190);
}

void Robot::imprimirImagen()
{
    QPixmap  pix(":/Robot.png");

    //Se conecta la imagen a la etiqueta
    (*etiqueta)->setPixmap(pix.scaled(100,100,Qt::KeepAspectRatio));
    //Se conecta el boton con un metodo
}

bool Robot::getEsGuardian()
{
    return esGuardian;
}

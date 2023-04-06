/**
Archivo: Lechuga.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Lechuga.h"
#include <stdio.h>

/*
  Constructor
*/
Lechuga::Lechuga(string nombre, string comando, QPushButton *boton, QLabel *etiqueta, string url ) :
    Personaje(nombre,comando,boton,etiqueta,url)
{
}

/*
  Destructor
*/
Lechuga::~Lechuga()
{
}

//QT
void Lechuga::handleButton()
{
    cout<<"Test Move Fox"<<endl;
    (*etiqueta)->move(250,190);
}

void Lechuga::imprimirImagen()
{
    QPixmap  pix(":/Lettuce.png");

    //Se conecta la imagen a la etiqueta
    (*etiqueta)->setPixmap(pix.scaled(100,100,Qt::KeepAspectRatio));
    //(*etiqueta)->setAlignment(Qt::AlignCenter);
    //Se conecta el boton con un metodo

}

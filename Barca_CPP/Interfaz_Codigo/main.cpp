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

#include "mainwindow.h"
#include <QApplication>
#include <QGraphicsScene>
#include <QGraphicsRectItem>
#include <QGraphicsView>



int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();
    return a.exec();
}

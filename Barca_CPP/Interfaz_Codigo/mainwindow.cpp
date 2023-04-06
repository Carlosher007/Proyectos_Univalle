/**
Archivo: mainwindow.cpp
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
Utilidad: Representar la ventana del juego
*/

#include "mainwindow.h"


MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setMinimumSize(size());
    this->setMaximumSize(size());
    this->setWindowTitle("Barca");

    class Lechuga *lechuga = new class Lechuga("Lechuga", "L", ui->Lechuga, ui->Lettuce,":/Lettuce.png");
    ui->Lettuce->setAlignment(Qt::AlignCenter);
    lechuga->imprimirImagen();
    connect(ui->Lechuga, SIGNAL (released()),this, SLOT (handleButtonL()));


    class Conejo *conejo = new class Conejo("Conejo", "C", ui->Conejo, ui->Bunny,":/bunny.png");
    conejo->imprimirImagen();
    ui->Bunny->setAlignment(Qt::AlignCenter);
    connect(ui->Conejo, SIGNAL (released()),this, SLOT (handleButtonB()));

    class Zorro *zorro = new  class Zorro("Zorro", "Z", ui->Lobo, ui->Fox,":/Robot.png");
    zorro->imprimirImagen();
    connect(ui->Lobo, SIGNAL (released()),this, SLOT (handleButtonF()));


    class Robot *robot =new class Robot("Robot", "R", ui->Robot, ui->Guardian,"a");
    robot->imprimirImagen();
    connect(ui->Robot, SIGNAL (released()),this, SLOT (handleButtonR()));

    connect(ui->BotonDer, SIGNAL (released()),this, SLOT (moveRight()));
    connect(ui->BotonIzq, SIGNAL (released()),this, SLOT (moveLeft()));

    //destruyendo todo
    delete robot;
    robot =nullptr;
    delete zorro;
    zorro =nullptr;
    delete lechuga;
    lechuga= nullptr;
    delete conejo;
    conejo =nullptr;


}


MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::mensajeDeGameOver(QString  &mensaje)
{
    if(gano())
    {
        QString final = mensaje+"\n ¿Desea volver a jugar?";
        QMessageBox::StandardButton reply = QMessageBox::question(this,"Has Ganado",final,QMessageBox::Yes | QMessageBox::No);
        if(reply==QMessageBox::Yes)
        {
           restart();
        }else
        {
            close();
        }
    }else
    {
        QString final = mensaje+"\n ¿Desea volver a jugar?";
        QMessageBox::StandardButton reply = QMessageBox::question(this,"Has Perdido",final,QMessageBox::Yes | QMessageBox::No);
        if(reply==QMessageBox::Yes)
        {
           restart();
        }else
        {
            close();
        }
    }


}

void MainWindow::perdio()
{
    disconnect(ui->Lechuga, SIGNAL (released()),this, SLOT (handleButtonL()));
    disconnect(ui->Conejo, SIGNAL (released()),this, SLOT (handleButtonB()));
    disconnect(ui->Lobo, SIGNAL (released()),this, SLOT (handleButtonF()));
    disconnect(ui->Robot, SIGNAL (released()),this, SLOT (handleButtonR()));
    disconnect(ui->BotonDer, SIGNAL (released()),this, SLOT (moveRight()));
    disconnect(ui->BotonIzq, SIGNAL (released()),this, SLOT (moveLeft()));
    juego=false;
}

void MainWindow::restart()
{
    c=0;
    z=0;
    r=0;
    l=0;
    juego =true;

    ui->Fox->move(80,190);
    ui->Bunny->move(80,100);
    ui->Lettuce->move(80,280);
    ui->Guardian->move(80,390);
    connect(ui->Lechuga, SIGNAL (released()),this, SLOT (handleButtonL()));
    connect(ui->Conejo, SIGNAL (released()),this, SLOT (handleButtonB()));
    connect(ui->Lobo, SIGNAL (released()),this, SLOT (handleButtonF()));
    connect(ui->Robot, SIGNAL (released()),this, SLOT (handleButtonR()));
    connect(ui->BotonDer, SIGNAL (released()),this, SLOT (moveRight()));
    connect(ui->BotonIzq, SIGNAL (released()),this, SLOT (moveLeft()));
    barca.limpiarBarca();
}

bool MainWindow::gano()

{
    if(c==3 and z==3 and l==3 and r==3)
    {
        return true;
    }
    else
    {
        return false;
    }
}
bool MainWindow::getEstadoJuego()
{
    return juego;
}

void MainWindow::moveLeft()
{
    if(barca.sePuedeMover() and barca.getPosicion()==1)
    {
        cout<<"Se mueve la barca"<<endl;
        barca.moverBarca();
        if(c==2)
        {

           c=1;
           ui->Bunny->move(250,100);
        }

        if(r==2)
        {
           ui->Guardian->move(250,390);
           r=1;
        }

        if(z==2)
        {
           ui->Fox->move(250,190);
           z=1;

        }
        if(l==2)
        {
           ui->Lettuce->move(250,280);
           l=1;
        }
    }
}
void MainWindow::moveRight()
{
    if(barca.sePuedeMover() and barca.getPosicion()==0)
    {
        cout<<"Se mueve la barca"<<endl;
        barca.moverBarca();
        if(c==1)
        {

           c=2;
           ui->Bunny->move(420,100);
        }

        if(r==1)
        {
           ui->Guardian->move(420,390);
           r=2;
        }

        if(z==1)
        {
           ui->Fox->move(420,190);
           z=2;

        }
        if(l==1)
        {
           ui->Lettuce->move(420,280);
           l=2;
        }
    }

}

void MainWindow::handleButtonF()
{
    QString mensaje = "El zorro salto al agua y se ahogo";
    const string nombre= "Zorro";
    if(barca.getPosicion()==0)
    {
        if(z==0 and barca.hayEspacio())
        {
          ui->Fox->move(250,190);
          barca.agregarPersonaje(nombre);
          changePlaceF();
          } else if(z==1)
        {
          ui->Fox->move(80,190);
          barca.elminarPersonaje(nombre);
          changePlaceF();

        } else {
            cout<<"El zorro se ahogo "<<endl;
            ui->Fox->move(420,190);
            mensajeDeGameOver(mensaje);
            changePlaceF();
            perdio();

        }
    } else if(barca.getPosicion()==1)
    {
        if(z==2)
        {
          ui->Fox->move(590,190);
          changePlaceF();
          barca.elminarPersonaje(nombre);
        } else if(z==3)
        {
          ui->Fox->move(420,190);
          changePlaceF();
          barca.agregarPersonaje(nombre);
        } else{
            cout<<"El zorro se ahogo"<<endl;
            ui->Fox->move(250,190);
            mensajeDeGameOver(mensaje);
            changePlaceF();
            perdio();

        }

    }
    if(cazar(z,c))
    {
        mensaje = "El zorro se comio al conejo";
        cout<<"El zorro se comio al conejo"<<endl;
        mensajeDeGameOver(mensaje);
    }
}

void MainWindow::handleButtonB()
{


    const string nombre= "Conejo";
    QString mensaje = "El conejo salto al agua y se ahogo";
    if(barca.getPosicion()==0)
    {
        if(c==0  and barca.hayEspacio())
        {
          ui->Bunny->move(250,100);
          barca.agregarPersonaje(nombre);
          changePlaceB();

        } else if(c==1)
        {
          ui->Bunny->move(80,100);
          changePlaceB();
          barca.elminarPersonaje(nombre);
        } else
        {
            cout<<"El Conejo se ahogo "<<endl;
            ui->Bunny->move(420,100);
            mensajeDeGameOver(mensaje);
            changePlaceB();
            perdio();
        }
    }   else if(barca.getPosicion()==1)
    {
        if(c==2)
       {
         ui->Bunny->move(590,100);
         changePlaceB();
         barca.elminarPersonaje(nombre);
       } else if(c==3)
       {
         ui->Bunny->move(420,100);
         changePlaceB();
         barca.agregarPersonaje(nombre);
       } else
        {
        cout<<"El Conejo se ahogo"<<endl;
        ui->Bunny->move(250,100);
        mensajeDeGameOver(mensaje);
        changePlaceB();
        perdio();
        }
    }

    if(cazar(c,l) and cazar(c,z))
    {
        cout<<"El Conejo se comio a la lechuga y el Zorro al conejo"<<endl;
        mensaje="El Conejo se comio a la lechuga y luego fue cazado por el Zorro";
        mensajeDeGameOver(mensaje);
    } else{
        if(cazar(c,z))
        {
            cout<<"El Zorro se comio al Conejo"<<endl;
            mensaje="El Zorro se comio al Conejo";
            mensajeDeGameOver(mensaje);
        }
        if(cazar(c,l))
        {
            cout<<"El Conejo se comio a la lechuga"<<endl;
            mensaje="El Conejo se comio a la lechuga";
            mensajeDeGameOver(mensaje);
        }
    }

    if(gano())
    {
        cout<<"Juego terminado"<<endl;
        mensaje="Juego terminado";
        mensajeDeGameOver(mensaje);
    }
}

void MainWindow::handleButtonL()
{

    const string nombre= "Lechuga";
    QString mensaje = "La lechuga salto al agua y se ahogo";
    if(barca.getPosicion()==0)
    {
        if(l==0  and barca.hayEspacio())
        {
          ui->Lettuce->move(250,280);
          barca.agregarPersonaje(nombre);
          changePlaceL();
        } else if(l==1)
        {
          ui->Lettuce->move(80,280);
          barca.elminarPersonaje(nombre);
          changePlaceL();

        } else
        {
            cout<<"La Lechuga se ahogo "<<endl;
            ui->Lettuce->move(420,280);
            mensajeDeGameOver(mensaje);

            changePlaceL();
            perdio();
        }
    } else if(barca.getPosicion()==1)
    {
        if(l==2)
       {
         ui->Lettuce->move(590,280);
         changePlaceL();
         barca.elminarPersonaje(nombre);
       } else if(l==3)
       {
          ui->Lettuce->move(420,280);
          changePlaceL();
          barca.agregarPersonaje(nombre);
       } else
        {
            cout<<"La Lechuga se ahogo "<<endl;
            ui->Lettuce->move(250,280);
            mensajeDeGameOver(mensaje);

            changePlaceL();
            perdio();
        }
    }

    if(cazar(l,c))
    {
        cout<<"El Conejo se comio a la lechuga"<<endl;
        mensaje="El Conejo se comio a la lechuga";
        mensajeDeGameOver(mensaje);
    }

}

void MainWindow::handleButtonR()
{

    const string nombre= "Robot";
    QString mensaje = "";
    if(r==0  and barca.hayEspacio())
    {
      ui->Guardian->move(250,390);
      barca.agregarPersonaje(nombre);
      changePlaceR();
    } else if(r==1)
    {
      ui->Guardian->move(80,390);
      barca.elminarPersonaje(nombre);
      changePlaceR();
    }else if(r==2)
    {
      ui->Guardian->move(590,390);
      changePlaceR();
      barca.elminarPersonaje(nombre);

    } else if(r==3)
    {
       ui->Guardian->move(420,390);
       changePlaceR();
       barca.agregarPersonaje(nombre);
    }
    if(cazar(c,l) and cazar(c,z))
    {
        cout<<"El Conejo se comio a la lechuga y el Zorro al conejo"<<endl;
        mensaje="El Conejo se comio a la lechuga y luego fue cazado por el Zorro";
        mensajeDeGameOver(mensaje);
    } else {
        if(cazar(c,l))
        {
            cout<<"El Conejo se comio a la lechuga"<<endl;
            mensaje="El Conejo se comio a la lechuga";
            mensajeDeGameOver(mensaje);

        }
        if(cazar(c,z))
        {
            cout<<"El Zorro se comio al Conejo"<<endl;
            mensaje="El Zorro se comio al Conejo";
            mensajeDeGameOver(mensaje);
        }

    }

}

void MainWindow::changePlaceB()
{
    switch (c)
    {
    case 0:
        c=1;
        break;
    case 1:
        c=0;
        break;
    case 2:
        c=3;
        break;
    case 3:
        c=2;
        break;
    }

}

void MainWindow::changePlaceF()
{
    switch (z)
    {
    case 0:
        z=1;
        break;
    case 1:
        z=0;
        break;
    case 2:
        z=3;
        break;
    case 3:
        z=2;
        break;

    }
}

void MainWindow::changePlaceR()
{
    switch (r)
    {
    case 0:
        r=1;
        break;
    case 1:
        r=0;
        break;
    case 2:
        r=3;
        break;
    case 3:
        r=2;
        break;

    }

}

void MainWindow::changePlaceL()
{
    switch (l)
    {
    case 0:
        l=1;
        break;
    case 1:
        l=0;
        break;
    case 2:
        l=3;
        break;
    case 3:
        l=2;
        break;
    }
}

bool MainWindow::cazar(int &a, int &b)
{
    if(a==b and a!=r)
    {
      perdio();
      return true;

    }else
    {
        return false;
    }
}

void MainWindow::over()
{

}

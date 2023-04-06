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

Barca::Barca()
{
}

Barca::~Barca()
{
}
int Barca::buscar(const string &aux){
    int index = 0;
    for(string str : barca){
        if(str==aux){
            return index;
        }else{
            index+=1;
        }
    }
    return -1;
}

void Barca::agregarPersonaje(const string&personaje)
{
    if(barca.size()>=2)
    {
    //    cout<<"La barca esta llena"<<endl;
    } else
    {
       barca.push_back(personaje);
    //    cout<<"Se Agrego: "<<personaje<<", Barca size: "<<barca.size()<<endl;
    }
}
/*
void Barca::elminarPersonaje(const string &personaje)
{
    //cout<<personaje<<endl;
    cout<<"-----------------------------------------------------------------------------------"<<endl;


    for(int i = 0; i<barca.size();i++)
    {
        if(barca[i]==personaje)
        {
        cout<<"Se selecciono: "<<barca[i]<<" Personaje: "<<personaje<<endl;

            barca.erase(barca.begin()+i) ;
        }

        cout<<" Elemento que queda: "<<barca.front()<<" Barca size: "<<barca.size()<<endl;

        break;
    }
}*/

void Barca::elminarPersonaje(const string &personaje)
{

    cout<<"-----------------------------------------------------------------------------------"<<endl;

    int indiceAEliminar = buscar(personaje);

    if(indiceAEliminar==-1){
        return;
    }

    barca.erase(barca.begin()+indiceAEliminar);

    // cout<<" Barca size: "<<barca.size()<<endl;

    // for(const string &str : barca)
    // {
    //         cout << str << endl;
    // }

}

bool Barca::hayEspacio()
{
    if(barca.size()<2)
    {
        return true;
    } else
    {
        return false;
    }
}

bool Barca::sePuedeMover()
{

    int indiceAEliminar = buscar("Robot");

    if(indiceAEliminar==-1)
    {
        return false;
    } else
    {
        return true;
    }
}

void Barca::moverBarca()
{
    if(posicion==0)
    {
        posicion=1;
    } else
    {
        posicion=0;
    }
}

int Barca::getPosicion()
{
    return posicion;
}

void Barca::limpiarBarca()
{
    barca.clear();
    posicion=0;
}

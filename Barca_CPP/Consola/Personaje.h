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

/*
APORTES

@Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
- Idear la clase en un principio
- Metodos getters y setters
- Metodo agregarPersonaje
- Metodo puedeComer

@Susana Valencia Bravo
<susana.valencia@correounivalle.edu.co>
- agregar un atributo comando a la clase
- Mejorar el metodo puede comer con el atributo comando
- agregar un vector de personaje que puede comer en vez de uno solo
- Mejorar el metodo agregarPersonajeQueCome para que funcione con el vector
*/

#ifndef _PERSONAJE_H_
#define _PERSONAJE_H_
#include <iostream>
#include <vector>
using namespace std;

class Personaje
{
  // Atributos
protected:
  string nombre;
  // el atributo comando determinará lo que debemos digitar por teclado para mover al personaje
  string comando;
  // Vector de personajes que el Personaje actual puede comer
  vector<Personaje *> personajesQueCome;
  bool estaVivo;

public:
  // Constructor : Si el comando es mayor a dos letras pasara a valer la primera letra del nombre
  Personaje(string nombre, string comando);
  // Destructor
  virtual ~Personaje();
  // Getters
  virtual string getNombre();
  virtual vector<Personaje *> getPersonajesQueCome();
  virtual bool getEstaVivo();
  virtual string getComando();
  // Setters
  virtual void setNombre(string nombre);
  virtual void setEstaVivo(bool estado);
  virtual void setComando(string comando);
  // Funciones
  /*
    agregarPersonajeQueCome: Añade un personaje al vector personajesQueCome de la clase
  */
  virtual void agregarPersonajeQueCome(Personaje *personajeQueCome);
  /*
    puedeComer : retorna true si existe algun personaje en el vector personajesQueCome cuyo nombre sea igual al nombre del personaje pasado por parametro
  */
  virtual bool puedeComer(Personaje *personaje);
};

#endif
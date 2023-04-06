/*
Archivo: Barca.h
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
Clase: Barca
Responsabilidad: Esta clase derivada de Lugar servira para hacer lo que hace su clase Padre (alamacenar personajes) y además poder hacer funciones con estos, como moverlos a la barca
Relaciones:
- Es parecido a un Lugar (esto debido a que agrega metodos
Relaciones que hereda:
- Contiene a Personaje : Por medio de un vector de punteros a Personaje
- Conoce a lugar: Por medio de un puntero a Lugar, que servira para transportar personajes al vecino actual
*/

/*
APORTES

@Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
- Idear la clase
- moverBarca (idear el metodo el cual luego sera mejorado)

@Susana Valencia Bravo
<susana.valencia@correounivalle.edu.co>
- Arreglar el metodo debido a que era un metodo que hacía que el juego dejara de funcionar, redefiniendo metodos como vincular y desvincular

@Jose Luis Hincapie Bucheli
<jose.bucheli@correounivalle.edu.co>
- agregar el setPosicion() en el metodo moverBarca para que así simular el movimiento de la barca dependiendo de su posicion
- imprimirLugar
*/

#ifndef _BARCA_H_
#define _BARCA_H_

#include "Lugar.h"

class Barca : public Lugar
{
  // Atributos
private:
public:
  // Constructor
  Barca(int pos = 1, string nombre = "Barca", int capacidad = 2);
  // Destructor
  virtual ~Barca();
  /*
    moverBarca : A traves del mapa que recibe como parametro (el cual contiene ambas orillas) podra verificar si la barca se puede mover y hacia que lugar, redefiniendo las relaciones de las orillas y la propia barca, a demás de cambiar su posición para imprimir la barca de forma diferente
  */
  void moverBarca(vector<Lugar *> *mapa, bool verificarConductor = true);

  /*
    imprimirLugar: imprime el nombre de la barca antecedido de un numero determinado de espacios el cual depende de la posicion actual de la barca, esto para hacer el efecto de que la barca se mueve
  */
  virtual string imprimirLugar();
};

#endif
/**
Archivo: Jugador.cpp
Autores:
Carlos Andrés Hernández
<carlos.hernandez.agudelo@correounivalle.edu.co >
Susana Valencia Bravo <susana.valencia@correounivalle.edu.co>
Jose Luis Hincapie Bucheli <jose.bucheli@correounivalle.edu.co>
Fecha creacion: 2022/02/07
Fecha ultima modificacion: 2022/02/07
licencia: GNU-GPL
*/
#include "Jugador.h"

Jugador::Jugador(Barca *barca, Orilla *orillaIzquierda, Orilla *orillaDerecha) : barca(barca)
{
  totalPersonajes = orillaIzquierda->numeroDePersonajes();
  mapa.push_back(orillaIzquierda);
  mapa.push_back(barca);
  mapa.push_back(orillaDerecha);
}

Jugador::~Jugador()
{
  for (Lugar *lugar : mapa)
  {
    if (lugar)
    {
      delete lugar;
      lugar = nullptr;
    }
  }
  mapa.clear();

  if (barca)
  {
    delete barca;
    barca = nullptr;
  }
}

bool Jugador::getEstaJuegoIniciado()
{
  return estaJuegoIniciado;
}

void Jugador::setEstaJuegoIniciado(bool estado)
{
  estaJuegoIniciado = estado;
}

void Jugador::inicio()
{

  system("cls");
  system("clear");
  cout << textoPurpura "Bienvenido al Juego de La Barca \nA continuacion veras las opciones que puedes elegir, por favor digite la opcion que desea" << endl;
  cout << endl;
  cout << endl;

  bool estado = true;

  while (estado)
  {
    string orden = "";
    cout << textoGris "Jugar : 1 \nPersonalizado : 2 \nSalir : 0 \n\nOpcion: ";
    getline(cin, orden);

    if (orden == "1")
    {
      estado = false;
      jugar();
    }
    else if (orden == "2")
    {
      estado = false;
      personalizado();
    }
    else if (orden == "0")
    {
      estado = false;
      exit(0);
    }
    else
    {
      cout << textoPurpura "Ingrese un valor valido" << endl;
    }
  }
}

// METODOS QUE UTILIZARA  A SU VEZ EL METODO PERSONALIZADO :

void Jugador::borrarPersonajes()
{
  // Borramos los personajes de los lugares que estan actualmente
  for (Lugar *lugar : mapa)
  {
    lugar->borrarVectorPersonajes();
  }
}

bool Jugador::esUnNumero(const string &str)
{
  for (char const &c : str)
  {
    if (std::isdigit(c) == 0)
      return false;
  }
  return true;
}

int Jugador::recibirNumero(string nombre)
{
  bool estado = true;
  string aux;
  int numero;
  while (estado)
  {
    cout << textoPurpura "Digite el numero de " << nombre << " en el juego: ";
    getline(cin, aux);
    if (esUnNumero(aux))
    {
      numero = stoi(aux);
      estado = false;
    }
    else
    {
      cout << textoRojo "Dato invalido" << endl;
    }
  }
  return numero;
}

// Retorna true si existe personaje con el nombre dado por parametro, en otro caso retorna false
bool Jugador::existePersonajeConNombre(string nombre)
{
  bool estado = false;
  if (mapa[0]->getPersonajes().size() != 0)
  {
    for (Personaje *personaje : mapa[0]->getPersonajes())
    {
      if (personaje->getNombre() == nombre)
      {
        estado = true;
      }
    }
  }
  else
  {
    return false;
  }
  if (estado)
  {
    return true;
  }
  else
  {
    return false;
  }
}

// Retorna true si existe personaje con el comando dado por parametro, en otro caso retorna false
bool Jugador::existePersonajeConComando(string comando)
{
  bool estado = false;
  if (mapa[0]->getPersonajes().size() != 0)
  {
    for (Personaje *personaje : mapa[0]->getPersonajes())
    {
      if (personaje->getComando() == comando)
      {
        estado = true;
      }
    }
  }
  else
  {
    return false;
  }
  if (estado)
  {
    return true;
  }
  else
  {
    return false;
  }
}

string Jugador::agregarNombrePersonaje()
{
  string nombre;
  bool estado = true;
  while (estado)
  {
    cout << textoPurpura "¿Cual será el nombre del Personaje? : ";
    getline(cin, nombre);
    if (existePersonajeConNombre(nombre))
    {
      cout << textoRojo "¡El nombre ya existe!" << endl;
    }
    else
    {
      estado = false;
    }
  }
  return nombre;
}

string Jugador::agregarComandoPersonaje()
{
  bool estado = true;
  string comando;
  while (estado)
  {
    bool auxEstado2 = true;
    while (auxEstado2)
    {
      cout << textoPurpura "Ingrese el comando del Personaje : ";
      getline(cin, comando);
      if (comando.size() == 0 or comando.size() > 2)
      {
        cout << textoRojo "Ingrese un comando valido" << endl;
      }
      else
      {
        auxEstado2 = false;
        // Ponemos el comando en mayuscula
        for (int i = 0; i < comando.length(); i++)
        {
          comando[i] = toupper(comando[i]);
        }
      }
    }
    if (existePersonajeConComando(comando))
    {
      cout << textoRojo "¡El comando ya existe!" << endl;
    }
    else
    {
      estado = false;
    }
  }
  return comando;
}

void Jugador::agregarGuardianes()
{
  system("cls");
  system("clear");
  string nombre;
  string comando;

  int numero = recibirNumero("guardianes");

  for (int i = 0; i < numero; i++)
  {
    system("cls");
    system("clear");
    // Preguntamos por el nombre
    nombre = agregarNombrePersonaje();
    // Preguntamos por el comando
    comando = agregarComandoPersonaje();
    // Creamos el guardian y lo agregamos en cada lugar como guardian, a demás de almaenarlo en la izquierda
    Personaje *guardian = new Personaje(nombre, comando);
    mapa[0]->agregarPersonaje(guardian);
    for (Lugar *lugar : mapa)
    {
      lugar->agregarGuardian(guardian);
    }
    guardian = nullptr;
  }
}

void Jugador::agregarPersonajes()
{
  system("cls");
  system("clear");
  string nombre;
  string comando;

  int numero = recibirNumero("personaje");

  for (int i = 0; i < numero; i++)
  {
    system("cls");
    system("clear");
    // Preguntamos por el nombre
    nombre = agregarNombrePersonaje();
    // Preguntamos por el comando
    comando = agregarComandoPersonaje();
    // Creamos el guardian y lo agregamos en cada lugar como guardian, a demás de almaenarlo en la izquierda
    Personaje *personaje = new Personaje(nombre, comando);
    mapa[0]->agregarPersonaje(personaje);
    personaje = nullptr;
  }
}

bool Jugador::verficiarEsGuardian(Personaje *personaje)
{
  bool esGuardian = false;
  for (Personaje *guardian : mapa[0]->getGuardianes())
  {
    if (personaje->getNombre() == guardian->getNombre())
    {
      esGuardian = true;
    }
  }
  return esGuardian;
}

string Jugador::recibirOpcionAgregarPersonaje(Personaje *personaje)
{
  system("cls");
  system("clear");
  string opcion;
  cout << textoPurpura "¿Desea agregar una presa para el personaje " << personaje->getNombre() << " ? (S/N): ";
  getline(cin, opcion);

  // Pasamos la opcion a mayuscula
  for (int i = 0; i < opcion.length(); i++)
  {
    opcion[i] = toupper(opcion[i]);
  }
  return opcion;
}

void Jugador::imprimirPosiblesPersonajes(Personaje *personaje)
{
  if (mapa[0]->getGuardianes().size() == 1 or mapa[0]->getGuardianes().size() == 0)
  {
    cout << textoPurpura "Posibles Personajes: " << endl;
    for (Personaje *personajeAux : mapa[0]->getPersonajes())
    {
      if (personajeAux->getNombre() != personaje->getNombre())
      {
        for (Personaje *guardian : mapa[0]->getGuardianes())
        {
          if (guardian->getNombre() != personajeAux->getNombre())
          {
            cout << textoPurpura << personajeAux->getNombre() << endl;
          }
        }
      }
    }
  }
}

string Jugador::preguntarPorElNombrePresa(Personaje *personaje)
{
  string nombrePersonajeAComer;
  bool estado = true;
  while (estado)
  {
    bool puedoSalir = true;
    cout << textoPurpura "¿Digite el nombre de la presa? : ";
    getline(cin, nombrePersonajeAComer);
    if (mapa[0]->existePersonajeConNombre(nombrePersonajeAComer))
    {
      // Verificamos que no sea guardian
      for (Personaje *guardian : mapa[0]->getGuardianes())
      {
        if (nombrePersonajeAComer == guardian->getNombre())
        {
          cout << textoRojo "El nombre coincide con un guardian, esa relacion no es posible" << endl;
          puedoSalir = false;
        }
      }
      if (personaje->getNombre() == nombrePersonajeAComer)
      {
        cout << textoRojo "El nombre coincide el mismo personaje, en que piensas!!" << endl;
        puedoSalir = false;
      }
    }
    else
    {
      cout << textoRojo "¡No existe ese nombre! " << endl;
      puedoSalir = false;
    }
    if (puedoSalir)
    {
      estado = false;
    }
  }
  return nombrePersonajeAComer;
}

void Jugador::crearRelaciones()
{
  system("cls");
  system("clear");
  // Creamos las relaciones entre personajes (quien come a quien)
  for (Personaje *personaje : mapa[0]->getPersonajes())
  {
    // Verificamos que el personaje no sea guardian pues guardian no podra comer a nadie
    bool esGuardian = verficiarEsGuardian(personaje);
    // Si el personaje no es guardian procedemos a crear las relaciones si el usuario quiere
    if (!esGuardian)
    {
      string opcion;
      string nombrePersonajeAComer;
      bool estado = true;
      while (estado)
      {
        opcion = recibirOpcionAgregarPersonaje(personaje);
        // Si no quiere crear relaciones pues pasamos
        if (opcion == "N")
        {
          estado = false;
        }
        // Si quiere crear relaciones procedemos a preguntar
        else if (opcion == "S")
        {
          // Imprimimos los posibles personajes que puede poner en la relacion, teniendo en cuenta que no puede estar el guardian ni el personaje mismo
          imprimirPosiblesPersonajes(personaje);
          cout << endl;
          // Agregar una presa
          nombrePersonajeAComer = preguntarPorElNombrePresa(personaje);
          // Agregar la relacion
          Personaje *personajeRelacion = mapa[0]->buscarPorNombre(nombrePersonajeAComer);
          personaje->agregarPersonajeQueCome(personajeRelacion);
          estado = false;
        }
        else
        {
          estado = false;
        }
      }
    }
  }
}

// Metodo de juego personalizado
void Jugador::personalizado()
{
  system("cls");
  system("clear");
  cout << textoRojo "Bienvenido al modo personalizado, por favor lea atentamente y digite lo que se le pida, gracias. " << endl;

  // Borramos los personajes que hay actualmente
  borrarPersonajes();

  // Creamos y añadimos los guadianes
  agregarGuardianes();

  // Creamos y añadimos los personajes
  agregarPersonajes();

  //  Creamos relaciones
  crearRelaciones();
  // Llamamos al juego

  totalPersonajes = mapa[0]->numeroDePersonajes();

  jugar();
}

void Jugador::mostrarJuego()
{
  // Limpiar la consoa
  // cout << "\033c";
  system("cls");
  system("clear");
  cout << textoRojo "LA BARCA" << endl;
  cout << textoPurpura "Tu mision es pasar a cada uno de los personajes al lado derecho, sin que ninguno muera" << endl;
  cout << endl;
  cout << textoPurpura "Aquellos personajes en verde son " << textoVerde "guardianes" << textoPurpura ", los que estan en naranja son " << textoMarron "personajes normales" << endl;
  cout << textoPurpura "Recuerde que la barca solo se movera si hay un" << textoVerde " guardian dentro" << endl;
  cout << endl;

  // Se muestran los lugares
  for (Lugar *lugar : mapa)
  {
    if (lugar->getNombre() == "Barca")
    {
      cout << textoSubrayadoAzul << lugar->imprimirLugar();
    }
    if (lugar->getNombre() == "Izquierda")
    {
      cout << textoSubrayadoPurpura << lugar->imprimirLugar();
    }
    if (lugar->getNombre() == "Derecha")
    {
      cout << textoSubrayadoVerde << lugar->imprimirLugar();
    }
  }

  cout << endl;
  cout << endl;

  // Se muestran los personajes de cada lugar
  for (Lugar *lugar : mapa)
  {
    for (Personaje *personaje : lugar->getPersonajes())
    {
      bool aux = true;
      if (!personaje->getEstaVivo())
      {
        cout << textoSubrayadoRojo << lugar->imprimirPersonaje(personaje) << endl;
        aux = false;
      }
      else
      {
        for (Personaje *guardian : barca->getGuardianes())
        {
          if (personaje->getNombre() == guardian->getNombre())
          {
            cout << textoVerde << lugar->imprimirPersonaje(personaje) << endl;
            aux = false;
          }
        }
      }
      if (aux == true)
      {
        cout << textoMarron << lugar->imprimirPersonaje(personaje) << endl;
      }
    }
  }

  cout << endl;
}

void Jugador::jugar()
{
  do
  {
    mostrarJuego();
    while (true) // Recibir las entradas hasta que sea valida
    {
      try
      {
        recibirEntrada();
        break;
      }
      catch (string mensaje)
      {
        cout << mensaje << endl;
      }
    }
  } while (comprobarEstadoDelJuego());
}

string Jugador::recibirCaracteres(string pregunta)
{
  string orden = "";
  if (pregunta == "")
  {
    cout << textoGris " Ingrese una orden: ";
    getline(cin, orden);
  }
  else
  {
    cout << textoGris << pregunta;
    getline(cin, orden);
  }

  if (orden.size() == 0)
    throw(string) textoPurpura " ¡No ha ingresado ninguna orden! ";

  if (orden.size() > 2)
    throw(string) textoPurpura " ¡Solo puedes ingresar dos letras como maximo! ";

  // Pasamos la orden a mayuscula
  for (int i = 0; i < orden.length(); i++)
  {
    orden[i] = toupper(orden[i]);
  }

  return orden;
}

void Jugador::recibirEntrada()
{
  string orden = recibirCaracteres("");
  if (orden == "B")
  {
    barca->moverBarca(&mapa);
  }
  else if (!getEstaJuegoIniciado())
  {
    if (orden == "S")
    {
      exit(0);
    }
    else if (orden == "R")
    {
      reiniciar();
    }
  }
  else
  {
    // Realizar el movimiento de un personaje
    for (Lugar *lugar : mapa)
    {
      if (lugar->moverPersonaje(orden))
      {
        return;
      }
    }
    throw textoPurpura "No hay ningun personaje que tenga como comando: " + orden;
  }
}

void Jugador::reiniciar()
{
  setEstaJuegoIniciado(true);
  // regresa todos los individuos a la orilla izquierda
  for (Lugar *lugar : mapa)
  {
    if (!(lugar->getNombre() == "Izquierda"))
    {
      lugar->moverATodos(mapa[0]);
    }
  }

  // Arreglar relaciones y posiciones
  mapa[0]->vincularVecino(barca);
  barca->vincularVecino(mapa[0]);
  barca->setPosicion(1);
  mapa[2]->desvincularVecino();

  // Ponemos a todos los personajes vivos
  for (Lugar *lugar : mapa)
  {
    for (Personaje *personaje : lugar->getPersonajes())
    {
      personaje->setEstaVivo(true);
    }
  }
}

bool Jugador::comprobarEstadoDelJuego()
{
  // Verifica que nadie este muerto
  for (Lugar *lugar : mapa)
  {
    for (Personaje *personaje : lugar->getPersonajes())
    {
      if (personaje->getEstaVivo() == false)
      {
        setEstaJuegoIniciado(false);
        mostrarJuego();
        // Mensaje
        // Texto obligatorio
        cout << textoRojo "PERDISTE" << endl;
        // Texto alternativo
        cout << textoPurpura "El personaje " << textoRojo << personaje->getNombre() << textoPurpura " salto al al agua, que desgracia... " << endl;
        menuDespuesDeGanarOPerder();
      }
    }
  }

  // se verifica si algun individuo ha podido comerse a otro
  for (Lugar *lugar : mapa)
  {
    Personaje *personajeMuerto = lugar->algunPersonajeHaSidoComido();
    if (personajeMuerto)
    {
      setEstaJuegoIniciado(false);
      mostrarJuego();
      // Mensaje
      // Texto obligatorio
      cout << textoRojo "PERDISTE" << endl;
      // Texto alternativo
      cout << textoPurpura "El personaje " << textoRojo << personajeMuerto->getNombre() << textoPurpura " ha sido cruelmente devorado..." << endl;

      menuDespuesDeGanarOPerder();
    }
  }

  // se verifica que todos los individuos han pasado a la orilla derecha
  if (mapa[2]->numeroDePersonajes() == totalPersonajes)
  {
    setEstaJuegoIniciado(false);
    mostrarJuego();
    // Texto obligatorio
    cout << "GANASTE" << endl;
    menuDespuesDeGanarOPerder();
  }

  return true;
}

void Jugador::menuDespuesDeGanarOPerder()
{
  cout << endl;
  cout << textoPurpura "Para salir del juego presione la letra " << textoAzul "S" << textoPurpura " para reiniciar presione la letra " << textoAzul "R" << endl;
  cout << endl;

  string orden;
  try
  {
    orden = recibirCaracteres("");
  }
  catch (string mensaje)
  {
    exit(0);
  }

  if (orden == "S")
    exit(0);
  else if (orden == "R")
    reiniciar();
  else
    exit(0);
}
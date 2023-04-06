import Principal._
import scala.collection.parallel.immutable.ParVector
import org.scalameter._
val standardConfig = config (
  Key.exec.minWarmupRuns := 10 ,
  Key.exec.maxWarmupRuns := 20 ,
  Key.exec.benchRuns := 15 ,

  Key.verbose := false
) withWarmer (Warmer.Default())

//------------------------------------------------------------------------------
//-------------------------------RHOER VS RHOERPAR------------------------------
//------------------------------------------------------------------------------
//Función que me permite crear distribuciones secuenciales
def crearrhoER(tamanio: Int): Distribution = {
  val frecuencias = for (i <- 0 until tamanio) yield Math.random()
  val decisiones = for (i <- 0 until tamanio) yield i.toDouble
  (frecuencias.toVector, decisiones.toVector)
}

def crearrhoERPar(tamanio: Int): DistributionPar = {
  val frecuencias = for (i <- 0 until tamanio) yield Math.random()
  val decisiones = for (i <- 0 until tamanio) yield i.toDouble
  (frecuencias.to(ParVector), decisiones.to(ParVector))
}

//Creación de valores
val distribucionesSeq = Vector(
crearrhoER(100),
crearrhoER(500),
crearrhoER(1000),
crearrhoER(5000),
crearrhoER(8000),
)

val distribucionesPar = Vector(
crearrhoERPar(100),
crearrhoERPar(500),
crearrhoERPar(1000),
crearrhoERPar(5000),
crearrhoERPar(8000),
)

/*
println("------------------rhoER vs rhoERPar------------------")
println("TIEMPOS SECUENCIALES:")
for (distribucion <- distribucionesSeq){
  val res = standardConfig measure {rhoER(distribucion)}
  println(res);
}

println("TIEMPOS PARALELOS:")
for (distribucion <- distribucionesPar){
  val res = standardConfig measure {rhoERPar(distribucion)}
  println(res);
}
*/
//------------------------------------------------------------------------------
//-------------------------------RHO VS RHOPAR----------------------------------
//------------------------------------------------------------------------------

def crearDiscretizacion(tamanio: Int): Vector[Double] = {
  //Considerese tamanio como el rango de valores que desea tener
  //Por favor meter valores tal que sean divisibles sin residuo para rango
  val rango:Double = 1.0/tamanio
  val discretizacion = for (i <- 1 until tamanio) yield (rango*i.toDouble).toDouble
  discretizacion.toVector
}

//Creencias específicas
def b1(nags:Int):SpecificBeliefConf= {
  Vector.tabulate(nags)((i:Int)=> if (i <= nags/2) 0.3 else 0.9)
}

def b2(nags:Int):SpecificBeliefConf= {
  Vector.tabulate(nags)((i:Int) => (i+1).toDouble/nags.toDouble)
}

//Se inicializan creencias
val b1_1 = b1(100)
val b1_2 = b1(1000)
val b1_3 = b1(10000)
val b1_4 = b1(100000)
val b1_5 = b1(10000000)

val b2_1 = b2(100)
val b2_2 = b2(1000)
val b2_3 = b2(10000)
val b2_4 = b2(100000)
val b2_5 = b2(1000000)

//Se inicializan discretizaciones
val d0 = crearDiscretizacion(10)
val d1 = crearDiscretizacion(25)
val d2 = crearDiscretizacion(50)

//val b1 = Vector(b1_1, b1_2, b1_3, b1_4, b1_5)
val b2 = Vector(b2_1, b2_2, b2_3, b2_4, b2_5)
val d = Vector(d0, d1, d2)
/*
println("------------------rho vs rhoPar------------------")
println("TIEMPOS SECUENCIALES:")
println("--Con b1")
for (creencia <- b1; discrecion <- d){
  val tiempo = standardConfig measure {rho(discrecion, creencia)}
  println(tiempo)
}

println("--Con b2")
for (creencia <- b2; discrecion <- d){
  val tiempo = standardConfig measure {rho(discrecion, creencia)}
  println(tiempo)
}

println("TIEMPOS PARALELOS:")
println("--Con b1")
for (creencia <- b1; discrecion <- d){
  val tiempo = standardConfig measure {rhoPar(discrecion, creencia)}
  println(tiempo)
}

println("--Con b2")
for (creencia <- b2; discrecion <- d){
  val tiempo = standardConfig measure {rhoPar(discrecion, creencia)}
  println(tiempo)
}
*/
//------------------------------------------------------------------------------
//--------------------CONFBIASUPDATE VS CONFBIASUPDATEPAR-----------------------
//------------------------------------------------------------------------------

//Se crean funciones de influencia
def i1(nags:Int) : SpecificWeightedGraph = {
  ((i:Int,j:Int) => if (i==j) 1.0
  else if (i<j) 1.0/(j-i).toDouble
  else 0.0, nags)
}

def i2(nags:Int) : SpecificWeightedGraph = {
  ((i:Int,j:Int) => if (i==j) 1.0
  else if (i<j) (j-i).toDouble/nags.toDouble
  else (nags-(i-j)).toDouble/nags.toDouble, nags)
}

//Inicializamos funciones de influencia
val i1_1 = i1(100)
val i1_2 = i1(500)
val i1_3 = i1(1000)
val i1_4 = i1(5000)
val i1_5 = i1(8000)
//i2 no considero necesario hacerlo ya que supongo no afecta nada en los tiempos
//Por lo que solo hago con i1.

println("------------------confBiasUpdate vs confBiasUpdatePar------------------")
/*
println("TIEMPOS SECUENCIALES:")
val timeSeq1 = standardConfig measure {
  confBiasUpdate(b1_1,i1_1)}
val timeSeq1 = standardConfig measure {
  confBiasUpdate(b1_2,i1_2)}
val timeSeq1 = standardConfig measure {
  confBiasUpdate(b1_3,i1_3)}
val timeSeq1 = standardConfig measure {
  confBiasUpdate(b1_4,i1_4)}
val timeSeq1 = standardConfig measure {
  confBiasUpdate(b1_5,i1_5)}

println("TIEMPOS PARALELOS:")
val timeSeq1 = standardConfig measure {
  confBiasUpdatePar(b1_1,i1_1)}
val timeSeq1 = standardConfig measure {
  confBiasUpdatePar(b1_2,i1_2)}
val timeSeq1 = standardConfig measure {
  confBiasUpdatePar(b1_3,i1_3)}
val timeSeq1 = standardConfig measure {
  confBiasUpdatePar(b1_4,i1_4)}
val timeSeq1 = standardConfig measure {
  confBiasUpdatePar(b1_5,i1_5)}
 */
//------------------------------------------------------------------------------
//------------------------------------SIMULATE----------------------------------
//------------------------------------------------------------------------------


val numeroAgentes = Vector(5,50,100,300,600)
val d0 = crearDiscretizacion(10)
val numeroVeces = Vector(5,10,50,100,300,400)

/*
println("Tiempos secuenciales: ")
for (i <- 0 until 5; j <- 0 until 6){
  val cant = numeroAgentes(i)
  println("Cantidad de agentes: " + cant)
  println("Número de veces: " + numeroVeces(j))
  val tiempos = standardConfig measure {for(b <- simulate(confBiasUpdate, i1(cant), b1(cant), numeroVeces(j))) yield rho (d0, b)}
  println(tiempos)
}

println("Tiempos paralelos: ")
for (i <- 0 until 5; j <- 0 until 6){
  val cant = numeroAgentes(i)
  println("Cantidad de agentes: " + cant)
  println("Número de veces: " + numeroVeces(j))
  val tiempos = standardConfig measure {for(b <- simulate(confBiasUpdatePar, i1(cant), b1(cant), numeroVeces(j))) yield rho (d0, b)}
  println(tiempos)
}
*/

//------------------------------------------------------------------------------
//------------------------EJEMPLOS PARA LA PRESENTACIÓN-------------------------
//------------------------------------------------------------------------------

//PUNTO 1

//PUNTO 2

//PUNTO 3
println("rhoER:")
val distribucionPrueba = crearrhoER(2000)
val distribucionPruebaPar = crearrhoERPar(2000)

val timeSeq1 = standardConfig measure {
  rhoER(distribucionPrueba)
}

val timeSeq1 = standardConfig measure {
  rhoERPar(distribucionPruebaPar)
}
println("-----------------------------------------")

println("rhoERPar:")
val timeSeq1 = standardConfig measure {
  rho(d1, b1_5)
}

val timeSeq1 = standardConfig measure {
  rhoPar(d1, b1_5)
}
println("-----------------------------------------")


println("confBiasUpdate: ")
val timeSeq1 = standardConfig measure {
  confBiasUpdate(b1_4,i1_4)}


val timeSeq1 = standardConfig measure {
  confBiasUpdatePar(b1_4,i1_4)}
println("-----------------------------------------")

println("Simulate")
val timeSeq1 = standardConfig measure {
  for(b <- simulate(confBiasUpdate, i1(300), b1(300), 100)) yield rho (d0, b)
}

val timeSeq1 = standardConfig measure {
  for(b <- simulate(confBiasUpdatePar, i1(300), b1(300), 100)) yield rho (d0, b)
}
println("-----------------------------------------")

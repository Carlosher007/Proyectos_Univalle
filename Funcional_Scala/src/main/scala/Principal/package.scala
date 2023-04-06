import scala.collection.mutable
import scala.collection.immutable._
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.immutable._
import common._
package object Principal {

  /*2.1.1*/

  // Versiones secuenciales
  type DistributionValues = Vector[Double] // Tipo para los valores, reales, de una distribución
  type Frequency = Vector[Double]
  // pi_k es una frecuencia de longitud k
  // si pi_k.length=k, 0 <= pi_k(i) <= 1, 0<=i<=k-1
  // pi_k.sum == 1
  type Distribution = (Frequency, DistributionValues)

  // Versiones paralelas
  type DistributionValuesPar = ParVector[Double]
  type FrequencyPar = ParVector[Double]
  type  DistributionPar = (FrequencyPar,
    DistributionValuesPar)

  /**
   * Función de Estaban y Rey
   * Se encarga de calcular una medida de polarización a partir de una Distribucion.
   * @param d variable del tipo Distribution (Frequency, DistributionValues)
   * @return Double, valor que mide una respectiva polarización
   */

  def rhoER(d: Distribution): Double = {
    //Funcion auxiliar
    def suma(xs: IndexedSeq[Double]): Double = {
      xs match {
        case x +: tail => x + suma(tail) // Si hay un elemento, sumarlo con el resto
        case _ => 0 // Si no hay elementos, sumarlo con 0 y terminar.
      }
    }

    //Se establecen valores de K y alpha
    val k = 10
    val alpha = 1.6

    //Tomamos la longitud del valor de Frecuencia de 'd'
    val n = d._1.length

    //Realizamos la doble sumatoria
    val sum = suma((0 until n).map(i => {
      suma((0 until n).map(j => {
        math.pow(d._1(i), 1 + alpha) * d._1(j) * math.abs(d._2(i) - d._2(j))
      }))
    }))

    //Se retorna el valor de la sumatoria con la multiplicacion de k
    k * sum
  }

  def rhoERPar(d: DistributionPar): Double = {
    val k = 10
    val alpha = 1.6
    val n = d._1.size
    val sum = (0 until n).par.map(i => {
      (0 until n).par.map(j => {
        math.pow(d._1(i), 1 + alpha) * d._1(j) * math.abs(d._2(i) - d._2(j))
      }).sum
    }).sum
    k * sum
  }


  /*2.2.1*/


  //Datos secuenciales
  type SpecificBeliefConf = Vector[Double] //Define una creencia como un vector de Doubles

  type GenericBeliefConf = Int => SpecificBeliefConf //Definir creencias especificas

  type Discretization = Vector[Double] //Definir discretizaciones

  /**
   * Función de polarización p 'rho'
   * Se encarga de calcular el valor de polarización de un conjunto de agentes respecto a una proposición
   *
   * @param d_k Discretización, o subdivisiones del intervalo [0,1] siendo 0<d1<d2<...<dk<1.
   *            Esta discretización se utiliza luego para subdividir el número de agentes presentes en cada intervalo
   * @param sb  SpecificBeliefConf. Define una creencia como un vector de Doubles, en el que para cada b[i] es un número
   *            entre 0 y 1, representando la creencia del agente i en una proposición
   * @return Double. Valor de polarización.
   */
  def rho(d_k: Discretization, sb: SpecificBeliefConf): Double = {
    //Realizamos subintervalos de la discretización 'd'
    val d_k1 = Vector(0.0) ++ d_k ++ Vector(1.00)
    val subd = (0 until d_k1.length - 1).map(i => (d_k1(i), d_k1(i + 1))).toVector

    //Calculamos distribuciones(frecuencias,distribuciones)
    def calcularDistribuciones(subd: Vector[(Double, Double)], sb: SpecificBeliefConf): Distribution = {
      val cantidades = for (intervalo <- subd) yield {
        for (belief <- sb if belief >= intervalo._1 && belief < intervalo._2 || (intervalo._2 == 1.0 && belief == 1.0)) yield belief
      }

      val frecuencias = (0 until cantidades.length).map(i => cantidades(i).length / sb.length.toDouble).toVector

      //Calculamos Y^b
      //Obtenemos los valores intermedios de cada lista de cantidades
      val distribuciones = for (cantidad <- cantidades) yield {
        if (cantidad.isEmpty) {
          0.0
        } else cantidad.sortWith(_ < _).drop(cantidad.length / 2).head
      }

      //Retornamos la tupla de frecuencias y distribuciones
      (frecuencias, distribuciones)
    }

    val distribucionFinal = calcularDistribuciones(subd, sb)
    rhoER(distribucionFinal)
  }

  def rhoPar(d_k: Discretization, sb:SpecificBeliefConf): Double = {
    val freq = d_k.map(x => sb(x.toInt))
    val dist = (freq.par, d_k.par)
    rhoERPar(dist)
  }



  /*2.3.1*/

  //Definir función de influencia
  type WeightedGraph = (Int, Int) => Double

  //Definir función de influencia especifica con n agentes
  type SpecificWeightedGraph = (WeightedGraph, Int)

  //Definir funciones de influencia especifica de forma generica
  //Si gsw:GenericWeightedGraph, entonces gsw(n) = sw tal que sw:SpecificWeightedGraph
  type GenericWeightedGraph = Int => SpecificWeightedGraph


  /**
   * Función showWeightedGraph
   * Se encarga de aplicar la función de pesos a cada par de agentes y retornar una matriz de n x n elementos,
   * donde n es el número de agentes, y cada elemento i, j de la matriz representa el peso (influencia) que tiene
   * el agente i sobre el agente j.
   *
   * @param swg SpecificWeightedGraph
   * @return IndexedSeq[IndexSeq[Double]], matriz de pesos
   */

  def showWeightedGraph(swg: SpecificWeightedGraph): IndexedSeq[IndexedSeq[Double]] = {
    val g = swg._1 //Función de pesos o funcion de influencia del grafo
    val n = swg._2 //Número de nodos del grafo o número de agentes
    val result = for {
      i <- 0 until n
      j <- 0 until n
    } yield g(i, j) // Vector que contiene los resultados de la aplicación de la función de influencia g sobre cada par de índices (i, j) en el rango de 0 a n-1

    //result.grouped(n).toIndexedSeq //Divide el vector result en una secuencia de subvectores de longitud n y lo convierte en secuencias
    //Haremos lo anterior pero con recursion

    def divideEnGrupos(result: IndexedSeq[Double], n: Int): IndexedSeq[IndexedSeq[Double]] = {
      if (result.length <= n) {
        IndexedSeq(result)
      } else {
        val (grupo, resto) = result.splitAt(n)
        IndexedSeq(grupo) ++ divideEnGrupos(resto, n)
      }
    }

    divideEnGrupos(result, n)
  }

  /*def showWeightedGraphPar(swg: SpecificWeightedGraph): IndexedSeq[IndexedSeq[Double]] = {
    //Nota de Sebastian: Jeje, apenas me vengo a dar cuenta de lo que me dijiste Carlos
    val g = swg._1
    val n = swg._2
    val result = for {
      i <- 0 until n
      j <- 0 until n
    } yield task {
      g(i, j)
    }.join()

    def divideEnGrupos(result: IndexedSeq[Double], n: Int): IndexedSeq[IndexedSeq[Double]] = {
      if (result.length <= n) {
        IndexedSeq(result)
      } else {
        val (grupo, resto) = result.splitAt(n)
        IndexedSeq(grupo) ++ divideEnGrupos(resto, n)
      }
    }

    divideEnGrupos(result, n)
  }*/

  /*2.3.2*/

  /**
   * Función confBiasUpdate
   *
   * @param b   Vector de creencias.
   * @param swg Función de influencia.
   * @return SpecificBeliefConf. Vector de creencias actualizado.
   * @utility Función que actualiza el vector de creencias de un agente i, en base a la función de influencia
   */


  def confBiasUpdate(b: SpecificBeliefConf, swg: SpecificWeightedGraph): SpecificBeliefConf = {
    val (f, n) = swg
    (0 until n).toVector.map { i =>
      val aux = (0 until n).filter(j => f(j, i) > 0)
      b(i) + (aux.map(j => (1 - Math.abs(b(j) - b(i))) * f(j, i) * (b(j) - b(i))).sum / Math.abs(aux.size))
    }
  }

  def confBiasUpdatePar(b: SpecificBeliefConf, swg: SpecificWeightedGraph): SpecificBeliefConf = {
    val (f, n) = swg
    val tasks = (0 until n).map(i => task {
      val aux = (0 until n).filter(j => f(j, i) > 0)
      b(i) + (aux.map(j => (1 - Math.abs(b(j) - b(i))) * f(j, i) * (b(j) - b(i))).sum / Math.abs(aux.size))
    })
    tasks.map(t => t.join()).toVector
  }

  /*2.3.3*/

    type FunctionUpdate = (SpecificBeliefConf, SpecificWeightedGraph) => SpecificBeliefConf //Representar las diferentes funciones de actualizacion que se quieran usar

    /*type FunctionUpdatePar = (SpecificBeliefConfPar, SpecificWeightedGraph) => SpecificBeliefConfPar */

    /**
     * Función simulate
     *
     * @param fu  , Función de actualización.
     * @param swg , Función de influencia.
     * @param b0  , Vector de creencias inicial.
     * @param t   , Número de iteraciones.
     * @return IndexedSeq[SpecificBeliefConf], Secuencia de vectores de creencias actualizados.
     * @utility Función que simula el proceso de actualización de creencias de un agente i, en base a la función de influencia
     * */
    def simulate(fu: FunctionUpdate, swg: SpecificWeightedGraph, b0: SpecificBeliefConf, t: Int): IndexedSeq[SpecificBeliefConf] = {
      val (f, n) = swg //Función de pesos o función de influencia del grafo y número de nodos del grafo o número de agentes
      
      def simulateRec(beliefs: IndexedSeq[SpecificBeliefConf], t: Int): IndexedSeq[SpecificBeliefConf] = {
        t match {
          case 0 => beliefs
          case n => simulateRec(beliefs :+ fu(beliefs.last, swg), t - 1)
        }
      }

      simulateRec(IndexedSeq(b0), t)
    }

  /*def simulatePar(fu: FunctionUpdate, swg: SpecificWeightedGraph, b0: SpecificBeliefConfPar, t: Int): IndexedSeq[SpecificBeliefConfPar] = {
    val (f, n) = swg //Función de pesos o función de influencia del grafo y número de nodos del grafo o número de agentes

    def simulateRec(beliefs: IndexedSeq[SpecificBeliefConfPar], t: Int): IndexedSeq[SpecificBeliefConfPar] = {
      t match {
        case 0 => beliefs
        case n => simulateRec(beliefs :+ fu(beliefs.last, swg), t - 1)
      }
    }

    simulateRec(IndexedSeq(b0), t)
  }*/

}



import Principal._
import scala.collection.parallel.immutable.ParVector

val pi1 = Vector(0.4, 0.6)
val pi2 = Vector(0.5, 0.5)
val pi3 = Vector(0.6, 0.4)
val pi4 = Vector(0.1, 0.9)
val pi5 = Vector(0.9, 0.1)

val y = Vector(1.0, 5.0)

rhoER((pi1,y))
rhoER((pi2,y))
rhoER((pi3,y))
rhoER((pi4,y))
rhoER((pi5,y))

rhoERPar((pi1.to(ParVector),y.to(ParVector)))
rhoERPar((pi2.to(ParVector),y.to(ParVector)))
rhoERPar((pi3.to(ParVector),y.to(ParVector)))
rhoERPar((pi4.to(ParVector),y.to(ParVector)))
rhoERPar((pi5.to(ParVector),y.to(ParVector)))

val d1 = Vector(0.2, 0.4, 0.6, 0.8)
val d2 = Vector(0.1, 0.4, 0.7, 0.9)

def b0(nags:Int):SpecificBeliefConf= {
  Vector.tabulate(nags)((i:Int) => {0.6}.toDouble)
}

def b1(nags:Int):SpecificBeliefConf= {
  Vector.tabulate(nags)((i: Int) => {if (i <= nags / 2) 0.6 else 0.4})
}

def b2(nags:Int):SpecificBeliefConf= {
  Vector.tabulate(nags)((i:Int)=> if (i <= nags/2) 0.3 else 0.9)
}

def b3(nags:Int):SpecificBeliefConf= {
  Vector.tabulate(nags)((i:Int) => (i+1).toDouble/nags.toDouble)
}

val b0_10 = b0(10)
val b0_20 = b0(20)

val b1_10 = b1(10)
val b1_20 = b2(20)

val b2_10 = b2(10)
val b2_20 = b2(20)

val b3_10 = b3(10)
val b3_20 = b3(20)

rho(d1, b1_10)
rho(d1, b2_10)
rho(d2, b1_10)
rho(d2, b2_10)
rho(d1, b3_10)
rho(d2, b3_10)

rhoPar(d1, b1_10)
rhoPar(d1, b2_10)
rhoPar(d2, b1_10)
rhoPar(d2, b2_10)
rhoPar(d1, b3_10)
rhoPar(d2, b3_10)

/*2.3*/
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

val i1_10 = i1(10)
val i2_10 = i2(10)
val i1_20 = i1(20)
val i2_20 = i2(20)

showWeightedGraph(i1_10)
showWeightedGraph(i2_10)

confBiasUpdate(b1_10,i1_10)
confBiasUpdate(b1_10,i2_10)
confBiasUpdate(b2_10,i1_10)
confBiasUpdate(b2_10,i2_10)
confBiasUpdate(b3_10,i1_10)
confBiasUpdate(b3_10,i2_10)

confBiasUpdatePar(b1_10,i1_10)
confBiasUpdatePar(b1_10,i2_10)
confBiasUpdatePar(b2_10,i1_10)
confBiasUpdatePar(b2_10,i2_10)
confBiasUpdatePar(b3_10,i1_10)
confBiasUpdatePar(b3_10,i2_10)

b1_10

for {
  b <- simulate(confBiasUpdate, i1_10, b1_10, 10)
} yield (b, rho(d1, b))

//Class implementation Example
class Rational(n: Int, d: Int) {
  private def gcd(x: Int, y: Int): Int = {
    if (x == 0) y
    else if (x < 0) gcd(-x, y)
    else if (y < 0) -gcd(x, -y)
    else gcd(y % x, x)
  }
  private val g = gcd(n, d)
  val numer: Int = n/g
  val denom: Int = d/g
  def +(that: Rational) =
    new Rational(numer * that.denom + that.numer * denom,
    denom * that.denom)
  def -(that: Rational) =
    new Rational(numer * that.denom - that.numer * denom,
    denom * that.denom)
  def *(that: Rational) =
    new Rational(numer * that.numer, denom * that.denom)
  def /(that: Rational) =
    new Rational(numer * that.denom, denom * that.numer)
} 

// in Scala, functions managed as objects !!!!!
f :A => B 
// est interprété 
package scala
  trait Function1[A, B] {
    def apply(x: A): B
}
// So functions are objects with apply methods

(x: Int) => x * x
// is expanded to : 
{ class AnonFun extends Function1[Int, Int] {
    def apply(x: Int) = x * x
  }
  new AnonFun
}
//or, shorter, using anonymous class syntax:
  new Function1[Int, Int] {
    def apply(x: Int) = x * x
}

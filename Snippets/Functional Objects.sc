class Rational1(n: Int, d: Int) {
  require(d != 0)
  def this(n: Int) = this(n, 1) // auxiliary constructor
  val numer: Int = n
  val denom: Int = d
  override def toString = numer + "/" + denom
  def add(that: Rational1): Rational1 =
    new Rational1(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  def lessThan(that: Rational1) =
    this.numer * that.denom < that.numer * this.denom
  def max(that: Rational1) =
    if (this.lessThan(that)) that else this
}


class Rational2(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  def this(n: Int) = this(n, 1)
  def add(that: Rational2): Rational2 =
    new Rational2(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  override def toString = numer + "/" + denom
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

class Rational(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  def this(n: Int) = this(n, 1)
  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)
  override def toString = numer + "/" + denom
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}


val x = new Rational(1, 2)
val y = new Rational(2, 3)
x + x * y
(x + x) * y
x + (x * y)

// Implicit conversions
implicit def intToRational(x: Int) = new Rational(x)
val r = new Rational(2,3)
2 * r

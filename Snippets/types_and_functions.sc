val hex2 = 0x00FF
val hex3 = 0x1

//If a floating-point literal ends in an F or f, it is a Float;
// otherwise it is a Double
val littleBigger = 3e5f
val anotherDouble = 3e5

val a = 'A'
// We can identify a character using its Unicode code point.
val d = '\u0041'

val escapes = "\\\"\'"
println("""Welcome to Ultamix 3000.
             Type "HELP" for help.""")
println("""|Welcome to Ultamix 3000.
          |Type "HELP" for help.""".stripMargin)

/* -------------------------------------------------- */

//Symbol LITERALS
def updateRecordByName(r: Symbol, value: Any) = {
  // code goes here
}
//updateRecordByName(favoriteAlbum, "OK Computer") wont work
updateRecordByName('favoriteAlbum, "OK Computer")

val s = 'aSymbol
val nm = s.name
nm == 'aSymbol.name

/* -------------------------------------------------- */

// String INTERPOLATION
val name = "reader"
println(s"Hello, $name!")

s"The answer is ${6 * 7}."

//The raw string interpolator behaves like s,
// except it does not recognize character literal escape sequences
println(raw"No\\\\escape!")

//The f string interpolator allows you to attach
// printf-style formatting instructions to embedded expressions
f"${math.Pi}%.5f"



// OPERATORS are METHODS
val sum = 1 + 2 //Scala invokes 1.+(2)
val a1 = List(1,2,3)
val b1 = List(4,5,6)


2^3
2 << 3
a1 ::: b1





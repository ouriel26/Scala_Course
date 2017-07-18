def gcd(x: Long, y: Long): Long =
  if (y == 0) x else gcd(y, x % y)


/**************************************************************/
/********************** For expressions ***********************/
/**************************************************************/
val filesHere = (new java.io.File(".")).listFiles
for (file <- filesHere)
  println(file)

for (i <- 1 to 4)
  println("Iteration " + i)

for (i <- 1 until 4)
  println("Iteration " + i)


// Not common in Scala...
for (i <- 0 to filesHere.length - 1)
  println(filesHere(i))


for (file <- filesHere if file.getName.endsWith(".scala"))
  println(file)
for (file <- filesHere)
  if (file.getName.endsWith(".log"))
    println(file)

for (
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".log")
) println(file)

/**************************************************************/
/************************* NESTED LOOPS ***********************/
/**************************************************************/

/**** NESTED LOOPS ****/

def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toList
//  "<-" implies a nested loop
//Below, two nested loops ...
def grep1(pattern: String) =
  for (
    file <- filesHere
    if file.getName.endsWith(".log");
    line <- fileLines(file)
    if line.trim.matches(pattern)
  ) println(file + ": " + line.trim)
grep1(".*log.*")
//Above , non-trivial computation : line.trim repeated twice
//Solution :
def grep(pattern: String) =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(file + ": " + trimmed)
grep(".*gcd.*")

/****** PRODUCING A NEW COLLECTION ****/
def scalaFiles =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
  } yield file
//Each time the body of the for expression executes,
// it produces one value, in this case simply file.
// When the for expression completes,
// the result will include all of the yielded values contained
// in a single collection .... here an Array[File]

val forLineLengths =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(".*for.*")
  } yield trimmed.length


/**************************************************************/
/************ Exception handling with try expressions ********/
/**************************************************************/
val n : Long = 4l
val half =
  if (n % 2 == 0)
    n/2 else
    throw new RuntimeException("n must be even")

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException
val f = new FileReader("hs_err_pid14849.log")
try {

  // Use and close file
} catch {
  case ex: FileNotFoundException => // Handle missing file
  case ex: IOException => // Handle other I/O error
}finally {
  f.close()  // Be sure to close the file
}

// A catch clause that yields a value
import java.net.URL
import java.net.MalformedURLException
def urlFor(path: String) =
  try {
    new URL(path)
  } catch {
    case e: MalformedURLException =>
      new URL("http://www.scala-lang.org")
  }

//In JAVA this return 2  because the finally clause
// overrules the try or catch clause
def f2(): Int = try return 1 finally return 2
//By contrast, this ...
def g(): Int = try 1 finally 2
// returns 1 !!!! POWER OF SCALA
// ==> The best way to think of finally clauses is
// as a way to ensure some side effect happens,
// such as closing an open file.


/**************************************************************/
/************************ Match expressions *******************/
/**************************************************************/
val l =  List("toto","titi")
val firstArg = if (l.length > 0) l(0) else ""
firstArg match {
  case "toto" => println("pepper") //the break is implicit
  case "chips" => println("salsa") //the break is implicit
  case "eggs" => println("bacon")  //the break is implicit
  case _ => println("huh?")
}

val friend =
  firstArg match {  //yield the value rather than print it
    case "toto" => "pepper"
    case "chips" => "salsa"
    case "eggs" => "bacon"
    case _ => "huh?"
  }
println(friend)

/**************************************************************/
/************************ Variable scopes *********************/
/**************************************************************/

val a = 1; {
  val a = 2 // Compiles just fine
  println(a) }
println(a)


/**************************************************************/
/**************** Refactoring imperative-style code ***********/
/**************************************************************/

def printMultiTable() = {
  var i = 1
  // only i in scope here
  while (i <= 10) {
    var j = 1
    // both i and j in scope here
    while (j <= 10) {
      val prod = (i * j).toString
      // i, j, and prod in scope here
      var k = prod.length
      // i, j, prod, and k in scope here
      while (k < 4) {
        print(" ")
        k += 1
      }
      print(prod)
      j += 1 }
    // i and j still in scope; prod and k out of scope
    println()
    i += 1 }
  // i still in scope; j, prod, and k out of scope
}

// transformed to :

// Returns a row as a sequence
def makeRowSeq(row: Int) =
  for (col <- 1 to 10) yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding + prod
  }
// Returns a row as a string
def makeRow(row: Int) = makeRowSeq(row).mkString
// Returns table as a string with one row per line
def multiTable() = {
  val tableSeq = // a sequence of row strings
    for (row <- 1 to 10)
      yield makeRow(row)
  tableSeq.mkString("\n")
}

println(multiTable)
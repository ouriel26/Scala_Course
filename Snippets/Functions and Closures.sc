/**************************************************************/
/********************** First-class functions *****************/
/**************************************************************/
(x: Int) => x + 1
val increase = (x: Int) => {
  println("We")
  println("are")
  println("here!")
  x+1
}
val someNumbers = List(-11, -10, -5, 0, 5, 10)
someNumbers.foreach((x: Int) => println(x))
someNumbers.filter((x: Int) => x > 0)
//TARGET TYPING
//because the targeted usage of an expression
// (in this case, an argument to someNumbers.filter())
// is allowed to influence the typing of that expression
// (in this case to determine the type of the x parameter).
someNumbers.filter(x => x > 0)


/**************************************************************/
/*********************** Placeholder syntax *******************/
/**************************************************************/

//To make a function literal even more concise, you can use
// underscores as placeholders for one or more parameters
someNumbers.filter(_ > 0)


val f = (_: Int) + (_: Int)
// here ":Int" cannot be infered by the compiler

/**************************************************************/
/****************** Partially applied functions ***************/
/**************************************************************/

someNumbers.foreach(println _)

// same as

someNumbers.foreach(x => println(x))
def sum(a: Int, b: Int, c: Int) = a + b + c
val a = sum _
//Given this code, the Scala compiler instantiates
// a function value that takes the three integer parameters
// missing from the partially applied function ex- pression,
// sum _, and assigns a reference
// to that new function value to the variable a.
val b = sum(1, _:Int, 3)
b(2)

/**************************************************************/
/************************** Closures **************************/
/**************************************************************/
var more = 1
val addMore = (x: Int) => x + more  //Closure (Open Term)
val addMore2 = (x: Int) => x + 1 // Closed Term but Not a Closure
// The closure sees the change (here if "more" has changed).
// And inversely, changes made by a closure to a captured
// variable are visible outside the closure

/**************************************************************/
/**************** Special function call forms *****************/
/**************************************************************/

// Repeated parameters
def echo(args: String*) =
  for (arg <- args) println(arg)

echo("one")
echo("hello", "world!")

val arr = Array("What's", "up", "doc?")
//Even String* is actually an array, calling "echo(arr)" will
// throw a type mismatch error .... rather that :
echo(arr: _*)



// Named Arguments
def speed(distance: Float, time: Float): Float =
  distance / time

speed(distance = 100, time = 10)
speed(time = 10, distance = 100)


// Default parameter values

def printTime(out: java.io.PrintStream = Console.out) =
  out.println("time = " + System.currentTimeMillis())


/**************************************************************/
/********************** Tail recursion ************************/
/**************************************************************/

//Functions like approximate, which call themselves
// as their last action, are called tail recursive.
// The Scala compiler detects tail recursion
// and replaces it with a jump back to the beginning
// of the function, after updating the function parameters
// with the new values.
//The moral is that you should not shy away
// from using recursive algo- rithms to solve your problem.
// Often, a recursive solution is more elegant
// and concise than a loop-based one.
// If the solution is tail recursive,
// there won’t be any runtime overhead to be paid

def boom(x: Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boom(x - 1) + 1

//calling boom(3) throws boom(3)
/*java.lang.Exception: boom!
  at .boom(<console>:5)
  at .boom(<console>:6)
  at .boom(<console>:6)
  at .boom(<console>:6)
  at .<init>(<console>:6)*/


def bang(x: Int): Int =
  if (x == 0) throw new Exception("bang!")
  else bang(x - 1)

//calling bang(5) throws
/*bang(5)
  java.lang.Exception: bang!
     at .bang(<console>:5)
     at .<init>(<console>:6) ... */






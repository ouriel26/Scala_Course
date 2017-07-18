/**************************************************************/
/******************** Reducing code duplication ***************/
/**************************************************************/


object FileMatcher1 {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesMatching(query: String,
                    matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }

//  def filesEnding(query: String) =
//    for (file <- filesHere; if file.getName.endsWith(query))
//      yield file

  def filesEnding(query: String) =
    filesMatching(query, _.endsWith(_))
    // _.endsWith(_) equivalent to (fileName, query) => fileName.endsWith(query)
  def filesContaining(query: String) =
    filesMatching(query, _.contains(_))
  def filesRegex(query: String) =
    filesMatching(query, _.matches(_))
}



object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles
  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file
  def filesEnding(query: String) =
    filesMatching(_.endsWith(query)) //
  def filesContaining(query: String) =
    filesMatching(_.contains(query))
  def filesRegex(query: String) =
    filesMatching(_.matches(query))
}

/*
Thus, _.endsWith(_) uses two bound variables,
and no free variables.
By contrast, the function literal _.endsWith(query),
used in the most recent example, contains one bound variable,
the argument represented by the underscore,
and one free variable named query.
It is only because Scala supports closures
that you were able to remove the query parameter
from filesMatching in the most recent example,
thereby simplifying the code even further.
 */


/**************************************************************/
/******************** Simplifying client code *****************/
/**************************************************************/

def containsNeg(nums: List[Int]) = nums.exists(_ < 0)
